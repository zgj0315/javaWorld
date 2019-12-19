
package org.after90;

import lombok.extern.slf4j.Slf4j;
import org.after90.sentinel.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaogj
 */
@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    log.info("args.length:{}", args.length);
    SpringApplication.run(Application.class, args);
    log.info("last line in main.");
  }

}
