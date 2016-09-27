package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.manager.group.GroupManager;
import com.example.leave.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
@Component
public class GroupEndpoint implements GroupEndpointInterface {
    private TeamGroup teamGroup;
    private Account account;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GroupManager groupManager;

    @Override
    public void createGroup(TeamGroupDTO createGroupDTO) {
        TeamGroup teamGroupTmp=new TeamGroup();
        teamGroupTmp.setVersion(0L);
        teamGroupTmp.setCreateDate(new java.util.Date());
        teamGroupTmp.setGroupTitle(createGroupDTO.getTitle());
        getYourAccount();
        teamGroupTmp.setManager(this.account);
        groupManager.createGroup(teamGroupTmp);
    }

    @Override
    public List<TeamGroup> getAllGroups() {
        return groupManager.getAllGroups();
    }

    @Override
    public void joinToGroup(TeamGroupDTO teamGroupDTO) {
        getYourAccount();
        getTeamGroup(teamGroupDTO.getID());
        TeamGroupMember teamGroupMember=new TeamGroupMember(this.account, this.teamGroup);
        groupManager.joinToGroup(teamGroupMember);
    }

    @Override
    public void getYourAccount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.account=accountRepository.findByLogin(user.getUsername());
    }

    @Override
    public void getTeamGroup(Long id) {
        this.teamGroup=groupManager.getTeamGroup(id);
    }

    @Override
    public List<TeamGroupMember> getApplicationToGroup() {
        getYourAccount();
        List<TeamGroupMember> teamGroupMemberList=groupManager.getApplicationToGroup(this.account);
        //TeamGroupMember a=new TeamGroupMember();
        //a.getEmployee().getName();
        return teamGroupMemberList;
    }

    @Override
    public void acceptApplication(TeamGroupMember teamGroupMember) {
       teamGroupMember.setActive(true);
        groupManager.acceptApplication(teamGroupMember);
    }

    @Override
    public void rejectApplication(TeamGroupMember teamGroupMember) {
        groupManager.rejectApplication(teamGroupMember);
    }

    @Override
    public List<TeamGroupMember> getMemberInGroup(TeamGroup teamGroup) {
        return groupManager.getMemberInGroup(teamGroup);
    }

    @Override
    public void removeMember(TeamGroupMember teamGroupMember) {
        groupManager.removeMember(teamGroupMember);
    }

    @Override
    public TeamGroup getTeamGroup() {
        this.teamGroup=groupManager.getTeamGroup(this.teamGroup.getId());
        return this.teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void createImportantDate(ImportantDateDTO importantDateDTO) {
        ImportantDates importantDates=new ImportantDates(this.teamGroup,Date.valueOf(importantDateDTO.getDateStart()),Date.valueOf(importantDateDTO.getDateEnd().toString()));
        groupManager.createImportantDate(importantDates);
    }

    @Override
    public void removeImportantDate(ImportantDates importantDates) {
        groupManager.removeImportantDate(importantDates);
    }

    @Override
    public List<ImportantDates> getImportantDates(TeamGroup teamGroup) {
        return groupManager.getImportantDates(teamGroup);
    }

    @Override
    public List<Leave> getAllLeavePlannedInGroup(TeamGroup teamGroup) {
        return groupManager.getAllLeavePlannedInGroup(teamGroup);
    }

    @Override
    public void rejectPlannedLeave(Leave leave) {
        groupManager.rejectPlannedLeave(leave);
    }
}
