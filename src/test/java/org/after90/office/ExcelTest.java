package org.after90.office;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

  @Autowired
  private Excel excel;

  @Test
  public void modifyExcel() {
    excel.modifyExcel();
  }

  @Test
  public void getHash() {
    excel.getHash();
  }
}