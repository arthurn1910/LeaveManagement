package com.example.leave.controler.account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class HomeControler {
    @RequestMapping("/home")
    public String index(Model model) {
        return "account/home";
    }
}
