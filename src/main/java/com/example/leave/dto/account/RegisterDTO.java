package com.example.leave.dto.account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-04.
 */

public class RegisterDTO {

    private String login="";
    private String password="";
    private String email="";
    private String name="";
    private String lastname="";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
