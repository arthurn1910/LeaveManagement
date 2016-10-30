package com.example.leave.controler.account.authorizated;

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
 * Created by Medion on 2016-09-04.
 */
@Controller
public class EditAccountController {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/editAccountGet", method = RequestMethod.GET)
    public @ResponseBody String editUserAccountPost() {
        System.out.println("editAccountGet");
        return JSONParser.quote("/editAccount");
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.GET)
    public String editAccount() {
        System.out.println("editAccount");
        return "account/authorizated/editAccount";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public @ResponseBody String editUserAccountData( @RequestBody List<String> data) {
        System.out.println("@@ "+ data.toString());
        accountEndpoint.editYourAccountData(data);
        return JSONParser.quote("Account changed!");
    }
}
