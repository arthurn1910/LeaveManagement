package com.example.leave.dto.account;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-18.
 */
public class ChangePasswordDTO {
    @NotNull
    @NotEmpty
    @Size(min=6, max=20)
    private String actualPassword;

    @NotNull
    @NotEmpty
    @Size(min=6, max=20)
    private String newPassword;
    @NotNull
    @NotEmpty
    @Size(min=6, max=20)
    private String confirmNewPassword;

    public ChangePasswordDTO() {
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        if(this.confirmNewPassword.equals(newPassword))
            this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
