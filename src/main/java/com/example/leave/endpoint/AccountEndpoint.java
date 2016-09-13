package com.example.leave.endpoint;

import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.Account;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
