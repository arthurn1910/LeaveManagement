package com.example.leave.manager.group;

import com.example.leave.entity.group.TeamGroup;
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

    @Override
    public void createGroup(TeamGroup createGroupDTO) {
        createGroupDTO.setId(7L);
        teamGroupRepository.save(createGroupDTO);
    }

    @Override
    public List<TeamGroup> getAllGroups() {
        return (List<TeamGroup>) teamGroupRepository.findAll();
    }
}
