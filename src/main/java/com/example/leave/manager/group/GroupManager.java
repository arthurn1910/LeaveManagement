package com.example.leave.manager.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.repository.group.ImportantDatesRepository;
import com.example.leave.repository.group.TeamGroupMemberRepository;
import com.example.leave.repository.group.TeamGroupRepository;
import com.example.leave.repository.leave.LeaveRepository;
import com.example.leave.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
@Component
public class GroupManager implements GroupManagerInterface {

    @Autowired
    TeamGroupRepository teamGroupRepository;

    @Autowired
    TeamGroupMemberRepository teamGroupMemberRepository;

    @Autowired
    ImportantDatesRepository importantDatesRepository;

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    Functions functions;

    @Override
    public void createGroup(TeamGroup createGroupDTO) {
        createGroupDTO.setId(7L);
        teamGroupRepository.save(createGroupDTO);
    }

    @Override
    public List<TeamGroup> getAllGroups() {
        return (List<TeamGroup>) teamGroupRepository.findAll();
    }

    @Override
    public TeamGroup getTeamGroup(Long id) {
        return teamGroupRepository.findOne(id);
    }

    @Override
    public void joinToGroup(TeamGroupMember teamGroupMember) {
        teamGroupMember.setId(3L);
        teamGroupMemberRepository.save(teamGroupMember);
    }

    @Override
    public List<TeamGroupMember> getTeamGroup(Account account, Boolean active) {
        List<TeamGroupMember> teamGroupMemberList=teamGroupMemberRepository.findAllByAccountAndActive(account, active);
        return teamGroupMemberList;
    }

    @Override
    public void rejectApplication(TeamGroupMember teamGroupMember) {
        teamGroupMemberRepository.delete(teamGroupMember);
    }

    @Override
    public void acceptApplication(TeamGroupMember teamGroupMember) {
        teamGroupMemberRepository.save(teamGroupMember);
    }

    @Override
    public void removeMember(TeamGroupMember teamGroupMember) {
        teamGroupMemberRepository.delete(teamGroupMember);
    }

    @Override
    public List<TeamGroupMember> getMemberInGroup(TeamGroup teamGroup) {
        return null;//teamGroupMemberRepository.findAllByTeamGroupIDAndActive(teamGroup, true);
    }

    @Override
    public void createImportantDate(ImportantDates importantDates) {
        importantDates.setId(5L);
        importantDatesRepository.save(importantDates);
    }

    @Override
    public void removeImportantDate(ImportantDates importantDates) {
        importantDatesRepository.delete(importantDates);
    }

    @Override
    public List<ImportantDates> getImportantDates(TeamGroup teamGroup) {
        return importantDatesRepository.findAllByTeamGroup(teamGroup);
    }


    @Override
    public List<Leave> getAllLeavePlannedInGroup(TeamGroup teamGroup) {
        List<TeamGroupMember> teamGroupMemberList=getMemberInGroup(teamGroup);
        List<Leave> leaveList=new ArrayList<>();
        Date date=functions.removeTimeInDate(new Date());
        for(TeamGroupMember teamGroupMember : teamGroupMemberList)
            leaveList.addAll(leaveRepository.findAllByAccountAndActiveAndAfterDate(teamGroupMember.getEmployee(), true, date));
        return leaveList;
    }

    @Override
    public void rejectPlannedLeave(Leave leave) {
        leaveRepository.delete(leave);
    }
}
