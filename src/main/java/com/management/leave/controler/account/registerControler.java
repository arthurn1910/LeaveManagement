package com.management.leave.controler.account;

import com.management.leave.endpoint.account.AccountEndpoint;
import com.management.leave.entity.Account;
import com.management.leave.manager.account.AccountManager;
import com.management.leave.model.RegisterDTO;
import com.management.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class registerControler {

    @Autowired(required = true)
    AccountRepository accountRepository;
    //AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "registerDTO") @Valid RegisterDTO registerDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controler register in");
            //accountEndpoint.registerAccount(registerDTO);
//            Account a=new Account("asaa","cccc");
//            a.setVersion(0);
//            a.setConfirm(Boolean.FALSE);
//            a.setActive(Boolean.FALSE);
//            a.setId(8L);
//            accountRepository.save(a);
            System.out.println("Controler register out");
            return "account/index";
        } else {
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
