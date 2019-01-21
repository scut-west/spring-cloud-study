//package com.itmuch.cloud.microservicesimpleprovideruser.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //use for testing, no encode.
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
//    }
//
//    @Component
//    class CustomUserDetailsService implements UserDetailsService {
//
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            if("user".equals(username)) {
//                SecurityUser user = new SecurityUser("user", "123", "user-role");
//                return user;
////                return new User(user.getUsername(), user.getPassword(), true,true,true,true, user.getAuthorities());
//            } else if("admin".equals(username)) {
//                SecurityUser admin = new SecurityUser("admin", "456", "admin-role");
//                return admin;
////                return new User(admin.getUsername(), admin.getPassword(), true,true,true,true, admin.getAuthorities());
//            } else {
//                return null;
//            }
//        }
//    }
//
//    class SecurityUser implements UserDetails {
//        private static final long servialVersionUID = 1L;
//
//        private Long id;
//        private String username;
//        private String password;
//        private String role;
//
//        public SecurityUser() {}
//
//        public SecurityUser(String username, String password, String role) {
////            super();
//            this.username = username;
//            this.password = password;
//            this.role = role;
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
//            authorities.add(authority);
//            return authorities;
//        }
//
//        @Override
//        public String getUsername() {
//            return this.username;
//        }
//
//        @Override
//        public String getPassword() {
//            return this.password;
//        }
//
//        //below 4 function need to return true. or will always need to input user and password but no login in success.
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//    }
//}
