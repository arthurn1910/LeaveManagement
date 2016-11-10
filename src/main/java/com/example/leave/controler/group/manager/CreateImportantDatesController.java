package com.example.leave.controler.group.manager;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-25.
 */
@Controller
public class CreateImportantDatesController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/createImportantDates", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "importantDateDTO") @Valid ImportantDateDTO importantDateDTO, BindingResult result) {
        TeamGroup teamGroup=new TeamGroup();
        teamGroup.setId(1L);
        groupEndpoint.setTeamGroup(teamGroup);
        importantDateDTO.setTeamGroup(groupEndpoint.getTeamGroup());
        return "group/manager/createImportantDates";
    }

    @RequestMapping(value = "/createImportantDates", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "importantDateDTO") @Valid ImportantDateDTO importantDateDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            groupEndpoint.createImportantDate(importantDateDTO);
            return "account/index";
        } else {
//            TeamGroup teamGroup=new TeamGroup();
//            teamGroup.setId(1L);
//            groupEndpoint.setTeamGroup(teamGroup);
//            importantDateDTO.setTeamGroup(groupEndpoint.getTeamGroup());
            return "group/manager/createImportantDates";
        }
    }
}