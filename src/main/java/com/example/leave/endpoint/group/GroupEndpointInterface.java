package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.group.UserGroupDTO;
import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;

import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupEndpointInterface {
    void createGroup(List<String> data);

    List<TeamGroup> getAllGroups();

    List<TeamGroupDTO> getAllGroupsDTO();

    void joinToGroup(TeamGroupDTO teamGroupDTO);

    void getYourAccount();

    TeamGroup getTeamGroup(Long id);

    List<TeamGroupMember> getTeamGroupUser(Boolean active);

    String acceptApplication(List<String> data);

    void removeMember(List<String> data);

    List<TeamGroupMember> getMemberInGroup();

    TeamGroup getTeamGroup();

    void createImportantDate(String date);

    void removeImportantDate(String id);

    List<ImportantDateDTO> getImportantDates();

    void rejectPlannedLeave(Leave leave);

    UserGroupDTO getUserGroup();

    public List<TeamGroupDTO> getTeamGroupDTOList(Boolean active);

    public void setTeamGroup(TeamGroup teamGroup);

    public Account getAccount();

    public void setAccount(Account account);

    public TeamGroupDTO getTeamGroupDTO();

    public void removeGroup();

    public void applyToGroup(String id);

    public List<LeaveDTO> getAllLeaveInGroup();

    public String confirmLeave(String id);

    public void rejectLeave(String id);

    public int checkdate(Date date, TeamGroup teamGroup);
}
