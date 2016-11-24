package com.example.leave.endpoint.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.example.leave.manager.leave.LeaveManager;
import com.example.leave.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
@Component
public class LeaveEndpoint implements LeaveEndpointInterface {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LeaveManager leaveManager;
    @Override
    public List<LeaveType> getListLeaveType() {
       return leaveManager.getListLeaveType();
    }

    @Override
    public void createLeave(LeaveDTO leaveDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
//        Leave leave=new Leave(account,leaveDTO.getLeaveType(), leaveDTO.getDateStart(), leaveDTO.getDateEnd(), true);
//        leaveManager.createLeave(leave);
    }

    @Override
    public void removeLeave(Long id) {
        leaveManager.removeLeave(id);
    }

    @Override
    public List<LeaveDTO> getYourLeave() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
        List<Leave> leaveList= leaveManager.getLeave(account);
        List<LeaveDTO> leaveListDTO=new ArrayList<>();
        for(Leave leave : leaveList){
            leaveListDTO.add(new LeaveDTO(leave));
        }
        System.out.println(leaveListDTO.size());
        return leaveListDTO;
    }
}
