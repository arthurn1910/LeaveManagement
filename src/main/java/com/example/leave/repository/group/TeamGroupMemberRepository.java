package com.example.leave.repository.group;

import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-21.
 */
public interface TeamGroupMemberRepository extends CrudRepository<TeamGroupMember, Long> {
    List<TeamGroupMember> findAllByTeamGroupIDAndActive(TeamGroup teamGroup, Boolean active);
}
