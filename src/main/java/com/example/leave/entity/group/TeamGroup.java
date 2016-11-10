package com.example.leave.entity.group;

import com.example.leave.entity.account.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Medion on 2016-09-20.
 */

@Entity
@Table(name = "team_group")
@NamedQueries({
        @NamedQuery(name = "TeamGroup.findAllByAccount",
                query = "select t from TeamGroup t where account=?1")
})
public class TeamGroup implements Serializable{
    @Id
    @Column(name = "team_group_id")
    private Long id;
    @JoinColumn(name = "id_manager", referencedColumnName = "account_id", updatable=false)
    @ManyToOne
    private Account account;
    @Column(name = "group_title")
    private String title;
    @Column(name = "date_create")
    private Date createDate;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "teamGroup")
    private Collection<TeamGroupMember> teamGroupMembers = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "teamGroup")
    private Collection<ImportantDates> importantDates = new ArrayList<>();

    @Column(name = "version")
    @Version
    private long version;

    public TeamGroup() {
    }

    public TeamGroup(Account account, String groupTitle, Date createDate, long version) {
        this.account = account;
        this.title = groupTitle;
        this.createDate = createDate;
        this.version = version;
    }

    public TeamGroup(Long id, Account account, String groupTitle, Date createDate, long version) {
        this.id = id;
        this.account = account;
        this.title = groupTitle;
        this.createDate = createDate;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getManager() {
        return account;
    }

    public void setManager(Account manager) {
        this.account = manager;
    }

    public String getGroupTitle() {
        return title;
    }

    public void setGroupTitle(String groupTitle) {
        this.title = groupTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Collection<ImportantDates> getImportantDates() {
        return importantDates;
    }

    public void setImportantDates(Collection<ImportantDates> importantDates) {
        this.importantDates = importantDates;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<TeamGroupMember> getTeamGroupMembers() {
        return teamGroupMembers;
    }

    public void setTeamGroupMembers(Collection<TeamGroupMember> teamGroupMembers) {
        this.teamGroupMembers = teamGroupMembers;
    }
}
