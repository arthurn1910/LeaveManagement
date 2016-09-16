package com.example.leave.controler.account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class IndexControler {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "ja!!!!!!!!!!!");
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername(); //get logged in username
            String role="";
            for(GrantedAuthority auth : user.getAuthorities()) {
                System.out.println(auth.getAuthority());
                role=auth.getAuthority();
            }

            model.addAttribute("user", name);
            model.addAttribute("role", role);
        }catch(Exception ex){
            System.out.println("11!!!!!!!!!!!!!!!brak użytkownika");
            model.addAttribute("user", "brak");
            model.addAttribute("role", "brak");
        }
        return "account/index";
    }
}
