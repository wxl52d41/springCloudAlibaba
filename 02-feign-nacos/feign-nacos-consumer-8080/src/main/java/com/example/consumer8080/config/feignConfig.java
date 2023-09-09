package com.example.consumer8080.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xlwang55
 * @date 2022/8/31 16:41
 */
@Configuration
public class feignConfig {
    /**
     *  随机策略
     */
//    @Bean
    public IRule loadBalanceRule() {
        return new RandomRule();
    }
    @Bean
    Logger.Level feginLoggerLevel(){
//        return Logger.Level.FULL;
        return Logger.Level.BASIC;
//        return Logger.Level.HEADERS;
    }


}
