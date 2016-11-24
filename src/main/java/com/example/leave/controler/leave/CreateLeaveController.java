package com.example.leave.controler.leave;

import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.endpoint.leave.LeaveEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-27.
 */
@Controller
public class CreateLeaveController {
        @Autowired
        LeaveEndpoint leaveEndpoint;

//        @RequestMapping(value = "/createLeave", method = RequestMethod.GET)
//        public String register(HttpServletRequest request, @ModelAttribute(value = "leaveDTO") @Valid LeaveDTO leaveDTO, BindingResult result) {
//            leaveDTO.setLeaveTypeList(leaveEndpoint.getListLeaveType());
//            return "leave/createLeave";
//        }

        @RequestMapping(value = "/createLeave", method = RequestMethod.POST)
        public String registerUser(HttpServletRequest request, @ModelAttribute(value = "leaveDTO") @Valid LeaveDTO leaveDTO, BindingResult result) {
            if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
                leaveEndpoint.createLeave(leaveDTO);
                return "account/index";
            } else {
                return "leave/createLeave";
            }
        }

}
