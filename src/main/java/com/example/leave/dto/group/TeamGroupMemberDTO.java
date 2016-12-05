package com.example.leave.dto.group;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;

/**
 * Created by arthurn on 12.11.16.
 */
public class TeamGroupMemberDTO {
    private Long id;
    private UserDTO employee;
    private TeamGroupDTO teamGroup;
    private Boolean active;
    private long version;

    public TeamGroupMemberDTO() {
    }

    public TeamGroupMemberDTO(Long id, UserDTO employee, TeamGroupDTO teamGroup, Boolean active, long version) {
        this.id = id;
        this.employee = employee;
        this.teamGroup = teamGroup;
        this.active = active;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getEmployee() {
        return employee;
    }

    public void setEmployee(UserDTO employee) {
        this.employee = employee;
    }

    public TeamGroupDTO getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroupDTO teamGroup) {
        this.teamGroup = teamGroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setUserDTO(Account account){
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(account);
        this.employee=userDTO;
    }

    public void setTeamGroupDTO(TeamGroup teamGroup){
        TeamGroupDTO teamGroupDTO=new TeamGroupDTO(teamGroup);
        this.teamGroup=teamGroupDTO;
    }

    public void setTeamGroupMember(TeamGroupMember teamGroupMember){
        this.id = teamGroupMember.getId();
        this.active = teamGroupMember.getActive();
        this.version = teamGroupMember.getVersion();
        setUserDTO(teamGroupMember.getEmployee());
        setTeamGroupDTO(teamGroupMember.getTeamGroup());
    }
}
