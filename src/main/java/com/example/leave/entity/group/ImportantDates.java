package com.example.leave.entity.group;

import com.example.leave.entity.account.Account;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Medion on 2016-09-20.
 */
@Entity
@Table(name = "important_dates")
@NamedQueries({

        @NamedQuery(name = "ImportantDates.getNewID",
                query = "select max(id)+1 from ImportantDates"),
        @NamedQuery(name = "ImportantDates.findAllByTeamGroupAfterNow",
                query = "select i from ImportantDates i where teamGroup=?1 and date>=?2"),
        @NamedQuery(name = "ImportantDates.findAllByTeamGroupAndDate",
                query = "select i from ImportantDates i where teamGroup=?1 and date=?2"),
        @NamedQuery(name = "ImportantDates.remove",
                query = "delete from ImportantDates where id=?1")
})
public class ImportantDates {
    @Id
    @Column(name = "important_dates_id")
    private Long id;
    @JoinColumn(name = "id_team_group ", referencedColumnName = "team_group_id", updatable=false)
    @ManyToOne
    private TeamGroup teamGroup;

    @Column(name = "date")
    private Date date;

    @Column(name = "version")
    @Version
    private long version;


    public ImportantDates() {
    }

    public ImportantDates(Long id, TeamGroup teamGroup, Date date, long version) {
        this.id = id;
        this.teamGroup = teamGroup;
        this.date = date;
        this.version = version;
    }

    public ImportantDates(TeamGroup teamGroup, Date date) {
        this.teamGroup = teamGroup;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ImportantDates{" +
                "id=" + id +
                ", teamGroup=" + teamGroup.toString() +
                ", date=" + date +
                ", version=" + version +
                '}';
    }
}
