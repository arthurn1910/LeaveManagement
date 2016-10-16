package com.example.leave.controler.account;
import com.example.leave.dto.account.AuthDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.AccessLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class HomeController {
    @Autowired
    AccountEndpoint accountEndpoint;
    @RequestMapping("/home")
    public String index(Model model) {
        return "account/home";
    }

    @RequestMapping("/aaa")
    public String aaa(Model model) {
        return "account/aaa";
    }

    @RequestMapping("/isAuthenticated")
    public  @ResponseBody AuthDTO isAuthenticated()   {
        //
        // Code processing the input parameters
        //
        System.out.println("111wszed");
        AuthDTO authDTO=new AuthDTO();
        if(accountEndpoint.isAuthenticated()) {
            authDTO.setLogin(accountEndpoint.getAccount().getLogin());
            for(AccessLevel accessLevel :accountEndpoint.getAccount().getAccessLevelCollection()){
                if(accessLevel.getActive()==true) {
                    switch(accessLevel.getLevel()){
                        case "ROLE_ADMINISTRATOR":
                            authDTO.setRoleAdministrator(true);
                            break;
                        case "ROLE_EMPLOYEE":
                            authDTO.setRoleEmployee(true);
                            break;
                        case "ROLE_ACCOUNTANT":
                            authDTO.setRoleAccountant(true);
                            break;
                        case "ROLE_MANAGER":
                            authDTO.setRoleManager(true);
                            break;
                    }
                }
            }
        }

        System.out.println("!!!!!!!! "+authDTO.getLogin());
        System.out.println("!!!!!!!! "+authDTO.getAuthenticated());
        return authDTO;
    }
}
