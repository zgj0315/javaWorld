package org.after90.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author zhaogj
 */
@Service
@Slf4j
public class EhcacheStudy {

  @Cacheable(cacheNames = "users", key = "#userId")
  public String getUser(String userId) {
    log.info("get user, id:{}", userId);
    return userId;
  }
}
