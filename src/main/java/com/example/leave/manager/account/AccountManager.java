package com.example.leave.manager.account;

import com.example.leave.controler.account.IndexController;
import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import com.example.leave.repository.account.AccessLevelRepository;
import com.example.leave.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountManager implements AccountManagerInterface {

    Logger log = Logger.getLogger(AccountManager.class.getName());
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccessLevelRepository accessLevelRepository;

    @Override
    @Transactional
    public void registerAccount(Account account, AccessLevel accessLevel){
        account.setId(accountRepository.getNewID());
        accessLevel.setId(accessLevelRepository.getNewAccessLevelID());
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
        Account accountTmp = accountRepository.findByLogin(account.getLogin());
        if(accessLevelRepository.findByLevelAndAccount(role, accountTmp).size()==0) {
            AccessLevel accessLevel = new AccessLevel(role, true, accountTmp);
            accessLevel.setId(accessLevelRepository.getNewAccessLevelID());
            accessLevelRepository.save(accessLevel);
        } else{
            log.warning("Exception add role "+ role+ "to user"+account.getLogin());
        }

    }

    @Override
    @Transactional
    public void removeRoleFromUser(String role, Account account) {
        Account accountTmp = accountRepository.findByLogin(account.getLogin());
        List<AccessLevel> accessLevelList=accessLevelRepository.findByLevelAndAccount(role, accountTmp);
        if(accessLevelList.size()!=0) {
            for (AccessLevel accessLevel : accessLevelList) {
                accessLevelRepository.removeLevel(accessLevel.getId());
            }
        } else{
            log.warning("Exception add role "+ role+ "to user"+account.getLogin());
        }
    }
}
