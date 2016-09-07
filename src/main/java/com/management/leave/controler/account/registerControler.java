package com.management.leave.controler.account;

import com.management.leave.model.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class registerControler {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            //formularz nie jest uzupełniony prawidłowo
            System.out.println("3311*****************");
            System.out.println(registerDTO.getName());
            System.out.println(registerDTO.getEmail());
            return "account/index";
        } else {
            //formularz wypełniony prawidłowo
            System.out.println("2211!!!!!!!!!!!!!*****************");
            System.out.println();

            for(ObjectError a : result.getAllErrors())
                System.out.println(a.toString());
            return "account/register";
        }
    }

//    @RequestMapping(value = "/register")
//    public String register(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
//        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
//            //formularz nie jest uzupełniony prawidłowo
//            System.out.println("*****************");
//            System.out.println(registerDTO.getName());
//            System.out.println(registerDTO.getEmail());
//            return "account/index";
//        } else {
//            //formularz wypełniony prawidłowo
//            System.out.println("!!!!!!!!!!!!!*****************");
//            System.out.println();
//            return "account/register";
//        }
//    }


}
