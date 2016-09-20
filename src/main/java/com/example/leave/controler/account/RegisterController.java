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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            accountEndpoint.registerAccount(registerDTO);
            return "account/index";
        } else {
            return "account/register";
        }
    }
}
