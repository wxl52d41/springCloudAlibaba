package com.example.consumer8080.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xlwang55
 * @date 2022/9/8 11:20
 */
@Configuration
public class hystrixRuleConfig {

    /**
     * 初始化生效的降级规则
     */
    @Bean
    public void initRule() {
        List<DegradeRule> rules = new ArrayList<>();
        rules.add(slowRequestDegradeRule());
        rules.add(errorRatioDegradeRule());
        rules.add(errorCountDegradeRule());
        DegradeRuleManager.loadRules(rules);
    }

    /**
     * 慢调用
     */
    @Bean
    public DegradeRule slowRequestDegradeRule() {
        //定义降级规则
        DegradeRule degradeRule = new DegradeRule();
        //指定降级规则名称
        degradeRule.setResource("slowRequestDegradeRule");
        //指定熔断策略为Rt(response time)
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //指定熔断阈值为200ms（请求响应时长大于200ms，统计为慢调用）
        degradeRule.setCount(200);
        //指定熔断时长60s
        degradeRule.setTimeWindow(60);
        //指定在1秒的统计时间窗内至少要5个请求
        degradeRule.setMinRequestAmount(5);
        //指定若要启动熔断，则在1秒内至少要3次慢请求
        degradeRule.setRtSlowRequestAmount(3);
        return degradeRule;
    }

    /**
     * 异常比
     */
    @Bean
    public DegradeRule errorRatioDegradeRule() {
        DegradeRule degradeRule = new DegradeRule();
        //指定降级规则名称
        degradeRule.setResource("errorRatioDegradeRule");
        //指定熔断策略为异常比
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
        //指定熔断阈值为50%
        degradeRule.setCount(0.5);
        //指定熔断时长60s
        degradeRule.setTimeWindow(60);
        //指定在1秒的统计时间窗内至少要5个请求
        degradeRule.setMinRequestAmount(5);
        return degradeRule;
    }

    /**
     * 异常数
     */
    @Bean
    public DegradeRule errorCountDegradeRule() {
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("errorCountDegradeRule");
        //指定熔断策略为异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        //指定熔断阈值为3
        degradeRule.setCount(3);
        //指定熔断时长60s
        degradeRule.setTimeWindow(60);
        //指定在1秒的统计时间窗内至少要5个请求
        degradeRule.setMinRequestAmount(5);
        return degradeRule;
    }
}
