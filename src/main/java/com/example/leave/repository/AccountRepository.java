package com.example.leave.repository;

import com.example.leave.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-12.
 */

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByLogin(String login);
    Long getNewID();

    void edit(String firstname, String lastname, String userId);
}
