package com.example.leave.controler.account.authorizated.administratorAccountant;

import com.example.leave.dto.account.AuthDTO;
import com.example.leave.dto.account.ListUsersDTO;
import com.example.leave.dto.account.UserDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getUsersListView() {
        return "account/authorizated/administratorAccountant/usersList";
    }

    @RequestMapping("/usersListData")
    public @ResponseBody ListUsersDTO userListData() {
        ListUsersDTO listUsersDTO=new ListUsersDTO();
        List<Account> userList=accountEndpoint.getUsers();
        List<UserDTO> listUserDTO = new ArrayList<>();
        UserDTO userDTO;
        for(Account account : userList) {
            userDTO=new UserDTO();
            userDTO.setAccount(account);
            listUserDTO.add(userDTO);
        }
        listUsersDTO.setAccountList(listUserDTO);
        for(UserDTO user: listUserDTO){
            System.out.print(user.toString());
        }
        System.out.println(listUserDTO.size());
        return listUsersDTO;
    }

    @RequestMapping("/changeUserActiveStatus")
    public @ResponseBody String changeUserActiveStatus(@RequestBody List<String> account) {
        accountEndpoint.changeUserActiveStatus(account.get(0), Long.parseLong(account.get(1)));
        return "true";
    }

    @RequestMapping("/changeUserConfirmStatus")
    public @ResponseBody String changeUserConfirmStatus(@RequestBody List<String> account) {
        accountEndpoint.changeUserConfirmStatus(account.get(0), Long.parseLong(account.get(1)));
        return "true";
    }


}
