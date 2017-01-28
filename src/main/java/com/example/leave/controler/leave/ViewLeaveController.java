package com.example.leave.controler.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.endpoint.leave.LeaveEndpoint;
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
public class ViewLeaveController {
    @Autowired
    LeaveEndpoint leaveEndpoint;

    @RequestMapping(value = "/viewLeave", method = RequestMethod.GET)
    public String viewLeave() {
        return "leave/leaveView";
    }

    @RequestMapping(value = "/removeLeave", method = RequestMethod.POST)
    public @ResponseBody String rejectApplication(@RequestBody String id){
        leaveEndpoint.removeLeave(Long.valueOf(id));
        return JSONParser.quote("Urlop został usunięty");
    }

    @RequestMapping(value = "/getYourLeave", method = RequestMethod.GET)
    public @ResponseBody List<LeaveDTO> getYourLeave(){
        return leaveEndpoint.getYourLeave();
    }
}
