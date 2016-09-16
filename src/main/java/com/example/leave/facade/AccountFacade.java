package com.example.leave.facade;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.repository.AccessLevelRepository;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountFacade implements AccountFacadeInterface {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccessLevelRepository accessLevelRepository;

    @Override
    public void registerAccount(Account account, AccessLevel accessLevel){
        System.out.println("F1");
        account.setId(accountRepository.getNewID()+1);
        System.out.println("f2");
        accessLevel.setId(accessLevelRepository.getNewAccessLevelID()+1);
        System.out.println("f3");
        accountRepository.save(account);
        System.out.println("f4");
        accessLevel.setAccount(account);
        accessLevelRepository.save(accessLevel);
        System.out.println("f5");


    }
}
