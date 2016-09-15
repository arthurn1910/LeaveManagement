package com.example.leave.repository;

import com.example.leave.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-12.
 */

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByLogin(String login);

    Long getNewID();
}
