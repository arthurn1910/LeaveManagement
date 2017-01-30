package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.Account;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Medion on 2016-09-17.
 */
@Controller
public class EditUserAccountController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.POST)
    public @ResponseBody String editUserAccountPost(@RequestBody String login) {
        accountEndpoint.setAccountToEdit(login);
        return JSONParser.quote("/editUserAccount");
    }

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.GET)
    public String editUserAccountGet() {
        return "account/authorizated/administratorAccountant/editUserAccount";
    }

    @RequestMapping("/getUserAccount")
    public @ResponseBody UserDTO editUserAccount() {
        UserDTO userDTO=new UserDTO();
        userDTO.setAccount(accountEndpoint.getAccountToEdit());
        return userDTO;
    }

    @RequestMapping(value = "/saveUserAccount", method = RequestMethod.POST)
    public @ResponseBody String editUserAccountData( @RequestBody List<String> data) {
        accountEndpoint.editUserAccount(data);
        return JSONParser.quote("Konto uzytkownika zmienione!");
    }
}
