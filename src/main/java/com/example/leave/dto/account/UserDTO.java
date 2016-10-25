package com.example.leave.dto.account;

import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-17.
 */
public class UserDTO {
    private Long id;

    private String login;
    private Boolean active;
    private Boolean confirm;
    private String name;
    private String lastname;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startingDate;
    private int expirienceYear;
    private int expirienceMonth;
    private int expirienceDay;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private List<String> accessLevel;
    private Long version;

    public UserDTO() {
        accessLevel=new ArrayList<>();
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
        setAccessLevel(account.getAccessLevelCollection());
        this.version=account.getVersion();
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

    public List<String> getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Collection<AccessLevel> accessLevelCollection) {
        for(AccessLevel accessLevel : accessLevelCollection)
            this.accessLevel.add(accessLevel.getLevel().substring(5));
    }

    public void setAccessLevel(List<String> accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", active=" + active +
                ", confirm=" + confirm +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", startingDate=" + startingDate +
                ", expirienceYear=" + expirienceYear +
                ", expirienceMonth=" + expirienceMonth +
                ", expirienceDay=" + expirienceDay +
                ", createDate=" + createDate +
                ", accessLevelCollection=" + accessLevel +
                ", version=" + version +
                '}';
    }
}