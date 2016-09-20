package com.example.leave.controler.group;

import com.example.leave.dto.group.ListGroupDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medion on 2016-09-21.
 */

@Controller
public class GroupListController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public String getUsersList(HttpServletRequest request, @ModelAttribute(value = "listGroupDTO") @Valid ListGroupDTO listGroupDTO, BindingResult result) {
        List<TeamGroup> groupList=groupEndpoint.getAllGroups();
        List<TeamGroupDTO> teamGroupList = new ArrayList<>();
        TeamGroupDTO teamGroupDTO;
        for(TeamGroup teamGroup : groupList) {
            teamGroupDTO=new TeamGroupDTO();;
            teamGroupDTO.setTeamGroup(teamGroup);
            teamGroupList.add(teamGroupDTO);
        }
        for(TeamGroupDTO teamGroupDTO1 :teamGroupList){
            System.out.println(teamGroupDTO1.getTitle());
            System.out.println("!!!!!  "+teamGroupDTO1.getNumberOfEmployee() );
        }
        listGroupDTO.setTeamGroupList(teamGroupList);
        return "group/groupList";
    }
}
