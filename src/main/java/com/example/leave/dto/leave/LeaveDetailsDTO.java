package com.example.leave.dto.leave;

/**
 * Created by arthurn on 26.11.16.
 */

public class LeaveDetailsDTO {
    private int leaveThisYear;
    private int leaveLastYear;
    private int reamainingVacationLeaveThisYear;
    private int reamainingVacationLeaveLastYear;


    public LeaveDetailsDTO() {
    }

    public int getLeaveThisYear() {
        return leaveThisYear;
    }

    public void setLeaveThisYear(int leaveThisYear) {
        this.leaveThisYear = leaveThisYear;
    }

    public int getLeaveLastYear() {
        return leaveLastYear;
    }

    public void setLeaveLastYear(int leaveLAstYear) {
        this.leaveLastYear = leaveLAstYear;
    }

    public int getReamainingVacationLeaveThisYear() {
        return reamainingVacationLeaveThisYear;
    }

    public void setReamainingVacationLeaveThisYear(int reamainingVacationLeaveThisYear) {
        this.reamainingVacationLeaveThisYear = reamainingVacationLeaveThisYear;
    }

    public int getReamainingVacationLeaveLastYear() {
        return reamainingVacationLeaveLastYear;
    }

    public void setReamainingVacationLeaveLastYear(int reamainingVacationLeaveLastYear) {
        this.reamainingVacationLeaveLastYear = reamainingVacationLeaveLastYear;
    }
}
