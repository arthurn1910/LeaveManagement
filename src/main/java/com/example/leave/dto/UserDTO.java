package com.example.leave.dto;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Medion on 2016-09-17.
 */
public class UserDTO {
    private Long id;

    private String login;
    private Boolean active;
    private Boolean confirm;
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
    private Date startingDate;
    private int expirienceYear;
    private int expirienceMonth;
    private int expirienceDay;
    private Date createDate;
    private Collection<AccessLevel> accessLevelCollection = new ArrayList<>();

    public UserDTO() {
    }

    public void setAccount(Account account) {
        this.id = account.getId();
        this.login = account.getLogin();
        this.active = account.getActive();
        this.confirm = account.getConfirm();
        this.name = account.getName();
        this.lastname = account.getLastname();
        this.email = account.getEmail();
        this.startingDate = account.getStartingDate();
        this.expirienceYear = account.getExpirienceYear();
        this.expirienceMonth = account.getExpirienceMonth();
        this.expirienceDay = account.getExpirienceDay();
        this.createDate = account.getCreateDate();
        this.accessLevelCollection = account.getAccessLevelCollection();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public int getExpirienceYear() {
        return expirienceYear;
    }

    public void setExpirienceYear(int expirienceYear) {
        this.expirienceYear = expirienceYear;
    }

    public int getExpirienceMonth() {
        return expirienceMonth;
    }

    public void setExpirienceMonth(int expirienceMonth) {
        this.expirienceMonth = expirienceMonth;
    }

    public int getExpirienceDay() {
        return expirienceDay;
    }

    public void setExpirienceDay(int expirienceDay) {
        this.expirienceDay = expirienceDay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Collection<AccessLevel> getAccessLevelCollection() {
        return accessLevelCollection;
    }

    public void setAccessLevelCollection(Collection<AccessLevel> accessLevelCollection) {
        this.accessLevelCollection = accessLevelCollection;
    }

    public String getRoles(){
        String roles="";
        for(AccessLevel accessLevel : accessLevelCollection)
            roles+=accessLevel.getLevel()+" ";
        return roles;
    }
}
