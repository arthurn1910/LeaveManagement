package com.example.leave.manager.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.manager.account.AccountManager;
import com.example.leave.repository.group.ImportantDatesRepository;
import com.example.leave.repository.group.TeamGroupMemberRepository;
import com.example.leave.repository.group.TeamGroupRepository;
import com.example.leave.repository.leave.LeaveRepository;
import com.example.leave.utils.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Medion on 2016-09-20.
 */
@Component
public class GroupManager implements GroupManagerInterface {

    Logger log = Logger.getLogger(AccountManager.class.getName());
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
    @Transactional
    public void createGroup(TeamGroup createGroup, TeamGroupMember teamGroupMember) {
        createGroup.setId(teamGroupRepository.getNewID());
        teamGroupRepository.save(createGroup);
        teamGroupMember.setId(teamGroupMemberRepository.getNewID());
        teamGroupMember.setActive(true);
        teamGroupMemberRepository.save(teamGroupMember);
        log.info("Group "+createGroup.getTitle()+" created");
    }

    @Override
    @Transactional
    public List<TeamGroup> getAllGroups() {
        return (List<TeamGroup>) teamGroupRepository.findAll();
    }

    @Override
    @Transactional
    public TeamGroup getTeamGroup(Long id) {
        return teamGroupRepository.findOne(id);
    }

    @Override
    @Transactional
    public void joinToGroup(TeamGroupMember teamGroupMember) {
        teamGroupMember.setId(3L);
        teamGroupMemberRepository.save(teamGroupMember);
        log.info("Add user "+teamGroupMember.getEmployee().getLogin()+" to group "+teamGroupMember.getTeamGroup().getTitle());
    }

    @Override
    @Transactional
    public List<TeamGroupMember> getTeamGroup(Account account, Boolean active) {
        List<TeamGroupMember> teamGroupMemberList=teamGroupMemberRepository.findAllByAccountAndActive(account, active);
        return teamGroupMemberList;
    }

    @Override
    @Transactional
    public void acceptApplication(TeamGroupMember teamGroupMember) {
        teamGroupMemberRepository.save(teamGroupMember);
        log.info("Accept user aplication "+teamGroupMember.getEmployee().getLogin()+" to group "+teamGroupMember.getTeamGroup().getTitle());
    }

    @Override
    @Transactional
    public void removeMember(TeamGroupMember teamGroupMember) {
        teamGroupMemberRepository.remove(teamGroupMember.getId());
        log.info("Remove user "+teamGroupMember.getEmployee().getLogin()+" from group "+teamGroupMember.getTeamGroup().getTitle());
    }

    @Override
    @Transactional
    public List<TeamGroupMember> getMemberInGroup(TeamGroup teamGroup) {
        return teamGroupMemberRepository.findAllByTeamGroupID(teamGroup);
    }

    @Override
    @Transactional
    public void createImportantDate(ImportantDates importantDates) {
        importantDates.setId(importantDatesRepository.getNewID());
        importantDatesRepository.save(importantDates);
        log.info("Create important date "+importantDates.getDate().toString()+" to group "+importantDates.getTeamGroup().getTitle());
    }

    @Override
    @Transactional
    public void removeImportantDate(ImportantDates importantDates) {

        importantDatesRepository.remove(importantDates.getId());
        log.info("Remove important date "+importantDates.getDate().toString()+" from group "+importantDates.getTeamGroup().getTitle());
    }

    @Override
    @Transactional
    public List<ImportantDates> getImportantDates(TeamGroup teamGroup) {
        return importantDatesRepository.findAllByTeamGroup(teamGroup);
    }

    @Override
    @Transactional
    public List<ImportantDates> getImportantDates(TeamGroup teamGroup, Date date) {
        return importantDatesRepository.findAllByTeamGroupAndDate(teamGroup,date);
    }


    @Override
    @Transactional
    public List<Leave> getAllLeaveInGroup(TeamGroup teamGroup) {
        List<TeamGroupMember> teamGroupMemberList=getMemberInGroup(teamGroup);
        List<Leave> leaveList=new ArrayList<>();
        Date date=functions.removeTimeInDate(new Date());
        for(TeamGroupMember teamGroupMember : teamGroupMemberList) {
            leaveList.addAll(leaveRepository.findAllByAccountAndActiveAndAfterDate(teamGroupMember.getEmployee(), true, date));
        }
        return leaveList;
    }

    @Override
    @Transactional
    public void rejectPlannedLeave(Leave leave) {
        leaveRepository.delete(leave);
        log.info("Reject planned leave user "+leave.getAccount().getLogin()+" id "+leave.getId());
    }

    @Override
    @Transactional
    public void removeGroup(TeamGroup teamGroup) {
        for(ImportantDates importantDates:teamGroup.getImportantDates()){
            removeImportantDate(importantDates);
        }
        for(TeamGroupMember teamGroupMember : teamGroup.getTeamGroupMembers()){
            removeMember(teamGroupMember);
        }
        teamGroupRepository.remove(teamGroup.getId());
        log.info("Group "+teamGroup.getTitle() + " removed");
    }

    @Override
    @Transactional
    public void applyToGroup(TeamGroupMember teamGroupMember) {
        teamGroupMember.setId(teamGroupMemberRepository.getNewID());
        teamGroupMemberRepository.save(teamGroupMember);
        log.info("Apply to group "+teamGroupMember.getTeamGroup().getTitle() +" user "+teamGroupMember.getEmployee().getLogin());
    }

    @Override
    @Transactional
    public void rejectLeave(String id) {
        Leave leave = leaveRepository.findLeaveById(Long.valueOf(id));
        leave.setActive(false);
        leaveRepository.save(leave);
        log.info("Reject leave "+leave.getId());
    }

    @Override
    @Transactional
    public void confirmLeave(String id) {
        Leave leave = leaveRepository.findLeaveById(Long.valueOf(id));
        leave.setConfirm(true);
        leaveRepository.save(leave);
        log.info("Confirm leave "+leave.getId());
    }
}
