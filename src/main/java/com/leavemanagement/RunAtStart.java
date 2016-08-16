package com.leavemanagement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Medion on 2016-08-15.
 */
@Component
public class RunAtStart {
    private final AccountRepository accountRepository;

    @Autowired
    public RunAtStart(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @PostConstruct
    public void runAtStart(){
        Account account =new Account();
        account.setActive(true);
        account.setConfirm(true);
        account.setLogin("apin");
        account.setVersion(0);
        account.setPassword("e6a52c828d56b46129fbf85c4cd164b3");
        account.setAccount_id(6L); // Jak chcesz miec longa a nie integer to mozesz robic liczbaL jak tu

        accountRepository.saveAndFlush(account); // and flush wymusza wyslanie zmian do bazy
    }
}
