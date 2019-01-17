package com.itmuch.cloud.microservicesimpleconsumermovie.feign;

import com.itmuch.cloud.microservicesimpleconsumermovie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="MICROSERVICE-PROVIDER-USER")
public interface UserFeignClient {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
