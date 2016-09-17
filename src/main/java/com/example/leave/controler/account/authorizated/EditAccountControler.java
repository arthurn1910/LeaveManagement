package com.example.leave.controler.account.authorizated;

import com.example.leave.dto.EditAccountDTO;
import com.example.leave.dto.RegisterDTO;
import com.example.leave.endpoint.AccountEndpoint;
import com.example.leave.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class EditAccountControler {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/editAccount", method = RequestMethod.GET)
    public String register(HttpServletRequest request, @ModelAttribute(value = "editAccountDTO") @Valid EditAccountDTO editAccountDTO, BindingResult result) {
        Account account=accountEndpoint.getAccountToEdit("manager");
        editAccountDTO.setAccountData(account);
        System.out.println(editAccountDTO.getName());
        return "account/authorizated/editAccount";
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @ModelAttribute(value = "editAccountDTO") @Valid EditAccountDTO editAccountDTO, BindingResult result) {
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            System.out.println("Controler register in");
            accountEndpoint.editYourAccountData(editAccountDTO);
            System.out.println("Controler register out");
            return "account/index";
        } else {
            for(ObjectError a:result.getAllErrors())
                System.out.println(a.toString());
            return "account/authorizated/editAccount";
        }
    }
}
