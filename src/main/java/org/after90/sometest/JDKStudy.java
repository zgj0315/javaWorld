package org.after90.sometest;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JDKStudy {

  public void mapStudy() {
    Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
    map.put("apple", "苹果");
    map.put("watermelon", "西瓜");
    map.put("banana", "香蕉");
    map.put("peach", "桃子");

    map.get("banana");
    map.get("apple");

    Iterator iter = map.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry) iter.next();
      log.info("key:{},value:{}", entry.getKey(), entry.getValue());
    }
  }

  public void arrayListStudy() {
    List<String> list = new ArrayList();
    log.info("add result:{}", list.add("hello"));
    log.info("set result:{}", list.set(0, "world"));
  }

  public void intStudy() {
    int a = 11;
    int b = a >> 1;
    int c = a << 1;
    log.info("a:{},b:{},c:{}", a, b, c);
    log.info("a:{},b:{},c:{}", Integer.toBinaryString(a), Integer.toBinaryString(b),
        Integer.toBinaryString(c));
  }

  public void atomicIntegerStudy() {
    AtomicInteger count = new AtomicInteger();
    count.incrementAndGet();
    count.get();
  }
}
