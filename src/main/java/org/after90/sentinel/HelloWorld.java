package org.after90.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaogj
 */
@Slf4j
public class HelloWorld {

  public static void main(String[] args) {
    // 配置规则.
    initFlowRules();

    while (true) {
      // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
      try (Entry entry = SphU.entry("HelloWorld")) {
        // 被保护的逻辑
        log.info("Hello world.");
      } catch (BlockException ex) {
        // 处理被流控的逻辑
        log.info("blocked!");
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }

  }

  private static void initFlowRules() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    rule.setResource("HelloWorld");
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 20.
    rule.setCount(5);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }
}