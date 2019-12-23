package org.after90.controller;

import javax.jws.soap.SOAPBinding;
import lombok.extern.slf4j.Slf4j;
import org.after90.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MessageController {

  @Autowired
  private AsyncService asyncService;

  @RequestMapping("/message")
  public String message() {
    long lTime = System.currentTimeMillis();
    log.info("start message, time:{}", lTime);
    asyncService.executeAsync(lTime);
    log.info("end message, time:{}", lTime);
    return "success";
  }
}
