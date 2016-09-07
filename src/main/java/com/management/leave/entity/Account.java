package com.management.leave.entity;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "name", table = "user_data")
    private String name;
    @Column(name = "lastname", table = "user_data")
    private String lastname;
    @Column(name = "email", table = "user_data")
    private String email;
    @Column(name = "starting_date", table = "user_data")
    private Date startingDate;
    @Column(name = "expirienceYear", table = "user_data")
    private int expirience_year;
    @Column(name = "expirienceMonth", table = "user_data")
    private int expirience_month;
    @Column(name = "expirienceDay", table = "user_data")
    private int expirience_day;
    @Column(name = "create_date", table = "user_data")
    private Date createDate;
    @Version
    private long version;



}
