package com.example.leave.manager.leave;

import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;

/**
 * Created by Medion on 2016-09-27.
 */
public interface LeaveManagerInterface {
    Iterable<LeaveType> getListLeaveType();

    void createLeave(Leave leave);

    void removeLeave(Leave leave);
}
