package com.example.leave.controler.account;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String index(Model model) {
        System.out.println("wszedłem");
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername();
            return "account/index";
        }catch(Exception e) {
            return "account/login";
        }
    }
}
