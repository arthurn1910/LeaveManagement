package com.example.leave.facade;

import com.example.leave.entity.Account;
import com.example.leave.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountFacade implements AccountFacadeInterface {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void registerAccount(Account account) {
        account.setId(accountRepository.getNewID()+1);
        accountRepository.save(account);
    }
}
