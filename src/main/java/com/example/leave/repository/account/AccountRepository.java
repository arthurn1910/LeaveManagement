package com.example.leave.repository.account;

import com.example.leave.entity.account.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Medion on 2016-09-12.
 */

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByLogin(String login);
    Long getNewID();

    void edit(String firstname, String lastname, String userId);
}
