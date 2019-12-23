package org.after90.controller;

import javax.jws.soap.SOAPBinding;
import lombok.extern.slf4j.Slf4j;
import org.after90.service.AsyncService;
import org.after90.study.EhcacheStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MessageController {

  @Autowired
  private AsyncService asyncService;

  @Autowired
  private EhcacheStudy ehcacheStudy;

  @RequestMapping("/message")
  public String message() {
    long lTime = System.currentTimeMillis();
    log.info("start message, time:{}", lTime);
    asyncService.executeAsync(lTime);
    log.info("end message, time:{}", lTime);
    return "success";
  }

  @ResponseBody
  @RequestMapping("/user/get/{user_id}")
  public String getUser(@PathVariable("user_id") String userId) {
    log.info("userId:{}", userId);
    return ehcacheStudy.getUser(userId);
  }
}
