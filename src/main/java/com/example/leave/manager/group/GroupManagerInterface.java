package com.example.leave.manager.group;

import com.example.leave.entity.group.TeamGroup;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupManagerInterface {
    void createGroup(TeamGroup createGroupDTO);

    List<TeamGroup> getAllGroups();
}
