package org.after90.study;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HttpClientStudy {

  public void sendGetRequest() {
    String url = "http://172.16.43.17:9200/_cat/nodes";
  }
}
