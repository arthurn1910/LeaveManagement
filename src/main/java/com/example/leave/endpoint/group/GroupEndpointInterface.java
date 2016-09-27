package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;

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

    List<TeamGroupMember> getMemberInGroup(TeamGroup teamGroup);

    void removeMember(TeamGroupMember teamGroupMember);

    TeamGroup getTeamGroup();

    void createImportantDate(ImportantDateDTO importantDateDTO);

    void removeImportantDate(ImportantDates importantDates);

    List<ImportantDates> getImportantDates(TeamGroup teamGroup);

    List<Leave> getAllLeavePlannedInGroup(TeamGroup teamGroup);

    void rejectPlannedLeave(Leave leave);
}
