package com.example.leave.repository.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Medion on 2016-09-20.
 */
public interface TeamGroupRepository extends CrudRepository<TeamGroup, Long> {
    TeamGroup findOneByAccount(Account account);

    TeamGroup findOneByTitle(String titleGroup);
}
