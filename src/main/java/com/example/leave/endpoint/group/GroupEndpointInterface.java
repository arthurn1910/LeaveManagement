package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.group.UserGroupDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupEndpointInterface {
    void createGroup(String title);

    List<TeamGroup> getAllGroups();

    List<TeamGroupDTO> getAllGroupsDTO();

    void joinToGroup(TeamGroupDTO teamGroupDTO);

    void getYourAccount();

    void getTeamGroup(Long id);

    List<TeamGroupMember> getTeamGroupUser(Boolean active);

    void acceptApplication(String login);

    void removeMember(String login);

    List<TeamGroupMember> getMemberInGroup();

    TeamGroup getTeamGroup();

    void createImportantDate(ImportantDateDTO importantDateDTO);

    void removeImportantDate(ImportantDates importantDates);

    List<ImportantDates> getImportantDates(TeamGroup teamGroup);

    List<Leave> getAllLeavePlannedInGroup(TeamGroup teamGroup);

    void rejectPlannedLeave(Leave leave);

    UserGroupDTO getUserGroup();

    public List<TeamGroupDTO> getTeamGroupDTOList(Boolean active);

    public void setTeamGroup(TeamGroup teamGroup);

    public Account getAccount();

    public void setAccount(Account account);

    TeamGroupDTO getTeamGroupDTO();
}
