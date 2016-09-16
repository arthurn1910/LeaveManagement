package com.example.leave.manager;

import com.example.leave.entity.AccessLevel;
import com.example.leave.entity.Account;
//import com.example.leave.entity.UserData;

/**
 * Created by Medion on 2016-09-14.
 */
public interface AccountManagerInterface {
    void registerAccount(Account account, AccessLevel accessLevel);
}
