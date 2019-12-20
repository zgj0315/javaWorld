package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HttpProxyServiceTest {

  @Mock
  MockHttpServletRequest request;

  @Mock
  MockHttpServletResponse response;

  @Before
  public void setUp() {
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    log.info("set uri");
    request.setCharacterEncoding("UTF-8");
    request.setMethod("GET");
    request.setContextPath("");
    request.setServletPath("/api");
    request.setPathInfo("/tasks/1");
    request.setRequestURI("/es/_cat/nodes");
    request.addHeader("Accept", "application/json");
  }

  @Autowired
  private HttpProxyService httpProxyService;

  @Test
  public void doGet() {
    log.info("uri:{}", request.getRequestURI());
    try {
      httpProxyService.doGet(request, response);
      log.info("content:{}", response.getContentAsString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void doPost() {
  }
}