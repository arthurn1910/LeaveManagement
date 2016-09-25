package com.example.leave.endpoint.group;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupEndpointInterface {
    void createGroup(TeamGroupDTO createGroupDTO);

    List<TeamGroup> getAllGroups();

    void joinToGroup(TeamGroupDTO teamGroupDTO);

    void getYourAccount();

    void getTeamGroup(Long id);

    List<TeamGroupMember> getApplicationToGroup();

    void acceptApplication(TeamGroupMember teamGroupMember);

    void rejectApplication(TeamGroupMember teamGroupMember);

    List<TeamGroupMember> getMemberInGroup(String s);

    void removeMember(TeamGroupMember teamGroupMember);
}
