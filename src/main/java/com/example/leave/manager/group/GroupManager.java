package com.example.leave.manager.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.repository.group.TeamGroupMemberRepository;
import com.example.leave.repository.group.TeamGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<TeamGroupMember> getApplicationToGroup(Account account) {
        System.out.println("4 " +account.getName());
        TeamGroup teamGroup=teamGroupRepository.findOneByAccount(account);
        System.out.println("5 "+ teamGroup.getId()+" "+teamGroup.getTitle());
        List<TeamGroupMember> teamGroupMemberList=teamGroupMemberRepository.findAllByTeamGroupIDAndActive(teamGroup, false);
        System.out.println("6 "+ teamGroupMemberList.size());
        for(TeamGroupMember teamGroupMember : teamGroupMemberList)
            System.out.println(teamGroupMember.getEmployee().getName());
        System.out.println("7");
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
    public List<TeamGroupMember> getMemberInGroup(String titleGroup) {
        TeamGroup teamGroup=teamGroupRepository.findOneByTitle(titleGroup);
        return teamGroupMemberRepository.findAllByTeamGroupIDAndActive(teamGroup, true);
    }
}
