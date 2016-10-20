package com.example.leave.controler.account;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.dto.account.RegisterDTO;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.jni.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Medion on 2016-09-04.
 */
@Controller
public class RegisterController {

    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUserGet(@RequestBody RegisterDTO registerDTO) {
        System.out.println("register");
        System.out.println("register "+registerDTO.getLogin());

        try {
            accountEndpoint.registerAccount(registerDTO);
            return "account/index";
        } catch(Exception e){
            return "account/register";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUserPost() {
        return "account/register";
    }

}
