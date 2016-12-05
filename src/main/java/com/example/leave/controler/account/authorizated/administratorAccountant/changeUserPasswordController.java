package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.endpoint.account.AccountEndpoint;
import jdk.nashorn.internal.parser.JSONParser;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Medion on 2016-09-18.
 */
@Controller
public class changeUserPasswordController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    public @ResponseBody String changeUserPasswordGet(@RequestBody String login) {
        accountEndpoint.setAccountToEdit(login);
        return JSONParser.quote("/changeUserPassword");
    }

    @RequestMapping(value = "/saveUserPassword", method = RequestMethod.POST)
    public @ResponseBody String saveUserPassword(@RequestBody List<String> data) {
        accountEndpoint.changeUserPassword(data.get(0), data.get(1));
        return JSONParser.quote("Password was changed!");
    }

    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.GET)
    public String editUserAccountGet() {
        return "account/authorizated/administratorAccountant/changeUserPassword";
    }

}

