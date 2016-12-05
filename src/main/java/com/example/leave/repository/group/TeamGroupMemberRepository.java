package com.example.leave.repository.group;

import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-21.
 */
public interface TeamGroupMemberRepository extends CrudRepository<TeamGroupMember, Long> {
    Long getNewID();
    List<TeamGroupMember> findAllByAccountAndActive(Account account, boolean b);

    List<TeamGroupMember> findAllByTeamGroupID(TeamGroup teamGroup);

    @Modifying
    void remove(Long id);
}
