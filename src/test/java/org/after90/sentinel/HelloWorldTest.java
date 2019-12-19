package org.after90.sentinel;


import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HelloWorldTest {

  @Autowired
  private HelloWorld helloWorld;

  @org.junit.Test
  public void main() {
  }

  @org.junit.Test
  public void testFlowRules() {
    helloWorld.testFlowRules();
  }
}