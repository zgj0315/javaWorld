package org.after90.service.impl;

import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.after90.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author zhaogj
 */
@Service
@Slf4j
public class MonitorServiceImpl implements MonitorService {


  @Override
  @Async("asyncComponentExecutor")
  public void ThreadPoolInfo(ThreadPoolTaskExecutor executor) {
    while (true) {
      try {
        Thread.sleep(1L * 200L);
      } catch (InterruptedException e) {
        log.error("sleep err", e);
      }
      if (executor == null) {
        log.info("executor is null");
        return;
      }
      ThreadPoolExecutor tpe = executor.getThreadPoolExecutor();
      log.info(
          "threadNamePrefix [{}], taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
          executor.getThreadNamePrefix(),
          tpe.getTaskCount(),
          tpe.getCompletedTaskCount(),
          tpe.getActiveCount(),
          tpe.getQueue().size());
    }
  }
}