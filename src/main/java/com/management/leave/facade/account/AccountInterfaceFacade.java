package com.management.leave.facade.account;

import com.management.leave.entity.Account;

/**
 * Created by Medion on 2016-09-07.
 */
public interface AccountInterfaceFacade {
    public void insert(Account account);
    public Account findByCustomerId(int custId);
}