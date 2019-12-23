package org.after90.service;


import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public interface MonitorService {

  /**
   * show thread pool log.
   */
  public void ThreadPoolInfo(ThreadPoolTaskExecutor executor);

}
