package com.example.leave.controler.group;

import com.example.leave.dto.group.ListApplicationGroupDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by arthurn on 12.11.16.
 */
@Controller
public class ShowGroupController {

    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/getShowGroup", method = RequestMethod.GET)
    public @ResponseBody String getShowGroup() {
        return JSONParser.quote("/showGroup");
    }

    @RequestMapping(value = "/showGroup", method = RequestMethod.GET)
    public String showGroup() {
        return "group/showGroup";
    }

    @RequestMapping(value = "/showGroup", method = RequestMethod.POST)
    public @ResponseBody String showGroupPost(@RequestBody String id) {
        groupEndpoint.getTeamGroup(Long.parseLong(id));
        return JSONParser.quote("/showGroup");
    }

    @RequestMapping(value = "/getTeamGroup", method = RequestMethod.GET)
    public @ResponseBody TeamGroupDTO showGroupPost() {
        return groupEndpoint.getTeamGroupDTO();
    }

//    ***************************************************************

    @RequestMapping(value = "/listMemberGroup", method = RequestMethod.GET)
    public @ResponseBody ListApplicationGroupDTO getListMemberGroup() {
        List<TeamGroupMember> teamGroupMemberList=groupEndpoint.getMemberInGroup();
        ListApplicationGroupDTO listApplicationGroupDTO=new ListApplicationGroupDTO();
        listApplicationGroupDTO.setTeamGroupMemberList(teamGroupMemberList);
        return listApplicationGroupDTO;
    }

    @RequestMapping(value = "/listMemberGroupView", method = RequestMethod.GET)
    public String listMemberGroup() {
        return "group/listMemberGroup";
    }


    @RequestMapping(value = "/removeMember", method = RequestMethod.POST)
    public @ResponseBody String removeMember(@RequestBody List<String> data) {
        groupEndpoint.removeMember(data);
        return JSONParser.quote("Member "+data.get(1)+" removed");
    }
}
