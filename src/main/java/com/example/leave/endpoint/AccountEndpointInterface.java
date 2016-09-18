package com.example.leave.endpoint;

import com.example.leave.dto.UserDTO;
import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.Account;

import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
public interface AccountEndpointInterface {

    void registerAccount(RegisterDTO registerDTO);

    Account getYourAccount();

    Account getUserAccount(String login);

    void editYourAccountData(UserDTO userDto);

    void editUserAccount(UserDTO userDto);

    List<Account> getUsers();
}
