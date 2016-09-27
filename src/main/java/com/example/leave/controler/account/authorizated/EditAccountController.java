package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.Account;
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
 * Created by Medion on 2016-09-04.
 */
@Controller
public class EditAccountController {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/editAccount", method = RequestMethod.GET)
    public String editAccount(HttpServletRequest request, @ModelAttribute(value = "accountDTO") @Valid UserDTO accountDTO, BindingResult result) {
        Account account=accountEndpoint.getYourAccount();
        accountDTO.setAccount(account);
        return "account/authorizated/editAccount";
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public String editAccountData(HttpServletRequest request, @ModelAttribute(value = "accountDTO") @Valid UserDTO accountDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            accountEndpoint.editYourAccountData(accountDTO);
            return "account/index";
        } else {
            for(ObjectError a:result.getAllErrors())
                System.out.println(a.toString());
            return "account/authorizated/editAccount";
        }
    }
}
