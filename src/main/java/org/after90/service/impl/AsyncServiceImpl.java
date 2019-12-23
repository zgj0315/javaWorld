package org.after90.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.after90.service.AsyncService;
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
  public void executeAsync(long lTime) {

    log.info("start executeAsync, time:{}", lTime);
    try {
      Thread.sleep(30000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("end executeAsync, time:{}", lTime);
  }

}
