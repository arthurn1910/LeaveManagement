package com.management.leave.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Medion on 2016-08-31.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/home").access("hasRole('ADMINISTRATOR')")
                .antMatchers("/y").permitAll()
                .anyRequest()
                .authenticated().and().formLogin()
                .loginPage("/login").failureUrl("/login?error").permitAll().and()
                .logout().permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new Md5PasswordEncoder())
                .usersByUsernameQuery(
                        "select login,password, active from account where active = true and confirm = true and login=?")
                .authoritiesByUsernameQuery(
                        "select login, level from account as a INNER JOIN access_level as al on al.account_id = a.account_id where login=?");
    }
}