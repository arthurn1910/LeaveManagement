package com.example.leave.repository.leave;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-26.
 */
public interface LeaveRepository extends CrudRepository<Leave, Long>{
    List<Leave> findAllByAccountAndActiveAndAfterDate(Account account,Boolean active, Date date);
    Leave findLeaveById(Long aLong);
    @Modifying
    void remove(Long id);
    @Modifying
    List<Leave> findAllByAccount(Account account);
    @Modifying
    List<Leave> findAllByAccountAndLeaveType(Account account, LeaveType leaveType);
    Long getNewID();

    @Modifying
    List<Leave> findAllByTypeAndAccountAndActiveAndConfirm(LeaveType type, Account account);

    @Modifying
    List<Leave> findAllByActiveAndConfirm();

    @Modifying
    List<Leave> findAllByAccountAndActiveAndConfirmAndCheckDate(Account employee, Boolean b, Boolean b1, Date date);
}
