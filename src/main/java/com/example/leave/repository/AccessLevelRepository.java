package com.example.leave.repository;

import com.example.leave.entity.AccessLevel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-15.
 */
public interface AccessLevelRepository extends CrudRepository<AccessLevel, Long> {
    List<AccessLevel> findByLevel(String level);

    Long getNewAccessLevelID();

}