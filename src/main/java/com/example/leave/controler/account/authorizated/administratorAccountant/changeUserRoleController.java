package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.account.ChangeUserRoleDTO;
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
 * Created by Medion on 2016-09-19.
 */
@Controller
public class changeUserRoleController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value="/getUserRole", method=RequestMethod.GET)
    public @ResponseBody List<String> getUserRole (){
        return accountEndpoint.getUserRoleToEdit();

    }

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.GET)
    public String changeUserRoleGet(){
        return "account/authorizated/administratorAccountant/changeUserRole";
    }

    @RequestMapping(value = "/changeUserRole", method = RequestMethod.POST)
    public @ResponseBody String changeUserRolePost(@RequestBody String login) {
        accountEndpoint.setAccountToEdit(login);
        return JSONParser.quote("/changeUserRole");
    }

    @RequestMapping(value = "/setUserRole", method = RequestMethod.POST)
    public @ResponseBody String changeUserRolePost(@RequestBody List<String> data) {
        accountEndpoint.changeUserRole(data);
        return JSONParser.quote("Rola "+data.get(2)+" zmieniona!");
    }
}
