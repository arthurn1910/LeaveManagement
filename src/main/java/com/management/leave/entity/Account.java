package com.management.leave.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Medion on 2016-09-07.
 */

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "account_id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "confirm")
    private Boolean confirm;
    @Column(name = "active")
    private Boolean active;

//    @Column(name = "name", table = "user_data")
//    private String name;
//    @Column(name = "lastname", table = "user_data")
//    private String lastname;
//    @Column(name = "email", table = "user_data")
//    private String email;
//    @Column(name = "starting_date", table = "user_data")
//    private Date startingDate;
//    @Column(name = "expirienceYear", table = "user_data")
//    private int expirience_year;
//    @Column(name = "expirienceMonth", table = "user_data")
//    private int expirience_month;
//    @Column(name = "expirienceDay", table = "user_data")
//    private int expirience_day;
//    @Column(name = "create_date", table = "user_data")
//    private Date createDate;
    @Version
    private long version;

//    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "account_id")
//    private Collection<AccessLevel> accessLevelCollection = new ArrayList<AccessLevel>();
//
//    public Account(String login, String password, String name, String lastname, String email) {
//        this.login = login;
//        this.password = password;
//        this.name = name;
//        this.lastname = lastname;
//        this.email = email;
//    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getStartingDate() {
//        return startingDate;
//    }
//
//    public void setStartingDate(Date startingDate) {
//        this.startingDate = startingDate;
//    }
//
//    public int getExpirience_year() {
//        return expirience_year;
//    }
//
//    public void setExpirience_year(int expirience_year) {
//        this.expirience_year = expirience_year;
//    }
//
//    public int getExpirience_month() {
//        return expirience_month;
//    }
//
//    public void setExpirience_month(int expirience_month) {
//        this.expirience_month = expirience_month;
//    }
//
//    public int getExpirience_day() {
//        return expirience_day;
//    }
//
//    public void setExpirience_day(int expirience_day) {
//        this.expirience_day = expirience_day;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }


    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
