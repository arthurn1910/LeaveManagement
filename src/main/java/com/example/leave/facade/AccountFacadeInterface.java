package com.example.leave.facade;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;

/**
 * Created by Medion on 2016-09-14.
 */
public interface AccountFacadeInterface {
    void registerAccount(Account account, AccessLevel accessLevel);

    Account getAccount(String login);

    void editYourAccountData(Account account);
}
