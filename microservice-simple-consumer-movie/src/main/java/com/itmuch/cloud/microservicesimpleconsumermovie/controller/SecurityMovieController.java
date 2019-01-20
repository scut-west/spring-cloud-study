package com.itmuch.cloud.microservicesimpleconsumermovie.controller;

import com.itmuch.cloud.microservicesimpleconsumermovie.entity.User;
import com.itmuch.cloud.microservicesimpleconsumermovie.feign.UserFeignClient;
import com.itmuch.cloud.microservicesimpleconsumermovie.feign.config.FeignConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Import(FeignClientsConfiguration.class)
@RestController
public class SecurityMovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityMovieController.class);

    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public SecurityMovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "123"))
                .target(UserFeignClient.class, "http://MICROSERVICE-PROVIDER-USER/");
        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "456"))
                .target(UserFeignClient.class, "http://MICROSERVICE-PROVIDER-USER/");
    }

    @HystrixCommand(fallbackMethod = "findByIdUserFallback")
    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return this.adminFeignClient.findById(id);
    }

    public User findByIdUserFallback(Long id, Throwable throwable) {
        SecurityMovieController.LOGGER.error("go into fallback function, error is: ", throwable);
        User user = new User();
        user.setId(-1L);
        user.setName("default user");
        return user;
    }
}
