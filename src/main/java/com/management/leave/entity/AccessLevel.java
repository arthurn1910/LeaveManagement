package com.management.leave.entity;

import javax.persistence.*;

/**
 * Created by Medion on 2016-09-07.
 */
@Entity
@Table(name = "access_level")
public class AccessLevel {
    @Id
    @Column(name = "access_level_id")
    @GeneratedValue
    private Long id;
    @Column(name = "level")
    private String level;

    @JoinColumn(name = "account", referencedColumnName = "account_id", updatable=false)
    @ManyToOne(optional = false)
    private Account account;

    @Column(name = "active")
    private Boolean active;

    @Version
    private long version;

    public AccessLevel(String level, Account account, Boolean active, long version) {
        this.level = level;
        this.account = account;
        this.active = active;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
