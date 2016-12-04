package com.example.leave.endpoint.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.dto.leave.LeaveDetailsDTO;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;

import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public interface LeaveEndpointInterface {
    void createLeave(List<String> data);

    void removeLeave(Long id);

    List<LeaveDTO> getYourLeave();

    LeaveDetailsDTO getLeaveDetails();

    List<LeaveType> getListLeaveType();

    String createParentalLeave(List<String> data);

    List<Date> getBlockDate();
}
