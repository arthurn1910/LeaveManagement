package com.example.leave.manager.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;

import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public interface LeaveManagerInterface {
    Iterable<LeaveType> getListLeaveType();

    void createLeave(Leave leave);

    void removeLeave(Long id);

    List<Leave> getLeave(Account account);

    List<Leave> getLeaveUserWithType(Account account, LeaveType one);


    List<Leave> getLeaveWithTypeAndTeamAndDate(LeaveType one, TeamGroup teamGroup, Date date);

    List<Leave> getAllLeave();

    List<Leave> findAllByAccountAndActiveAndConfirmAndDate(Account account, Boolean b, Boolean b1, Date date);
}
