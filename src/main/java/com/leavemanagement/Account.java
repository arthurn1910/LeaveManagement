package com.leavemanagement;

import com.sun.javafx.beans.IDProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.annotation.Generated;
import javax.persistence.Id;

/**
 * Created by Medion on 2016-08-15.
 */
@Entity
public class Account {
    @Id
    private Long account_id;
    private String login;
    private String password;
    private Boolean confirm;
    private Boolean active;
    private int version;

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirm=" + confirm +
                ", active=" + active +
                ", version=" + version +
                '}';
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
