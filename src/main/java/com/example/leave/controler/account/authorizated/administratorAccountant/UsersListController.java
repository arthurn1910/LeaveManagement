package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.account.ListUsersDTO;
import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medion on 2016-09-17.
 */
@Controller
public class UsersListController {
    @Autowired
    AccountEndpoint accountEndpoint;

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public String getUsersList(HttpServletRequest request, @ModelAttribute(value = "listUsersDTO") @Valid ListUsersDTO listUsersDTO, BindingResult result) {
        List<Account> userList=accountEndpoint.getUsers();
        List<UserDTO> listUserDTO = new ArrayList<>();
        UserDTO userDTO;
        for(Account account : userList) {
            userDTO=new UserDTO();;
            userDTO.setAccount(account);
            listUserDTO.add(userDTO);
        }
        listUsersDTO.setAccountList(listUserDTO);
        return "account/authorizated/administratorAccountant/usersList";
    }

}
