package com.example.leave.manager.account;

import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;

import java.util.List;
//import com.example.leave.entity.UserData;

/**
 * Created by Medion on 2016-09-14.
 */
public interface AccountManagerInterface {
    void registerAccount(Account account, AccessLevel accessLevel);

    Account getAccount(String login);

    void editAccount(Account account);

    List<Account> getUsers();

    void changePassword(Account account);

    void addRoleToUser(String role, Account account);

    void removeRoleFromUser(String role, Account account);
}
