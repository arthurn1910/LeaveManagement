package com.example.leave.entity;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Medion on 2016-09-12.
 */

@Entity
@Table(name = "account")
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
    @Version
    private long version;

    //@JoinColumn(name = "account_id", referencedColumnName = "user_data_id", updatable=false)
   // @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "id")
   // private UserData userData;

    public Long getId() {
        return id;
    }

//    public UserData getUserData() {
//        return userData;
//    }

    public Account() {
    }

//    public void setUserData(UserData userData) {
//        this.userData = userData;
//    }

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}