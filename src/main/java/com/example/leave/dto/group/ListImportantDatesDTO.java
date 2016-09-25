package com.example.leave.dto.group;

import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;

import java.util.List;

/**
 * Created by Medion on 2016-09-25.
 */
public class ListImportantDatesDTO {
    private TeamGroup teamGroup;
    private List<ImportantDates> importantDatesList;

    public ListImportantDatesDTO() {
    }

    public ListImportantDatesDTO(TeamGroup teamGroup, List<ImportantDates> importantDatesList) {
        this.teamGroup = teamGroup;
        this.importantDatesList = importantDatesList;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public List<ImportantDates> getImportantDatesList() {
        return importantDatesList;
    }

    public void setImportantDatesList(List<ImportantDates> importantDatesList) {
        this.importantDatesList = importantDatesList;
    }

}
