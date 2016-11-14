package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.group.UserGroupDTO;
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
import java.util.ArrayList;
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
    public void createGroup(String title) {
        TeamGroup teamGroupTmp=new TeamGroup();
        teamGroupTmp.setVersion(0L);
        teamGroupTmp.setCreateDate(new java.util.Date());
        teamGroupTmp.setGroupTitle(title);
        getYourAccount();
        teamGroupTmp.setManager(this.account);
        TeamGroupMember teamGroupMember=new TeamGroupMember(this.account,teamGroupTmp);
        groupManager.createGroup(teamGroupTmp, teamGroupMember);
    }

    @Override
    public List<TeamGroup> getAllGroups() {
        return groupManager.getAllGroups();
    }

    @Override
    public List<TeamGroupDTO> getAllGroupsDTO() {
        List<TeamGroup> teamGroupList=getAllGroups();
        List<TeamGroupDTO> teamGroupDTOList=new ArrayList<>();
        for(TeamGroup teamGroup : teamGroupList){
            teamGroupDTOList.add(new TeamGroupDTO(teamGroup));
        }
        return teamGroupDTOList;

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
    public List<TeamGroupMember> getTeamGroupUser(Boolean active) {
        getYourAccount();
        List<TeamGroupMember> teamGroupMemberList=groupManager.getTeamGroup(this.account,active);
        return teamGroupMemberList;
    }

    @Override
    public void acceptApplication(String login) {
        for(TeamGroupMember teamGroupMember : this.teamGroup.getTeamGroupMembers()){
            if(teamGroupMember.getEmployee().getLogin().equals(login)){
                teamGroupMember.setActive(true);
                groupManager.acceptApplication(teamGroupMember);
            }
        }
        getTeamGroup();
    }

    @Override
    public void removeMember(String login) {
        for(TeamGroupMember teamGroupMember : this.teamGroup.getTeamGroupMembers()){
            if(teamGroupMember.getEmployee().getLogin().equals(login)){
                groupManager.removeMember(teamGroupMember);
            }
        }
        getTeamGroup();
    }

    @Override
    public List<TeamGroupMember> getMemberInGroup() {
        return groupManager.getMemberInGroup(this.teamGroup);
    }

    @Override
    public TeamGroup getTeamGroup() {
        this.teamGroup=groupManager.getTeamGroup(this.teamGroup.getId());
        return this.teamGroup;
    }

    @Override
    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    @Override
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

    @Override
    public UserGroupDTO getUserGroup(){
        UserGroupDTO userGroupDTO=new UserGroupDTO();
        getYourAccount();
        userGroupDTO.setAccount(this.account);
        userGroupDTO.setApplyTeamGroupDTOList(getTeamGroupDTOList(false));
        userGroupDTO.setTeamGroupDTOList(getTeamGroupDTOList(true));
        return userGroupDTO;

    }

    @Override
    public List<TeamGroupDTO> getTeamGroupDTOList(Boolean active){
        List<TeamGroupDTO> teamGroupDTOList=new ArrayList<>();
        List<TeamGroupMember> teamGroupMemberList=getTeamGroupUser(active);
        for(TeamGroupMember teamGroupMember: teamGroupMemberList){
            TeamGroup teamGroup=teamGroupMember.getTeamGroup();
            TeamGroupDTO teamGroupDTO=new TeamGroupDTO();
            teamGroupDTO.setTeamGroup(teamGroup);
            teamGroupDTOList.add(teamGroupDTO);
        }
        return teamGroupDTOList;
    }

    @Override
    public TeamGroupDTO getTeamGroupDTO(){
        TeamGroupDTO teamGroupDTO=new TeamGroupDTO(this.teamGroup);
        return teamGroupDTO;
    }
}
