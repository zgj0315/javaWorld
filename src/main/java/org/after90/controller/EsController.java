package org.after90.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.after90.service.HttpProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaogj
 */
@RestController
@Slf4j
public class EsController {

  @Autowired
  private HttpProxyService httpProxyService;


  @RequestMapping(value = "/es/**", method = RequestMethod.GET)
  public void es(HttpServletRequest request, HttpServletResponse response) {
    httpProxyService.doGet(request, response);
  }
}
