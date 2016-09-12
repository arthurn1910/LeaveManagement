package com.management.leave.controler;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Medion on 2016-08-28.
 */
@SpringBootConfiguration
@SpringBootApplication
@ComponentScan("com.management.leave.config")
public class LeaveManagement extends WebMvcConfigurerAdapter{
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(LeaveManagement.class).run(args);
    }
}
