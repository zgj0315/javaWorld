package org.after90.study;

import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SnowFlakeTest {

    @Test
    public void getId() {
        SnowFlake snowFlake = new SnowFlake(0, 0);
        for (int i = 0; i < 1000L; i++) {
            log.info("id:{}", snowFlake.nextId());
        }
    }
}