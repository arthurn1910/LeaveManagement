package com.example.leave.controler.account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("wszedlem");

        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername(); //get logged in username
            System.out.println("!!!!! "+name);
            String role="";
            for(GrantedAuthority auth : user.getAuthorities()) {
                role+=auth.getAuthority()+" ";
            }

            model.addAttribute("user", name);
            model.addAttribute("role", role);
        }catch(Exception ex){
            model.addAttribute("user", "brak");
            model.addAttribute("role", "brak");
        }
        return "account/index";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
