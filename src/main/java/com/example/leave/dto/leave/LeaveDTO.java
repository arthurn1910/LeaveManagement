package com.example.leave.dto.leave;

import com.example.leave.entity.leave.LeaveType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public class LeaveDTO {
    private List<LeaveType> leaveTypeList;
    @NotNull
    @NotEmpty
    private LeaveType leaveType;

    @NotEmpty
    @NotNull
    private Date dateStart;

    @NotEmpty
    @NotNull
    private Date dateEnd;

    public LeaveDTO() {
    }

    public LeaveDTO(List<LeaveType> leaveTypeList, LeaveType leaveType, Date dateStart, Date dateEnd) {
        this.leaveTypeList = leaveTypeList;
        this.leaveType = leaveType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public List<LeaveType> getLeaveTypeList() {
        return leaveTypeList;
    }

    public void setLeaveTypeList(List<LeaveType> leaveTypeList) {
        this.leaveTypeList = leaveTypeList;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
