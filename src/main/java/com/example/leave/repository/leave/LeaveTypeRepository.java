package com.example.leave.repository.leave;

import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.LeaveType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Medion on 2016-09-26.
 */
public interface LeaveTypeRepository extends CrudRepository<LeaveType, Long>{

}
