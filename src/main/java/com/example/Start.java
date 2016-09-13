package com.example;

import com.example.Entity.Account;
import com.example.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Medion on 2016-09-12.
 */
@Component
public class Start {
    private final AccountRepository accountRepository;

    @Autowired
    public Start(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void start(){
        Account a=new Account("aaaeeea","bbbb");
        a.setActive(Boolean.FALSE);
        a.setConfirm(Boolean.FALSE);
        a.setVersion(0);
        a.setId(9L);
        accountRepository.save(a);
    }
}
