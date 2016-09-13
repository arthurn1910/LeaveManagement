package com.example.Endpoint.Account;

import com.example.DTO.RegisterDTO;
import com.example.Entity.Account;
import com.example.Manager.AccountManager;
import com.example.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountEndpoint implements AccountEndpointInterface {

//    @Autowired
//    AccountManager accountManager;
   // private AccountRepository accountRepository;

//    @Autowired
//    private AccountEndpoint(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }

    @Autowired
    AccountRepository accountRepository;


    public void registerAccount(RegisterDTO registerDTO) {
        Account a=new Account("aaaeeea","bbbb");
        a.setActive(Boolean.FALSE);
        a.setConfirm(Boolean.FALSE);
        a.setVersion(0);
        a.setId(10L);
        //accountRepository.save(a);
    }
}
