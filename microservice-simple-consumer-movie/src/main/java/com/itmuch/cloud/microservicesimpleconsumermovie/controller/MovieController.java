package com.itmuch.cloud.microservicesimpleconsumermovie.controller;

import com.itmuch.cloud.microservicesimpleconsumermovie.entity.User;
import com.itmuch.cloud.microservicesimpleconsumermovie.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
//        return this.restTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/user/" + id, User.class);
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.client.getInstances("microservice-provider-user");
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("MICROSERVICE-PROVIDER-USER");
        MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }
}
