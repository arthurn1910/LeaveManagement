package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.account.ChangePasswordDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
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
 * Created by Medion on 2016-09-18.
 */
@Controller
public class ChangePasswordController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public @ResponseBody String changePasswordPost() {
        return JSONParser.quote("/changePassword");
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword() {
        return "account/authorizated/changePassword";
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    public @ResponseBody String savePassword(@RequestBody List<String> password) {
        try {
            accountEndpoint.changePassword(password);
            return JSONParser.quote("Password Changed!");
        } catch (Exception e) {
            return JSONParser.quote("Actual Password Incorect!");
        }

    }

}