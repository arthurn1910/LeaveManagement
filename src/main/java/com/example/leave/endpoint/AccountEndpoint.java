package com.example.leave.endpoint;

import com.example.leave.dto.UserDTO;
import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountEndpoint implements AccountEndpointInterface {

    private Account account;
    private AccessLevel accessLevel;

    public AccountEndpoint() {
        this.account = new Account();
        this.accessLevel = new AccessLevel();
    }

    @Autowired
    AccountManager accountManager;

    @Override
    public void registerAccount(RegisterDTO registerDTO) {
        this.account=new Account(registerDTO.getLogin(),registerDTO.getPassword());
        this.account.setUserData(registerDTO.getName(), registerDTO.getLastname(), registerDTO.getEmail());
        AccessLevel accessLevel=new AccessLevel("ROLE_EMPLOYEE",Boolean.TRUE, this.account);
        accountManager.registerAccount(this.account,accessLevel);
    }

    @Override
    public Account getYourAccount(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.account = accountManager.getAccount(user.getUsername());
        return  account;
    }

    @Override
    public Account getUserAccount(String login){
        this.account = accountManager.getAccount(login);
        return account;
    }

    @Override
    public void editYourAccountData(UserDTO AccountDTO) {
        this.account.setEmail(AccountDTO.getEmail());
        this.account.setName(AccountDTO.getName());
        this.account.setLastName(AccountDTO.getLastname());
        accountManager.editAccount(account);
    }

    @Override
    public void editUserAccount(UserDTO accountDTO) {
        this.account.setEmail(accountDTO.getEmail());
        this.account.setName(accountDTO.getName());
        this.account.setLastName(accountDTO.getLastname());
        this.account.setActive(account.getActive());//accountDTO.getActive());*******************
        this.account.setConfirm(account.getConfirm());//accountDTO.getConfirm());*****************
        this.account.setExpirienceDay(accountDTO.getExpirienceDay());
        this.account.setExpirienceMonth(accountDTO.getExpirienceMonth());
        this.account.setExpirienceYear(accountDTO.getExpirienceYear());
        this.account.setStartingDate(account.getStartingDate());
        accountManager.editAccount(account);
    }

    @Override
    public List<Account> getUsers() {
        List<Account> listUsers= accountManager.getUsers();
        return listUsers;
    }
}
