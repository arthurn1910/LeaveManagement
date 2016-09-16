package com.example.leave.entity;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Medion on 2016-09-12.
 */

@Entity
@Table(name = "account")
@SecondaryTable(name="user_data")
@NamedQuery(name = "Account.getNewID",
        query = "select max(id) from Account")
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
    @Column(name = "version")
    @Version
    private long version;
    @Column(name = "version", table = "user_data")
    private long versionUserData;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "account")
    private Set<AccessLevel> accessLevelCollection = new HashSet<>();

    @Column(name = "name", table = "user_data")
    private String name;
    @Column(name = "lastname", table = "user_data")
    private String lastName;
    @Column(name = "email", table = "user_data")
    private String email;
    @Column(name = "starting_date", table = "user_data")
    private Date startingDate;
    @Column(name = "expirience_year", table = "user_data")
    private int expirienceYear;
    @Column(name = "expirience_month", table = "user_data")
    private int expirienceMonth;
    @Column(name = "expirience_day", table = "user_data")
    private int expirienceDay;
    @Column(name = "create_date", table = "user_data")
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public long getVersionUserData() {
        return versionUserData;
    }

    public void setVersionUserData(long versionUserData) {
        this.versionUserData = versionUserData;
    }

    public Long getId() {
        return id;
    }

    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = new Md5PasswordEncoder().encodePassword(password,null);
        this.active=true;
        this.confirm=false;
        this.version=0;
    }

    public Account(Long id, String login, String password, Boolean confirm, Boolean active, long version) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.confirm = confirm;
        this.active = active;
        this.version = version;
    }

    public void setUserData(String name, String lastName, String email){
        this.name=name;
        this.lastName=lastName;
        this.email=email;
        this.createDate=new Date();
        this.startingDate=new Date();
        this.expirienceYear=0;
        this.expirienceMonth=0;
        this.expirienceDay=0;
        this.versionUserData=0;
    }

    /*public Collection<AccessLevel> getAccessLevelCollection() {
        return accessLevelCollection;
    }

    public void setAccessLevelCollection(Collection<AccessLevel> accessLevelCollection) {
        this.accessLevelCollection = accessLevelCollection;
    }*/

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
        this.password = new Md5PasswordEncoder().encodePassword(password,null);
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


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}