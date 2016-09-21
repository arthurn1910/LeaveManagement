package com.example.leave.entity.group;

import com.example.leave.entity.account.Account;

import javax.naming.directory.SearchResult;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Medion on 2016-09-20.
 */
@Entity
@Table(name = "team_group_member")
public class TeamGroupMember implements Serializable{
    @Id
    @Column(name = "team_group_id")
    private Long id;
    @JoinColumn(name = "id_user", referencedColumnName = "account_id", updatable=false)
    @ManyToOne
    private Account employee;
    @JoinColumn(name = "id_team_group", referencedColumnName = "team_group_id", updatable=false)
    @ManyToOne
    private TeamGroup teamGroup;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "version")
    @Version
    private long version;

    public TeamGroupMember() {
    }

    public TeamGroupMember(Account employee, TeamGroup teamGroup) {
        this.employee = employee;
        this.teamGroup = teamGroup;
        this.version=0;
        this.active=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Account employee) {
        this.employee = employee;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
