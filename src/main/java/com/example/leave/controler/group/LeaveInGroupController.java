package com.example.leave.controler.group;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.leave.Leave;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by arthurn on 20.11.16.
 */
@Controller
public class LeaveInGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/memberLeaveInGroup", method = RequestMethod.GET)
    public String leaveInGroup() {
        return "group/viewMemberLeaveInGroup";
    }

    @RequestMapping(value = "/memberPlannedLeaveInGroup", method = RequestMethod.GET)
    public String memberPlannedLeaveInGroup() {
        return "group/manager/plannedLeave";
    }

    @RequestMapping(value = "/confirmLeave", method = RequestMethod.POST)
    public @ResponseBody String acceptApplication(@RequestBody String id){
        String response = groupEndpoint.confirmLeave(id);
        return JSONParser.quote(response);
    }

    @RequestMapping(value = "/rejectLeave", method = RequestMethod.POST)
    public @ResponseBody String rejectApplication(@RequestBody String id){
        groupEndpoint.rejectLeave(id);
        return JSONParser.quote("Urlop odmowiony");
    }

    @RequestMapping("/getLeaveInGroup")
    public @ResponseBody List<LeaveDTO> getLeaveInGroup() {
        List<LeaveDTO> groupList=groupEndpoint.getAllLeaveInGroup();
        return groupList;
    }
}
