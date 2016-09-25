package com.example.leave.controler.group;

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
 * Created by Medion on 2016-09-24.
 */
@Controller
public class ListMemberGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/listMemberGroup", method = RequestMethod.GET)
    public String getListMemberGroup(HttpServletRequest request, @ModelAttribute(value = "listApplicationGroupDTO") @Valid ListApplicationGroupDTO listApplicationGroupDTO, BindingResult result) {
        List<TeamGroupMember> teamGroupMemberList=groupEndpoint.getMemberInGroup("New Dev");
        listApplicationGroupDTO.setTeamGroupMemberList(teamGroupMemberList);

        return "group/manager/listOfApplication";
    }

    public void removeMember(TeamGroupMember teamGroupMember){
        groupEndpoint.removeMember(teamGroupMember);
    }


    @RequestMapping(value = "/listMemberGroup", method = RequestMethod.POST)
    public String getListMemberGroup1(HttpServletRequest request, @ModelAttribute(value = "listApplicationGroupDTO") @Valid ListApplicationGroupDTO listApplicationGroupDTO, BindingResult result) {
        List<TeamGroupMember> teamGroupMemberList=groupEndpoint.getMemberInGroup("New Dev");
        listApplicationGroupDTO.setTeamGroupMemberList(teamGroupMemberList);
        removeMember(teamGroupMemberList.get(0));
        return "/index";
    }
}
