package com.example.leave.dto.group;

import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;

import java.sql.Date;


/**
 * Created by Medion on 2016-09-25.
 */
public class ImportantDateDTO {
    private TeamGroup teamGroup;

    private String dateStart;
    private String dateEnd;

    public ImportantDateDTO() {
    }

    public ImportantDateDTO(TeamGroup teamGroup, String dateStart, String dateEnd) {
        this.teamGroup = teamGroup;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setImportantDates(ImportantDates importantDates){
        this.dateStart=importantDates.getDateStart().toString();
        this.dateEnd=importantDates.getDateEnd().toString();
        this.teamGroup=importantDates.getTeamGroup();
    }
}
