package com.itmuch.cloud.microservicediscoveryeureka;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();        //csrf:Cross-site request forgery, default protected open so that other server can not register
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
