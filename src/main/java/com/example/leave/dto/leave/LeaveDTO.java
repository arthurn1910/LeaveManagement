package com.example.leave.dto.leave;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public class LeaveDTO {
    private Long id;
    private LeaveType leaveType;
    private Date dateStart;
    private Date dateEnd;
    private Boolean active;
    private Boolean confirm;
    private UserDTO account;
    private int currentYearDays;
    private int lastYearDays;


    public LeaveDTO() {
    }

    public LeaveDTO(Leave leave) {
        this.id = leave.getId();
        this.leaveType = leave.getLeaveType();
        this.dateStart = leave.getDateStart();
        this.dateEnd = leave.getDateEnd();
        this.active = leave.getActive();
        this.confirm = leave.getConfirm();
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(leave.getAccount());
        this.account=userDTO;
        this.lastYearDays=leave.getLastYearDays();
        this.currentYearDays=leave.getCurrentYearDays();
    }

    public LeaveDTO(Long id, LeaveType leaveType, Date dateStart, Date dateEnd, Boolean active, Boolean confirm, Account account) {
        this.id = id;
        this.leaveType = leaveType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.confirm = confirm;
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(account);
        this.account=userDTO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public UserDTO getAccount() {
        return account;
    }

    public void setAccount(UserDTO account) {
        this.account = account;
    }

    public void setAccount(Account account) {
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(account);
        this.account = userDTO;
    }

    public int getCurrentYearDays() {
        return currentYearDays;
    }

    public void setCurrentYearDays(int currentYearDays) {
        this.currentYearDays = currentYearDays;
    }

    public int getLastYearDays() {
        return lastYearDays;
    }

    public void setLastYearDays(int lastYearDays) {
        this.lastYearDays = lastYearDays;
    }
}
