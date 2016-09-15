package com.example.leave.controler.account;
import com.example.leave.endpoint.AccountEndpoint;
import com.example.leave.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class RegisterControler {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controler register in");
            accountEndpoint.registerAccount(registerDTO);
            System.out.println("Controler register out");
            return "account/index";
        } else {
            for(ObjectError a:result.getAllErrors())
                System.out.println(a.toString());
            System.out.print(new Md5PasswordEncoder().encodePassword("zaq1@WSX",null));
            return "account/register";
        }
    }
}
