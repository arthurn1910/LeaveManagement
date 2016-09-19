package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.ChangePasswordDTO;
import com.example.leave.dto.UserDTO;
import com.example.leave.endpoint.AccountEndpoint;
import com.example.leave.entity.Account;
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
 * Created by Medion on 2016-09-18.
 */
@Controller
public class ChangePasswordController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String change(HttpServletRequest request, @ModelAttribute(value = "changePasswordDTO") @Valid ChangePasswordDTO changePasswordDTO, BindingResult result) {
        accountEndpoint.getYourAccount();
        return "account/authorizated/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, @ModelAttribute(value = "changePasswordDTO") @Valid ChangePasswordDTO changePasswordDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controller 1");
            try {
                accountEndpoint.changePassword(changePasswordDTO);
            } catch (Exception e) {
                System.out.println("Controller error");
                return "account/authorizated/changePassword";
            }
            System.out.println("Controller 2");
            return "account/index";
        }
        System.out.println("Controller 3");
        for(ObjectError o :result.getAllErrors())
            System.out.println("!!! "+o.toString());
        return "account/authorizated/changePassword";
    }
}