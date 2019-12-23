package org.after90.study;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostConstructStudy {

  @Autowired
  private JDKStudy jdkStudy;

  @PostConstruct
  public void init() {
    log.info("start init, when project init.");
  }

  @PostConstruct
  public void doSomething() {
    log.info("start do something.");
    jdkStudy.mapStudy();
  }


}
