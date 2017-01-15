package com.example.leave.repository.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-20.
 */
public interface TeamGroupRepository extends CrudRepository<TeamGroup, Long> {
    TeamGroup findOneByTitle(String titleGroup);
    Long getNewID();
    List<TeamGroup> findAllByAccount(Account account);

    @Modifying
    void remove(Long id);
}
