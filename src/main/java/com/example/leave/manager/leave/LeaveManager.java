package com.example.leave.manager.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.example.leave.repository.leave.LeaveRepository;
import com.example.leave.repository.leave.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
    public List<Leave> getLeaveUserWithType(Account account, LeaveType leaveType) {
        return leaveRepository.findAllByAccountAndLeaveType(account, leaveType);
    }


}
