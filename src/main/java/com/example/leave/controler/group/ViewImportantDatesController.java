package com.example.leave.controler.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.ListImportantDatesDTO;
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
public class ViewImportantDatesController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/viewImportantDates", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "listImportantDatesDTO") @Valid ListImportantDatesDTO listImportantDatesDTO, BindingResult result) {
        TeamGroup teamGroup=new TeamGroup();
        teamGroup.setId(1L);
        groupEndpoint.getTeamGroup(teamGroup.getId());
        listImportantDatesDTO.setTeamGroup(groupEndpoint.getTeamGroup());
        listImportantDatesDTO.setImportantDatesList(groupEndpoint.getImportantDates(teamGroup));
        return "group/viewImportantDates";
    }

    @RequestMapping(value = "/viewImportantDates", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "listImportantDatesDTO") @Valid ListImportantDatesDTO listImportantDatesDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            TeamGroup teamGroup=new TeamGroup();
            teamGroup.setId(1L);
            listImportantDatesDTO.setImportantDatesList(groupEndpoint.getImportantDates(teamGroup));
            groupEndpoint.removeImportantDate(listImportantDatesDTO.getImportantDatesList().get(0));
            return "account/index";
        } else {
            for(ObjectError o: result.getAllErrors())
                System.out.println(o.toString());
            return "group/viewImportantDates";
        }
    }
}
