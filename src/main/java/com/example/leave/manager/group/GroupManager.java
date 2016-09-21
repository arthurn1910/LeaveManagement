package com.example.leave.manager.group;

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
}
