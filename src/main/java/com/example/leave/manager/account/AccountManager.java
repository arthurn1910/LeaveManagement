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
            System.out.println("Exception addRoleToUser");
        }

    }

    @Override
    @Transactional
    public void removeRoleFromUser(String role, Account account) {
        Account accountTmp = accountRepository.findByLogin(account.getLogin());
        System.out.println("REmove ");
        List<AccessLevel> accessLevelList=accessLevelRepository.findByLevelAndAccount(role, accountTmp);
        System.out.println("REmove "+accessLevelList.toString());
        System.out.println("REmove "+ accountTmp.getLogin());
        if(accessLevelList.size()!=0) {
            System.out.println("REmove wszedl");
            for (AccessLevel accessLevel : accessLevelList) {
                System.out.println("REmove accessLevel" + accessLevel.getLevel()+ " id "+accessLevel.getId());
                accessLevelRepository.removeLevel(accessLevel.getId());
            }
        } else{
            System.out.println("Exception addRoleToUser");
        }
    }
}
