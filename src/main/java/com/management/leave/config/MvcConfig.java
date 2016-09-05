package com.management.leave.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.management.leave.controler" })
public class MvcConfig  extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

//    @RequestMapping("/home")
//    public String home(Map<String, Object> model) {
//        model.put("message", "Hello World");
//        model.put("title", "Hello Home");
//        return "/home";
//    }
//
//    @RequestMapping("/foo")
//    public String foo() {
//        throw new RuntimeException("Expected exception in controller");
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/account/login").setViewName("login");
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("start");
//        registry.addViewController("/register").setViewName("register");
//    }

}
