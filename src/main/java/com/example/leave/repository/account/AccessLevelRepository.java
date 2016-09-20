package com.example.leave.repository.account;

import com.example.leave.entity.account.AccessLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-15.
 */
public interface AccessLevelRepository extends CrudRepository<AccessLevel, Long> {
    List<AccessLevel> findByLevel(String level);

    Long getNewAccessLevelID();

}