package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.ChangeUserPasswordDTO;
import com.example.leave.dto.ChangeUserRoleDTO;
import com.example.leave.endpoint.AccountEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-19.
 */
@Controller
public class changeUserRole {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
    public String change(HttpServletRequest request, @ModelAttribute(value = "changeUserRoleDTO") @Valid ChangeUserRoleDTO changeUserRoleDTO, BindingResult result) {
        changeUserRoleDTO.setRole(accountEndpoint.getUserAccount("manager"));
        System.out.println(changeUserRoleDTO.getRoleAccountant());
        System.out.println(changeUserRoleDTO.getRoleAdministrator());
        System.out.println(changeUserRoleDTO.getRoleEmployee());
        System.out.println(changeUserRoleDTO.getRoleManager());
        changeUserRoleDTO.setLogin(accountEndpoint.getAccount().getLogin());
        changeUserRoleDTO.setRole(accountEndpoint.getAccount());
        return "account/authorizated/administratorAccountant/changeUserRole";
    }

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.POST)
    public String changeUserRole(HttpServletRequest request, @ModelAttribute(value = "changeUserRoleDTO") @Valid ChangeUserRoleDTO changeUserRoleDTO, BindingResult result) {
        System.out.println(changeUserRoleDTO.getRoleAccountant());
        System.out.println(changeUserRoleDTO.getRoleAdministrator());
        System.out.println(changeUserRoleDTO.getRoleEmployee());
        System.out.println(changeUserRoleDTO.getRoleManager());
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controller 1");
            accountEndpoint.changeUserRole(changeUserRoleDTO);
            System.out.println("Controller 2");
            return "account/index";
        }
        System.out.println("Controller 3");
        for(ObjectError o :result.getAllErrors())
            System.out.println("!!! "+o.toString());
        return "account/authorizated/administratorAccountant/changeUserRole";
    }
}
