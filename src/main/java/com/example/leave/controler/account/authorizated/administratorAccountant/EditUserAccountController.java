package com.example.leave.controler.account.authorizated.administratorAccountant;

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
 * Created by Medion on 2016-09-17.
 */
@Controller
public class EditUserAccountController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.GET)
    public String editUserAccount(HttpServletRequest request, @ModelAttribute(value = "accountDTO") @Valid UserDTO accountDTO, BindingResult result) {
        Account account=accountEndpoint.getUserAccount("manager");
        accountDTO.setAccount(account);
        return "account/authorizated/administratorAccountant/editUserAccount";
    }

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.POST)
    public String editUserAccountData(HttpServletRequest request, @ModelAttribute(value = "accountDTO") @Valid UserDTO accountDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            accountEndpoint.editUserAccount(accountDTO);
            return "account/index";
        }
        for(ObjectError a:result.getAllErrors())
            System.out.println(a.toString());
        return "account/authorizated/administratorAccountant/editUserAccount";
    }
}
