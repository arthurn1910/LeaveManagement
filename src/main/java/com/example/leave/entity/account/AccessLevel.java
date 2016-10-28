package com.example.leave.entity.account;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * Created by Medion on 2016-09-15.
 */
@Entity
@Table(name = "access_level")
@NamedQuery(name = "AccessLevel.getNewAccessLevelID",
        query = "select max(id)+1 from AccessLevel")
public class AccessLevel {
    @Id
    @Column(name = "access_level_id")
    private Long id;
    @Column(name = "level")
    private String level;
    @Column(name = "active")
    private Boolean active;
    @Version
    private long version;

    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable=false)
    @ManyToOne(optional = false)
    private Account account;

    public AccessLevel() {
    }

    public AccessLevel(Long id, String level, Boolean active, long version) {
        this.id = id;
        this.level = level;
        this.active = active;
        this.version = version;
    }

    public AccessLevel(String level, Boolean active, Account account) {
        this.level = level;
        this.active = active;
        this.account = account;
        this.version=0;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
