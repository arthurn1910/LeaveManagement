package com.example.leave.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Medion on 2016-09-15.
 */
@Entity
@Table(name = "user_data")
@NamedQuery(name = "UserData.getNewDataID",
        query = "select max(id) from UserData")
public class UserData implements Serializable {

    @Id
    @Column(name = "user_data_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname ")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "starting_date")
    private Date startingDate;
    @Column(name = "expirience_year")
    private int expirienceYear;
    @Column(name = "expirience_month")
    private int expirienceMonth;
    @Column(name = "expirience_day")
    private int expirienceDay;
    @Column(name = "create_date")
    private Date createDate;
    @Version
    private long version;

    //@OneToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "user_data_id")
    //private Account account;

    public UserData(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.startingDate = new Date();
        this.expirienceYear = 0;
        this.expirienceMonth = 0;
        this.expirienceDay = 0;
        this.createDate = new Date();
        this.version = 0;

    }

    public UserData(Long id, String name, String lastName, String email, Date startingDate, int expirienceYear, int expirienceMonth, int expirienceDay, Date createDate, long version) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.startingDate = startingDate;
        this.expirienceYear = expirienceYear;
        this.expirienceMonth = expirienceMonth;
        this.expirienceDay = expirienceDay;
        this.createDate = createDate;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}