package com.example.leave.manager;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;

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

    void changeUserRole(Account account);
}
