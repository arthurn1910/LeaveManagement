package com.example.leave.facade;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.repository.AccessLevelRepository;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
    @Transactional
    public void registerAccount(Account account, AccessLevel accessLevel){
        account.setId(accountRepository.getNewID()+1);
        accessLevel.setId(accessLevelRepository.getNewAccessLevelID()+1);
        accountRepository.save(account);
        accessLevel.setAccount(account);
        accessLevelRepository.save(accessLevel);
    }

    @Override
    @Transactional
    public Account getAccount(String login){
        Account account=accountRepository.findByLogin(login);
        return account;
    }

    @Override
    @Transactional
    public void editYourAccountData(Account account) {
        System.out.println("Facade in");
        accountRepository.save(account);
        System.out.println("Facade out");
    }
}
