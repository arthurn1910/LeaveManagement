package com.example.leave.dto.group;

import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

/**
 * Created by arthurn on 09.11.16.
 */
public class UserGroupDTO {
    private String login;
    private boolean isManager;
    private List<TeamGroupDTO> teamGroupDTOList;
    private List<TeamGroupDTO> applyTeamGroupDTOList;

    public UserGroupDTO() {
        this.login = "";
        this.isManager = false;
        this.teamGroupDTOList = null ;
        this.applyTeamGroupDTOList = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public List<TeamGroupDTO> getTeamGroupDTOList() {
        return teamGroupDTOList;
    }

    public void setTeamGroupDTOList(List<TeamGroupDTO> teamGroupDTOList) {
        this.teamGroupDTOList = teamGroupDTOList;
    }

    public List<TeamGroupDTO> getApplyTeamGroupDTOList() {
        return applyTeamGroupDTOList;
    }

    public void setApplyTeamGroupDTOList(List<TeamGroupDTO> applyTeamGroupDTOList) {
        this.applyTeamGroupDTOList = applyTeamGroupDTOList;
    }

    public void setAccount(Account account){
        this.login=account.getLogin();
        setisManager(account.getAccessLevelCollection());
    }

    public void setisManager(Collection<AccessLevel> accessLevels){
        for(AccessLevel accesslevel : accessLevels){
            if(accesslevel.getLevel().equals("ROLE_MANAGER")){
                this.isManager=true;
            }
        }
    }
}
