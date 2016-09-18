package com.example.leave.manager;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.facade.AccountFacade;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountManager implements AccountManagerInterface {
    @Autowired
    AccountFacade accountFacade;

    @Override
    public void registerAccount(Account account, AccessLevel accessLevel){
        accountFacade.registerAccount(account, accessLevel);
    }

    @Override
    public Account getAccount(String login){
        return accountFacade.getAccount(login);
    }

    @Override
    public void editAccount(Account account) {
        accountFacade.editYourAccountData(account);
    }

    @Override
    public List<Account> getUsers() {
        return accountFacade.getUsers();
    }

    @Override
    public void changePassword(Account account) {
        accountFacade.changePassword(account);
    }

    @Override
    public void changeUserRole(Account account) {
        accountFacade.changeUserRole(account);
    }
}
