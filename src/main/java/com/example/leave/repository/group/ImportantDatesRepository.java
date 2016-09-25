package com.example.leave.repository.group;

import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Medion on 2016-09-25.
 */
public interface ImportantDatesRepository extends CrudRepository<ImportantDates, Long> {
    List<ImportantDates> findAllByTeamGroup(TeamGroup teamGroup);
}
