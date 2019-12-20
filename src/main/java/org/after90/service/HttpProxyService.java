package org.after90.service;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhaogj
 */
@Service
@Slf4j
public class HttpProxyService {

  public void doGet(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(
        "http://172.16.43.17:9200/" + servletRequest.getRequestURI().substring(4));
    CloseableHttpResponse proxyResponse = null;
    try {
      proxyResponse = httpclient.execute(httpGet);
      // set status
      servletResponse.setStatus(proxyResponse.getStatusLine().getStatusCode());
      // set entity
      HttpEntity entity = proxyResponse.getEntity();
      if (entity != null) {
        OutputStream servletOutputStream = servletResponse.getOutputStream();
        entity.writeTo(servletOutputStream);
      }
      EntityUtils.consume(entity);
    } catch (Exception e) {
      log.error("do get err", e);
    } finally {
      try {
        proxyResponse.close();
      } catch (IOException e) {
        log.error("response.close err", e);
      }
    }
  }

  public void doPost() {

  }
}
