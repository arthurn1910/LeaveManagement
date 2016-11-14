package com.example.leave.controler.group;

import com.example.leave.dto.group.ListGroupDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.group.UserGroupDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/getGroupList", method = RequestMethod.GET)
    public @ResponseBody String getgroupList() {
        return JSONParser.quote("/groupList");
    }

    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public String getUsersListView() {
        return "group/groupList";
    }

    @RequestMapping("/groupListData")
    public @ResponseBody List<TeamGroupDTO> groupListData() {
        List<TeamGroupDTO> groupList=groupEndpoint.getAllGroupsDTO();
        return groupList;
    }

    @RequestMapping("/getUserGroupDTO")
    public @ResponseBody UserGroupDTO getUserGroupDTO() {
        UserGroupDTO userGroupDTO=groupEndpoint.getUserGroup();
        return userGroupDTO;
    }
}
