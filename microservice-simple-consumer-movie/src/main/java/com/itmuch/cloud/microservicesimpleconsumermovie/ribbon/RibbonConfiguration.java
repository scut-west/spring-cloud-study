package com.itmuch.cloud.microservicesimpleconsumermovie.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *  该类不应该简历在@ComponentScan所扫描的包中， 否则会该类的配置信息将被所有的@RibbonClient共享
 *  但测试发现，不放在@ComponentScan所扫描的路径下，会导致ribbon配置无法生效？ -> 所以只能增加全局配置？
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
