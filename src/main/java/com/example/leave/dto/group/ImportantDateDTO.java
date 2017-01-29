package com.example.leave.dto.group;

import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by arthurn on 17.11.16.
 */
public class ImportantDateDTO {

    Logger log = Logger.getLogger(ImportantDates.class.getName());

    private Long id;
    private TeamGroupDTO teamGroupDTO;
    private int number;
    private Date date;

    public ImportantDateDTO(Long id, TeamGroupDTO teamGroupDTO, Date date) {
        this.id = id;
        this.teamGroupDTO = teamGroupDTO;
        this.date = date;
    }

    public ImportantDateDTO(ImportantDates importantDates) {
        this.id = importantDates.getId();
        setTeamGroup(importantDates.getTeamGroup());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(formatter.format(importantDates.getDate()));
        } catch (ParseException e) {
            log.warning("Exception in importantDateDTO. Parse date " + importantDates);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ImportantDateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamGroupDTO getTeamGroupDTO() {
        return teamGroupDTO;
    }

    public void setTeamGroupDTO(TeamGroupDTO teamGroupDTO) {
        this.teamGroupDTO = teamGroupDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTeamGroup(TeamGroup teamGroup){
        this.teamGroupDTO=new TeamGroupDTO(teamGroup);
    }


}
