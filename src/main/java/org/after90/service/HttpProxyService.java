package org.after90.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhaogj
 */
@Service
@Slf4j
public class HttpProxyService {

  @Value("${es.url}")
  private String esUrl;

  public void doGet(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(
        esUrl + "/" + servletRequest.getRequestURI().substring(4));
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
      try {
        httpclient.close();
      } catch (IOException e) {
        log.error("httpclient.close err", e);
      }
    }
  }

  public void doPost(HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {

    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(esUrl + "/" + servletRequest.getRequestURI().substring(4));
    CloseableHttpResponse proxyResponse = null;
    try {
      httpPost.setEntity(
          new InputStreamEntity(servletRequest.getInputStream(),
              Long.parseLong(servletRequest.getHeader("Content-Length"))));
      proxyResponse = httpclient.execute(httpPost);
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
      log.error("do post err", e);
    } finally {
      try {
        proxyResponse.close();
      } catch (IOException e) {
        log.error("response.close err", e);
      }
      try {
        httpclient.close();
      } catch (IOException e) {
        log.error("httpclient.close err", e);
      }
    }
  }
}
