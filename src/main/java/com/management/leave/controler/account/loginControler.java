package com.management.leave.controler.account;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class loginControler {
    @RequestMapping("/login")
    public String index(Model model) {
        return "account/login";
    }
}
