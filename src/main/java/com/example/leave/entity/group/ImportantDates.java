package com.example.leave.entity.group;

import com.example.leave.entity.account.Account;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Medion on 2016-09-20.
 */
@Entity
@Table(name = "important_dates")
public class ImportantDates {
    @Id
    @Column(name = "important_dates_id")
    private Long id;
    @JoinColumn(name = "id_team_group ", referencedColumnName = "team_group_id", updatable=false)
    @ManyToOne
    private TeamGroup teamGroup;

    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "version")
    @Version
    private long version;


    public ImportantDates() {
    }

    public ImportantDates(Long id, TeamGroup teamGroup, Date dateStart, Date dateEnd, long version) {
        this.id = id;
        this.teamGroup = teamGroup;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.version = version;
    }

    public ImportantDates(TeamGroup teamGroup, Date dateStart, Date dateEnd) {
        this.teamGroup = teamGroup;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.version=0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


}
