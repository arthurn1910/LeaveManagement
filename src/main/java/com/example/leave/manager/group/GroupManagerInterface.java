package com.example.leave.manager.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupManagerInterface {
    void createGroup(TeamGroup createGroupDTO, TeamGroupMember teamGroupMember);

    List<TeamGroup> getAllGroups();

    TeamGroup getTeamGroup(Long id);

    void joinToGroup(TeamGroupMember teamGroupMember);

    List<TeamGroupMember> getTeamGroup(Account account, Boolean active);

    void acceptApplication(TeamGroupMember teamGroupMember);

    void removeMember(TeamGroupMember teamGroupMember);

    List<TeamGroupMember> getMemberInGroup(TeamGroup teamGroup);

    void createImportantDate(ImportantDates importantDates);

    List<ImportantDates> getImportantDates(TeamGroup teamGroup);

    void removeImportantDate(ImportantDates importantDates);

    @Transactional
    List<ImportantDates> getImportantDates(TeamGroup teamGroup, Date date);

    List<Leave> getAllLeaveInGroup(TeamGroup teamGroup);

    void rejectPlannedLeave(Leave leave);

    void removeGroup(TeamGroup teamGroup);

    void applyToGroup(TeamGroupMember teamGroupMember);

    public void rejectLeave(String id);

    public void confirmLeave(String id);
}
