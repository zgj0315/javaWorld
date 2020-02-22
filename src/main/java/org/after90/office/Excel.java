package org.after90.office;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Excel {

  public void modifyExcel() {
    Map udidHash = getHash();
    String strFile = "/Users/zhaogj/tmp/threat.xlsx";
    String strOutput = "/Users/zhaogj/tmp/threat_output.xlsx";
    FileInputStream inputStream = null;
    Workbook workbook = null;
    FileOutputStream out = null;
    try {
      File file = new File(strFile);
      log.info("file:{}", strFile);
      if (!file.exists()) {
        log.info("file {} not exist, read excle exit.", strFile);
        return;
      }
      inputStream = new FileInputStream(strFile);
      workbook = new XSSFWorkbook(inputStream);
      log.info("workbook.getNumberOfSheets:{}", workbook.getNumberOfSheets());
      Sheet sheet = workbook.getSheetAt(0);
      //      log.info("last row num:{}", sheet.getLastRowNum());
      //      int firstRowNum = sheet.getFirstRowNum();
      //      log.info("firstRowNum:{}", firstRowNum);

      //      log.info("data:{}", firstRow.getCell(1).getStringCellValue());
      for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
        Row row = sheet.getRow(i);
        Cell cell = row.getCell(1);
//        log.info("data:{}", cell.getStringCellValue());
        // 这里操作替换
        String strUdid = cell.getStringCellValue();
        if (strUdid.contains("...")) {
          cell.setCellValue("" + udidHash.get(strUdid));
        }
      }
      out = new FileOutputStream(strOutput);
      workbook.write(out);
    } catch (Exception e) {
      log.error("read exception", e);
    } finally {
      if (null != out) {
        try {
          out.close();
        } catch (Exception e) {
          log.error("out err:", e);
        }

      }
      if (null != workbook) {
        try {
          workbook.close();
        } catch (Exception e) {
          log.error("workbook err", e);
        }
      }
      if (null != inputStream) {
        try {
          inputStream.close();
        } catch (Exception e) {
          log.error("inputstream err", e);
        }
      }
    }
  }

  public Map getHash() {
    Map map = new HashMap();
    StringBuffer sbBuffer = new StringBuffer();
    String strFile = "/Users/zhaogj/tmp/threat.json";
    File file = new File(strFile);
    InputStream is = null;
    BufferedReader reader = null;
    try {
      is = new FileInputStream(strFile);
      reader = new BufferedReader(new InputStreamReader(is));
      String strLine = reader.readLine();
      while (strLine != null) {
        sbBuffer.append(strLine);
        sbBuffer.append("\n");
        strLine = reader.readLine();
      }
      String strBuffer = sbBuffer.toString();
      int iBegin = strBuffer.indexOf("\"udid\":");
      while (iBegin > 0) {
        String json = strBuffer.substring(iBegin, iBegin + 45);
        //        log.info("output:{}", json);
        String strKey = json.substring(8, 12) + "..." + json.substring(40, 44);
        String strValue = json.substring(8, 44);
        //        log.info("strKey:{}, strValue:{}", strKey, strValue);
        map.put(strKey, strValue);
        strBuffer = strBuffer.replaceAll(json, "");
        iBegin = strBuffer.indexOf("\"udid\":");
      }
//      log.info("strBuffer:{}", strBuffer);
    } catch (Exception e) {
      log.error("", e);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Exception e) {
          log.error("", e);
        }
      }
      if (is != null) {
        try {
          is.close();
        } catch (Exception e) {
          log.error("", e);
        }
      }
    }
    return map;
  }
}
