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

}
