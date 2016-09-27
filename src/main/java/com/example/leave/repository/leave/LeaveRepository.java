package com.example.leave.repository.leave;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-26.
 */
public interface LeaveRepository extends CrudRepository<Leave, Long>{
    List<Leave> findAllByAccountAndActiveAndAfterDate(Account account,Boolean active, Date date);
}
