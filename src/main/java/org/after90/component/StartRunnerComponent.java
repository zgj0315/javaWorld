package org.after90.component;

import lombok.extern.slf4j.Slf4j;
import org.after90.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(value = 1)
public class StartRunnerComponent implements CommandLineRunner {

  @Autowired
  private MonitorService monitorService;

  @Override
  public void run(String... args) throws Exception {
//    monitorService.ThreadPoolInfo();
  }

}
