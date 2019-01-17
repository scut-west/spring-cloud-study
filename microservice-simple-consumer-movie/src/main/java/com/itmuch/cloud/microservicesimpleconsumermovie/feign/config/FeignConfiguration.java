package com.itmuch.cloud.microservicesimpleconsumermovie.feign.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;

/*
 *  该类不需要@Configuration, 否则不能被@ComponentScan扫描到。 否则会成为全局Feign 配置
 */
public class FeignConfiguration {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    //还可以通过增加Interceptor
}
