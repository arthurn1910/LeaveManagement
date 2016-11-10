package com.example.leave.dto.group;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Medion on 2016-09-20.
 */
public class TeamGroupDTO {
    private Long ID;

    @NotNull
    @NotEmpty
    @Size(min=2)
    private String title;

    private UserDTO manager;

    private Date createDate;

    private int numberOfEmployee;

    public TeamGroupDTO() {
    }

    public TeamGroupDTO(String title, Account manager, Date createDate, int numberOfEmployee) {
        this.title = title;
        this.manager = new UserDTO();
        this.manager.setAccount(manager);
        this.createDate = createDate;
        this.numberOfEmployee = numberOfEmployee;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getManager() {
        return manager;
    }

    public void setManager(UserDTO manager) {
        this.manager = manager;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(int numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.ID = teamGroup.getId();
        this.title = teamGroup.getGroupTitle();
        this.manager =  new UserDTO();
        this.manager.setAccount(teamGroup.getManager());
        this.createDate = teamGroup.getCreateDate();
        this.numberOfEmployee = teamGroup.getTeamGroupMembers().size();
    }

    public TeamGroupDTO(TeamGroup teamGroup) {
        this.ID = teamGroup.getId();
        this.title = teamGroup.getTitle();
        this.manager = new UserDTO();
        this.manager.setAccount(teamGroup.getManager());
        this.createDate = teamGroup.getCreateDate();
        this.numberOfEmployee = teamGroup.getTeamGroupMembers().size();
    }
}
