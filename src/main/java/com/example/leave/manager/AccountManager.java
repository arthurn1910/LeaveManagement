package com.example.leave.manager;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.facade.AccountFacade;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountManager implements AccountManagerInterface {
    @Autowired
    AccountFacade accountFacade;

    @Override
    public void registerAccount(Account account, AccessLevel accessLevel){
        System.out.println("Manager in");
        accountFacade.registerAccount(account, accessLevel);
        System.out.println("Manager out");
    }

    @Override
    public Account getAccount(String login){
        return accountFacade.getAccount(login);
    }

    @Override
    public void editYourAccountData(Account account) {
        System.out.println("Manager in");
        accountFacade.editYourAccountData(account);
        System.out.println("Manager out");
    }
}
