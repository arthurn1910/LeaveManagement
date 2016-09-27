package com.example.leave.dto.group;

import com.example.leave.entity.group.TeamGroupMember;

import java.util.List;

/**
 * Created by Medion on 2016-09-23.
 */
public class ListApplicationGroupDTO {

    List<TeamGroupMember> teamGroupMemberList;

    public ListApplicationGroupDTO() {
    }

    public List<TeamGroupMember> getTeamGroupMemberList() {
        return teamGroupMemberList;
    }

    public void setTeamGroupMemberList(List<TeamGroupMember> teamGroupMemberList) {
        this.teamGroupMemberList = teamGroupMemberList;
    }
}
