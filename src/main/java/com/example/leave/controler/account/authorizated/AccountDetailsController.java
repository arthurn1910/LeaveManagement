package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-17.
 */
@Controller
public class AccountDetailsController {

        @Autowired
        AccountEndpoint accountEndpoint;

        @RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
        public String AccountDetails(HttpServletRequest request, @ModelAttribute(value = "accountDTO") @Valid UserDTO accountDTO, BindingResult result) {
            accountDTO.setAccount(accountEndpoint.getYourAccount());
            System.out.println("!!!!!!!!!!!"+accountDTO.getName());
            return "account/authorizated/accountDetails";
        }
}
