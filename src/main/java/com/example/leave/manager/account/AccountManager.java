package com.example.leave.manager.account;

import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import com.example.leave.repository.account.AccessLevelRepository;
import com.example.leave.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountManager implements AccountManagerInterface {

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
        return accountRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void editAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public List<Account> getUsers() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    @Transactional
    public void changePassword(Account account) {
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void addRoleToUser(String role, Account account) {
        AccessLevel accessLevel=new AccessLevel(role, true, account);
        accessLevel.setId(2L);
        accessLevelRepository.save(accessLevel);
    }

    @Override
    @Transactional
    public void removeRoleFromUser(String role, Account account) {
        Collection<AccessLevel> accessLevelCollection=account.getAccessLevelCollection();
        for(AccessLevel accessLevel : accessLevelCollection)
            if(accessLevel.getLevel().equals(role))
                accessLevelRepository.delete(accessLevel);
    }
}
