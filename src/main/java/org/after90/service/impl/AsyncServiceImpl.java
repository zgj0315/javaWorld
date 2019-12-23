package org.after90.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.after90.service.AsyncService;
import org.after90.service.MonitorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhaogj
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

  @Override
  @Async("asyncServiceExecutor")
  public void executeAsync() {

    log.info("start executeAsync");
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("end executeAsync");
  }

}
