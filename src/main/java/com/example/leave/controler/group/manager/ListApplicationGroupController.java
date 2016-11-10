package com.example.leave.controler.group.manager;

import com.example.leave.dto.group.ListApplicationGroupDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroupMember;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Medion on 2016-09-23.
 */
@Controller
public class ListApplicationGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/listofapplication", method = RequestMethod.GET)
    public String getListApplication(HttpServletRequest request, @ModelAttribute(value = "listApplicationGroupDTO") @Valid ListApplicationGroupDTO listApplicationGroupDTO, BindingResult result) {
        List<TeamGroupMember> teamGroupMemberList=groupEndpoint.getTeamGroupUser(false);
        listApplicationGroupDTO.setTeamGroupMemberList(teamGroupMemberList);

        return "group/manager/listOfApplication";
    }

    public void acceptApplication(TeamGroupMember teamGroupMember){
        groupEndpoint.acceptApplication(teamGroupMember);
    }

    public void rejectApplication(TeamGroupMember teamGroupMember){
        groupEndpoint.rejectApplication(teamGroupMember);
    }

    @RequestMapping(value = "/listofapplication", method = RequestMethod.POST)
    public String getListApplicationPOST(HttpServletRequest request, @ModelAttribute(value = "listApplicationGroupDTO") @Valid ListApplicationGroupDTO listApplicationGroupDTO, BindingResult result) {
        List<TeamGroupMember> teamGroupMemberList=groupEndpoint.getTeamGroupUser(false);
        listApplicationGroupDTO.setTeamGroupMemberList(teamGroupMemberList);
        acceptApplication(teamGroupMemberList.get(0));
        rejectApplication(teamGroupMemberList.get(1));
        return "index";
    }


}
