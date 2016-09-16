package com.example.leave.dto;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-04.
 */

public class RegisterDTO {
    @NotEmpty
    @Size(min=3, max=20)
    private String login;

    @NotNull
    @NotEmpty
    @Size(min=6, max=20)
    private String password;
    private String confirmPassword;

    @NotNull
    @NotEmpty
    @Email
    @Size(min=1)
    private String email;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String lastname;

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
        if(password.equals(this.confirmPassword))
            this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirm_password) {
            this.confirmPassword = confirm_password;

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
