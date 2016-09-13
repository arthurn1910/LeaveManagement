package com.example.Repository;

import com.example.Entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Medion on 2016-09-12.
 */

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByFirstName(String firstName);
}
