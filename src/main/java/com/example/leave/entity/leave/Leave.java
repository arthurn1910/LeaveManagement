package com.example.leave.entity.leave;

import com.example.leave.entity.account.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Medion on 2016-09-26.
 */
@Entity
@Table(name = "leave")
@NamedQueries({
        @NamedQuery(name = "Leave.findAllByAccountAndActiveAndAfterDate",
                query = "select l from Leave l where account = ?1 and active = ?2 and dateEnd >= ?3")
})
public class Leave implements Serializable{

    @Id
    @Column(name = "leave_id")
    private Long id;

    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable=false)
    @ManyToOne(optional = false)
    private Account account;

    @JoinColumn(name = "leave_type_id", referencedColumnName = "leave_type_id", updatable=false)
    @OneToOne(optional = false)
    private LeaveType leaveType;

    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "active")
    private Boolean active;
    @Column(name = "version")
    @Version
    private long version;

    public Leave() {
    }

    public Leave(Account account, LeaveType leaveType, Date dateStart, Date dateEnd, Boolean active) {
        this.version=0;
        this.account = account;
        this.leaveType = leaveType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
    }

    public Leave(Long id, Account account, LeaveType leaveType, Date dateStart, Date dateEnd, Boolean active, long version) {
        this.id = id;
        this.account = account;
        this.leaveType = leaveType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
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
