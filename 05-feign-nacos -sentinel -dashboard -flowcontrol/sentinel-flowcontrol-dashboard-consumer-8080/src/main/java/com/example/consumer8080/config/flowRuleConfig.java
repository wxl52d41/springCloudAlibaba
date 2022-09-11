package com.example.consumer8080.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xlwang55
 * @date 2022/9/8 16:53
 */
@Configuration
public class flowRuleConfig {

    @Bean
    public void initFlowRule(){
        List<FlowRule> flowRules=new ArrayList<>();
        flowRules.add(qpsFlowRule());
        FlowRuleManager.loadRules(flowRules);
    }

    @Bean
    public FlowRule qpsFlowRule(){
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("qpsFlowRule");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(1);
        flowRule.setLimitApp("default");
        return flowRule;
    }
}
