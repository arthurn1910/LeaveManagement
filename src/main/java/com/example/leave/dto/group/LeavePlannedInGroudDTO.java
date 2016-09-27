package com.example.leave.dto.group;

import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.leave.Leave;
import com.sun.xml.internal.bind.v2.model.core.LeafInfo;

import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
public class LeavePlannedInGroudDTO {
    private TeamGroup teamGroup;

    private List<Leave> leaveList;

    public LeavePlannedInGroudDTO() {
    }

    public LeavePlannedInGroudDTO(TeamGroup teamGroup, List<Leave> leaveList) {
        this.teamGroup = teamGroup;
        this.leaveList = leaveList;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }
}
