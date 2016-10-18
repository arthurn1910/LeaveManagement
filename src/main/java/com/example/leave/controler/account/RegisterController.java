package com.example.leave.controler.account;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.dto.account.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class RegisterController {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUserGet(@RequestBody RegisterDTO registerDTO) {
        accountEndpoint.registerAccount(registerDTO);
        return "account/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUserPost() {
        return "account/register";
    }

}
