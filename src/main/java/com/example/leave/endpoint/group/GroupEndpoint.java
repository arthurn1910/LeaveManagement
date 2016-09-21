package com.example.leave.endpoint.group;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.manager.group.GroupManager;
import com.example.leave.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
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
        teamGroupTmp.setCreateDate(new Date());
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
}
