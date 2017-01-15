package com.example.leave.dto.group;

import com.example.leave.entity.group.TeamGroupMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medion on 2016-09-23.
 */
public class ListApplicationGroupDTO {

    List<TeamGroupMemberDTO> teamGroupMemberList;

    public ListApplicationGroupDTO() {
    }

    public List<TeamGroupMemberDTO> getTeamGroupMemberList() {
        return teamGroupMemberList;
    }

    public void setTeamGroupMemberList(List<TeamGroupMember> teamGroupMemberList) {
        List<TeamGroupMemberDTO> teamGroupMemberDTOList=new ArrayList<>();
        for(TeamGroupMember teamGroupMember: teamGroupMemberList){
            TeamGroupMemberDTO teamGroupMemberDTO=new TeamGroupMemberDTO();
            teamGroupMemberDTO.setTeamGroupMember(teamGroupMember);
            teamGroupMemberDTOList.add(teamGroupMemberDTO);
        }
        this.teamGroupMemberList = teamGroupMemberDTOList;
    }
}
