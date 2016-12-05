package com.example.leave.repository.group;

import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Medion on 2016-09-25.
 */
public interface ImportantDatesRepository extends CrudRepository<ImportantDates, Long> {
    Long getNewID();
    List<ImportantDates> findAllByTeamGroup(TeamGroup teamGroup);
    @Modifying
    void remove(Long id);
    List<ImportantDates> findAllByTeamGroupAfterNow(TeamGroup teamGroup, Date time);


}
