package com.example.leave.controler.group.manager;

import com.example.leave.endpoint.group.GroupEndpoint;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by arthurn on 19.11.16.
 */
@Controller
public class GroupLeaveController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/getGroupLeave", method = RequestMethod.POST)
    public @ResponseBody String createImportantDate(@RequestBody String date) {
        return JSONParser.quote("getGroupLeave");
    }

    @RequestMapping(value = "/groupApprovedLeave", method = RequestMethod.GET)
    public String approvedLeave() {
        return "group/groupApprovedLeaveController";
    }
}
