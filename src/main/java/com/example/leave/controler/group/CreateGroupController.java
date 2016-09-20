package com.example.leave.controler.group;

import com.example.leave.dto.account.RegisterDTO;
import com.example.leave.dto.group.CreateGroupDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.endpoint.group.GroupEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-20.
 */
@Controller
public class CreateGroupController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "teamGroupDTO") @Valid TeamGroupDTO teamGroupDTO, BindingResult result) {
        return "group/manager/createGroup";
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "teamGroupDTO") @Valid TeamGroupDTO teamGroupDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            groupEndpoint.createGroup(teamGroupDTO);
            return "account/index";
        } else {
            return "group/manager/createGroup";
        }
    }
}
