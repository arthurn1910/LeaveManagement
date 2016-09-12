package com.management.leave.endpoint.account;

import com.management.leave.manager.account.AccountManager;
import com.management.leave.model.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Medion on 2016-09-07.
 */
@Component
public class AccountEndpoint implements AccountInterfaceEndpoint {

    @Autowired
    AccountManager accountManager;

    @Override
    public void registerAccount(RegisterDTO registerDTO) {
        System.out.println("Endpoint register in");
        accountManager.registerAccount(registerDTO);
        System.out.println("Endpoint register out");
    }
}
