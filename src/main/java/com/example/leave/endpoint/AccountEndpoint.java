package com.example.leave.endpoint;

import com.example.leave.dto.EditAccountDTO;
import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.manager.AccountManager;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

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
        System.out.println("Endpoint in");
        Account account=new Account(registerDTO.getLogin(),registerDTO.getPassword());
        account.setUserData(registerDTO.getName(), registerDTO.getLastname(), registerDTO.getEmail());
        AccessLevel accessLevel=new AccessLevel("ROLE_USER",Boolean.TRUE, account);
        accountManager.registerAccount(account,accessLevel);
        System.out.println("Endpoint out");
    }

    @Override
    public Account getYourAccountToEdit(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.account = accountManager.getAccount(user.getUsername());
        return  account;
    }

    @Override
    public Account getAccountToEdit(String login){
        this.account = accountManager.getAccount(login);
        return account;
    }

    @Override
    public void editYourAccountData(EditAccountDTO editAccountDTO) {
        System.out.println("Endpoint in");
        this.account.setEmail(editAccountDTO.getEmail());
        this.account.setName(editAccountDTO.getName());
        this.account.setLastName(editAccountDTO.getLastname());

        accountManager.editYourAccountData(account);
        System.out.println("Endpoint out");
    }
}
