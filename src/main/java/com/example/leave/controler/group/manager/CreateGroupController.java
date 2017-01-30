package com.example.leave.controler.group.manager;

import com.example.leave.endpoint.group.GroupEndpoint;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Medion on 2016-09-20.
 */
@Controller
public class CreateGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/getCreateGroup", method = RequestMethod.GET)
    public @ResponseBody String getCreateGroup() {
        return JSONParser.quote("/createGroup");
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public String createGroup() {
        return "group/manager/createGroup";
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public @ResponseBody String createGroupPost(@RequestBody List<String> data) {
        try{
            groupEndpoint.createGroup(data);
            return JSONParser.quote("Grupa stworzona");
        } catch(Exception e){
            return JSONParser.quote("Grupa nie zostala stworzona");
        }
    }
}
