package com.example.leave.manager.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface GroupManagerInterface {
    void createGroup(TeamGroup createGroupDTO);

    List<TeamGroup> getAllGroups();

    TeamGroup getTeamGroup(Long id);

    void joinToGroup(TeamGroupMember teamGroupMember);

    List<TeamGroupMember> getApplicationToGroup(Account account);

    void acceptApplication(TeamGroupMember teamGroupMember);

    void rejectApplication(TeamGroupMember teamGroupMember);

    void removeMember(TeamGroupMember teamGroupMember);

    List<TeamGroupMember> getMemberInGroup(String titleGroup);
}
