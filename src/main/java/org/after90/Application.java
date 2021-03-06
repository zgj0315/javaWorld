
package org.after90;

import lombok.extern.slf4j.Slf4j;
import org.after90.sentinel.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhaogj
 */
@SpringBootApplication
@EnableCaching
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
