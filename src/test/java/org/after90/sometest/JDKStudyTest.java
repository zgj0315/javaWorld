package org.after90.sometest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JDKStudyTest {

  @Autowired
  private JDKStudy jdkStudy;

  @Test
  public void mapStudy() {
    jdkStudy.mapStudy();
  }

  @Test
  public void arrayListStudy() {
    jdkStudy.arrayListStudy();
  }

  @Test
  public void intStudy() {
    jdkStudy.intStudy();
  }
}