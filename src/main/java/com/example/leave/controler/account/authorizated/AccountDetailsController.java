package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.account.ListUsersDTO;
import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medion on 2016-09-17.
 */
@Controller
public class AccountDetailsController {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
    public String GetUsersListView() {
        return "account/authorizated/accountDetails";
    }

    @RequestMapping("/getAccountDetails")
    public @ResponseBody UserDTO GetAccountDetails() {
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(accountEndpoint.getYourAccount());
        System.out.println("!@#$ "+userDTO.toString());
        return userDTO;
    }
}
