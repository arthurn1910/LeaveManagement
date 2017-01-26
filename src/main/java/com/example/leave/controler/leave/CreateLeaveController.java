package com.example.leave.controler.leave;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.dto.leave.LeaveDetailsDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.endpoint.leave.LeaveEndpoint;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-27.
 */
@Controller
public class CreateLeaveController {
    @Autowired
    LeaveEndpoint leaveEndpoint;

    @RequestMapping(value = "/createLeave", method = RequestMethod.GET)
    public String viewLeave() {
        return "leave/createLeave";
    }

    @RequestMapping(value = "/getLeaveDetails", method = RequestMethod.GET)
    public @ResponseBody LeaveDetailsDTO getLeaveDetails() {
        return leaveEndpoint.getLeaveDetails();

    }

    @RequestMapping(value = "/createParentalLeave", method = RequestMethod.GET)
    public String createParentalLeave() {
        return "leave/createParentalLeave";
    }

    @RequestMapping(value = "/createParentalLeave", method = RequestMethod.POST)
    public @ResponseBody String createParentalLeave(@RequestBody List<String> data) {
        return leaveEndpoint.createParentalLeave(data);
    }

    @RequestMapping(value = "/createLeave", method = RequestMethod.POST)
    public @ResponseBody String createLeave(@RequestBody List<String> data) {
        String response = leaveEndpoint.createLeave(data);
        return JSONParser.quote(response);
    }

    @RequestMapping(value = "/getBlockDate", method = RequestMethod.GET)
    public @ResponseBody List<Date> getBlockDate() {
        return leaveEndpoint.getBlockDate();
    }

    @RequestMapping(value = "/createVacationLeave", method = RequestMethod.GET)
    public String createVacationLeave() {
        return "leave/createVacationLeave";
    }

    @RequestMapping(value = "/getCreateParentalLeave", method = RequestMethod.GET)
    public @ResponseBody String getCreateParentalLeave() {
        return JSONParser.quote("/createParentalLeave");
    }

    @RequestMapping(value = "/getCreateLeave", method = RequestMethod.GET)
    public @ResponseBody String getCreateLeave() {
        return JSONParser.quote("/createLeave");
    }

    @RequestMapping(value = "/getCreateVacationLeave", method = RequestMethod.GET)
    public @ResponseBody String getCreateVacationLeave() {
            return JSONParser.quote("/createVacationLeave");
        }
}
