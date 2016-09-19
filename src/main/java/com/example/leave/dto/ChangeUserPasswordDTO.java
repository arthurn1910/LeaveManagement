package com.example.leave.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-18.
 */
public class ChangeUserPasswordDTO {
    private String login;

    @NotNull
    @NotEmpty
    @Size(min=6, max=20)
    private String newPassword;

    public ChangeUserPasswordDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
