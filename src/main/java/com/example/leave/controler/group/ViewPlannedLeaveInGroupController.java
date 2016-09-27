package com.example.leave.controler.group;

import com.example.leave.dto.group.LeavePlannedInGroudDTO;
import com.example.leave.dto.group.ListImportantDatesDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-27.
 */
@Controller
public class ViewPlannedLeaveInGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/viewPlannedLeave", method = RequestMethod.GET)
    public String viewPlannedLeave(HttpServletRequest request, @ModelAttribute(value = "leavePlannedInGroudDTO") @Valid LeavePlannedInGroudDTO leavePlannedInGroudDTO, BindingResult result) {
        TeamGroup teamGroup=new TeamGroup();
        teamGroup.setId(1L);
        groupEndpoint.getTeamGroup(teamGroup.getId());
        leavePlannedInGroudDTO.setTeamGroup(groupEndpoint.getTeamGroup());
        leavePlannedInGroudDTO.setLeaveList(groupEndpoint.getAllLeavePlannedInGroup(groupEndpoint.getTeamGroup()));
        return "group/viewMemberLeaveInGroup";
    }

    @RequestMapping(value = "/viewPlannedLeave", method = RequestMethod.POST)
    public String rejectPlannedLeave(HttpServletRequest request, @ModelAttribute(value = "leavePlannedInGroudDTO") @Valid LeavePlannedInGroudDTO leavePlannedInGroudDTO, BindingResult result) {
        TeamGroup teamGroup=new TeamGroup();
        teamGroup.setId(1L);
        groupEndpoint.getTeamGroup(teamGroup.getId());
        leavePlannedInGroudDTO.setTeamGroup(groupEndpoint.getTeamGroup());
        leavePlannedInGroudDTO.setLeaveList(groupEndpoint.getAllLeavePlannedInGroup(groupEndpoint.getTeamGroup()));
        groupEndpoint.rejectPlannedLeave(leavePlannedInGroudDTO.getLeaveList().get(0));
        return "group/viewMemberLeaveInGroup";
    }
}
