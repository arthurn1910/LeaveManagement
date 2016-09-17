package com.example.leave.dto;

import com.example.leave.entity.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-17.
 */
public class EditAccountDTO {

    @NotNull
    @NotEmpty
    @Size(min=2)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=2)
    private String lastname;

    @NotNull
    @NotEmpty
    @Email
    @Size(min=1)
    private String email;

    public EditAccountDTO() {
    }

    public EditAccountDTO(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountData(Account account){
        this.name=account.getName();
        this.lastname=account.getLastname();
        this.email=account.getEmail();
    }

}
