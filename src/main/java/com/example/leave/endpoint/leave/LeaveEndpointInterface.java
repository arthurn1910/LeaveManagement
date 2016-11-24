package com.example.leave.endpoint.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;

import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public interface LeaveEndpointInterface {
    void createLeave(LeaveDTO leaveDTO);

    List<LeaveType> getListLeaveType();

    void removeLeave(Long id);

    List<LeaveDTO> getYourLeave();
}
