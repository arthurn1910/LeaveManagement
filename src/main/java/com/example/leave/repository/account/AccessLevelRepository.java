package com.example.leave.repository.account;

import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-15.
 */
public interface AccessLevelRepository extends CrudRepository<AccessLevel, Long> {
    List<AccessLevel> findByLevel(String level);
    List<AccessLevel> findByLevelAndAccount(String level, Account account);
    Long getNewAccessLevelID();

    @Modifying
    void removeLevel(Long id);
}