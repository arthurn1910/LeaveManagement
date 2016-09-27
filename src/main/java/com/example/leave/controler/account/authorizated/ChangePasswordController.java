package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.account.ChangePasswordDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
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
            try {
                accountEndpoint.changePassword(changePasswordDTO);
            } catch (Exception e) {
                return "account/authorizated/changePassword";
            }
            return "account/index";
        }
        for(ObjectError o :result.getAllErrors())
            System.out.println("!!! "+o.toString());
        return "account/authorizated/changePassword";
    }
}