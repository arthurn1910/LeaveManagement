package com.example.leave.controler.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.endpoint.leave.LeaveEndpoint;
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
 * Created by arthurn on 04.12.16.
 */
@Controller
public class ReportLeaveController {
    @Autowired
    LeaveEndpoint leaveEndpoint;

    @RequestMapping(value = "/reportLeave", method = RequestMethod.GET)
    public String reportLeave() {
        return "leave/reportLeave";
    }

    @RequestMapping(value = "/getLeave", method = RequestMethod.GET)
    public @ResponseBody List<LeaveDTO> getLeave(){
        return leaveEndpoint.getAllLeave();
    }
}
