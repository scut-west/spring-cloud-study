package com.itmuch.cloud.microservicesimpleconsumermovie.feign;

import com.itmuch.cloud.microservicesimpleconsumermovie.entity.User;
import com.itmuch.cloud.microservicesimpleconsumermovie.feign.config.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//comment @FeignClient for customized Feign Client
//@FeignClient(name="MICROSERVICE-PROVIDER-USER", configuration = FeignConfiguration.class)   //通过configuration 属性配置特定feign config
public interface UserFeignClient {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)     //SpringMVC的注解
//    @RequestLine("GET /user/{id}")      //feign 自带的注解
    public User findById(@PathVariable("id") Long id);  //SpringMVC的注解
//    public User findById(@Param("id") Long id);    //feign 自带的注解
}
