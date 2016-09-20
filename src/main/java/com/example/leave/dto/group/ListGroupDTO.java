package com.example.leave.dto.group;

import com.example.leave.entity.group.TeamGroup;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public class ListGroupDTO {
    List<TeamGroupDTO> teamGroupList;

    public ListGroupDTO() {
    }

    public ListGroupDTO(List<TeamGroupDTO> teamGroupList) {
        this.teamGroupList = teamGroupList;
    }

    public List<TeamGroupDTO> getTeamGroupList() {
        return teamGroupList;
    }

    public void setTeamGroupList(List<TeamGroupDTO> teamGroupList) {
        this.teamGroupList = teamGroupList;
    }
}
