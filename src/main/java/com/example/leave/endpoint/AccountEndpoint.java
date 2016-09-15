package com.example.leave.endpoint;

import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.Account;
import com.example.leave.manager.AccountManager;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountEndpoint implements AccountEndpointInterface {

    @Autowired
    AccountManager accountManager;


    public void registerAccount(RegisterDTO registerDTO) {
        accountManager.registerAccount(new Account(registerDTO.getLogin(),registerDTO.getPassword()));
    }
}
