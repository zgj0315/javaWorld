package org.after90.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.after90.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhaogj
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfiguration {

  @Autowired
  private MonitorService monitorService;

  @Bean
  public Executor asyncServiceExecutor() {
    log.info("init asyncServiceExecutor");
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
    //配置核心线程数
    executor.setCorePoolSize(3);
    //
    executor.setKeepAliveSeconds(10);
    //配置最大线程数
    executor.setMaxPoolSize(20);
    //配置队列大小
    executor.setQueueCapacity(5);
    //配置线程池中的线程的名称前缀
    executor.setThreadNamePrefix("async-service-");

    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    //    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    //    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    // 队列满了的话，把老的删除，加入新的
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

    //执行初始化
    executor.initialize();
    //启动监控
//    monitorService.ThreadPoolInfo(executor);
    return executor;
  }

  @Bean
  public Executor asyncComponentExecutor() {
    log.info("init asyncComponentExecutor");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    //配置核心线程数
    executor.setCorePoolSize(2);
    //配置最大线程数
    executor.setMaxPoolSize(2);
    //配置队列大小
    executor.setQueueCapacity(5);
    //配置线程池中的线程的名称前缀
    executor.setThreadNamePrefix("async-commponent-");

    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //执行初始化
    executor.initialize();
    return executor;
  }
}
