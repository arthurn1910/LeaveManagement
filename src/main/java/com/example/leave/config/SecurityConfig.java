package com.example.leave.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Medion on 2016-09-12.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/menu").permitAll()
                .antMatchers("/isAuthenticated").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/css/style.css").permitAll()
                //Account
                .antMatchers("/accountDetails").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/getAccountDetails").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/changePassword").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/savePassword").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/editAccountGet").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/editAccount").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT","MANAGER","EMPLOYEE")
                .antMatchers("/usersList").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/usersListData").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/changeUserActiveStatus").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/changeUserConfirmStatus").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/editUserAccount").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/getUserAccount").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/getUserRole").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/changeUserRole").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/setUserRole").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/changeUserPassword").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/saveUserPassword").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")
                .antMatchers("/changeUserPassword").hasAnyRole("ADMINISTRATOR", "ACCOUNTANT")

                //Group
                .antMatchers("/getImportantDates").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/viewImportantDates").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/removeImportantDates").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/getShowGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/showGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/getTeamGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/listMemberGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/listMemberGroupView").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/removeMember").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/memberLeaveInGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/memberPlannedLeaveInGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/confirmLeave").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/rejectLeave").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/getLeaveInGroup").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/getGroupList").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/groupList").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/groupListData").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/getUserGroupDTO").hasAnyRole("MANAGER","EMPLOYEE")
                .antMatchers("/applyToGroup").hasAnyRole("EMPLOYEE")
                .antMatchers("/getGroupLeave").hasAnyRole("MANAGER")
                .antMatchers("/groupApprovedLeave").hasAnyRole("MANAGER")
                .antMatchers("/createImportantDate").hasAnyRole("MANAGER")
                .antMatchers("/createImportantDateView").hasAnyRole("MANAGER")
                .antMatchers("/getCreateGroup").hasAnyRole("MANAGER")
                .antMatchers("/createGroup").hasAnyRole("MANAGER")
                .antMatchers("/getAdministrationGroup").hasAnyRole("MANAGER")
                .antMatchers("/administrationGroup").hasAnyRole("MANAGER")
                .antMatchers("/listOfApplication").hasAnyRole("MANAGER")
                .antMatchers("/acceptApplication").hasAnyRole("MANAGER")
                .antMatchers("/rejectApplication").hasAnyRole("MANAGER")
                .antMatchers("/removeGroup").hasAnyRole("MANAGER")
                .antMatchers("/removeGroupView").hasAnyRole("MANAGER")
                //leave
                .antMatchers("/createLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/viewLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/removeLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getYourLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getLeave").hasAnyRole("ACCOUNTANT")
                .antMatchers("/reportLeave").hasAnyRole("ACCOUNTANT")
                .antMatchers("/getYourLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getLeaveDetails").hasAnyRole("EMPLOYEE")
                .antMatchers("/createParentalLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getBlockDate").hasAnyRole("EMPLOYEE")
                .antMatchers("/createVacationLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getCreateParentalLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getCreateLeave").hasAnyRole("EMPLOYEE")
                .antMatchers("/getCreateVacationLeave").hasAnyRole("EMPLOYEE")

                .anyRequest()
                .authenticated().and().formLogin()
                .loginPage("/login").failureUrl("/login?error").permitAll().and()
                .logout().permitAll()
        .and().csrf().disable();
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**/**");
    }
}