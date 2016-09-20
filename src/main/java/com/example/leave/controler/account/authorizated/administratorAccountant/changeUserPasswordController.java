package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.account.ChangeUserPasswordDTO;
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
public class changeUserPasswordController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.GET)
    public String change(HttpServletRequest request, @ModelAttribute(value = "changeUserPasswordDTO") @Valid ChangeUserPasswordDTO changeUserPasswordDTO, BindingResult result) {
        accountEndpoint.getUserAccount("manager");
        changeUserPasswordDTO.setLogin(accountEndpoint.getAccount().getLogin());
        System.out.println(changeUserPasswordDTO.getLogin()+"       "+accountEndpoint.getAccount().getLogin());
        return "account/authorizated/administratorAccountant/changeUserPassword";
    }

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    public String changeUserPassword(HttpServletRequest request, @ModelAttribute(value = "changeUserPasswordDTO") @Valid ChangeUserPasswordDTO changeUserPasswordDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controller 1");
            try {
                accountEndpoint.changeUserPassword(changeUserPasswordDTO);
            } catch (Exception e) {
                System.out.println("Controller error");
                return "account/authorizated/administratorAccountant/changeUserPassword";
            }
            System.out.println("Controller 2");
            return "account/index";
        }
        System.out.println("Controller 3");
        for(ObjectError o :result.getAllErrors())
            System.out.println("!!! "+o.toString());
        return "account/authorizated/administratorAccountant/changeUserPassword";
    }
}

