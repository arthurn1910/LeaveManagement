package com.example.leave.manager.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.example.leave.repository.leave.LeaveRepository;
import com.example.leave.repository.leave.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
@Component
public class LeaveManager implements LeaveManagerInterface {
    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Override
    @Transactional
    public List<LeaveType> getListLeaveType() {
        return (List) leaveTypeRepository.findAll();
    }

    @Override
    @Transactional
    public void createLeave(Leave leave) {
        leave.setId(leaveRepository.getNewID());
        leaveRepository.save(leave);
    }

    @Override
    @Transactional
    public void removeLeave(Long id) {
        leaveRepository.remove(id);
    }

    @Override
    @Transactional
    public List<Leave> getLeave(Account account) {
        return leaveRepository.findAllByAccount(account);
    }

    @Override
    @Transactional
    public List<Leave> getLeaveUserWithType(Account account, LeaveType leaveType) {
        return leaveRepository.findAllByAccountAndLeaveType(account, leaveType);
    }

    @Override
    @Transactional
    public List<Leave> getLeaveWithTypeAndTeamAndDate(LeaveType type, TeamGroup teamGroup, Date date) {
        List<Leave> leaveList=new ArrayList<>();
        for(TeamGroupMember teamGroupMember : teamGroup.getTeamGroupMembers()){
            List<Leave> leaveList1=leaveRepository.findAllByTypeAndAccountAndActiveAndConfirm(type, teamGroupMember.getEmployee());
            for(Leave leave : leaveList1){
                if((leave.getDateStart().before(date)|| leave.getDateStart().equals(date))&& (leave.getDateEnd().after(date) || leave.getDateEnd().equals(date)))
                    leaveList.add(leave);
            }
        }
        return leaveList;
    }

    @Override
    @Transactional
    public List<Leave> getAllLeave() {
       return leaveRepository.findAllByActiveAndConfirm();
    }


}
