package com.example.leave.endpoint.group;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.entity.group.TeamGroup;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupEndpointInterface {
    void createGroup(TeamGroupDTO createGroupDTO);

    List<TeamGroup> getAllGroups();
}
