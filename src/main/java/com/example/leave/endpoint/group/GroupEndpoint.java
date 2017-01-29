package com.example.leave.endpoint.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.dto.group.TeamGroupDTO;
import com.example.leave.dto.group.UserGroupDTO;
import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.dto.leave.LeaveDetailsDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.endpoint.leave.LeaveEndpoint;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.manager.group.GroupManager;
import com.example.leave.manager.leave.LeaveManager;
import com.example.leave.repository.account.AccountRepository;
import com.example.leave.repository.leave.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Medion on 2016-09-20.
 */
@Component
public class GroupEndpoint implements GroupEndpointInterface {
    Logger log = Logger.getLogger(GroupEndpoint.class.getName());

    private TeamGroup teamGroup;
    private Account account;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    LeaveManager leaveManager;

    @Autowired
    LeaveEndpoint leaveEndpoint;

    @Autowired
    GroupManager groupManager;

    @Override
    public void createGroup(List<String>  data) {
        TeamGroup teamGroupTmp=new TeamGroup();
        teamGroupTmp.setVersion(0L);
        teamGroupTmp.setCreateDate(new java.util.Date());
        teamGroupTmp.setGroupTitle(data.get(0));
        teamGroupTmp.setNumber(Integer.parseInt(data.get(1)));
        getYourAccount();
        teamGroupTmp.setManager(this.account);
        TeamGroupMember teamGroupMember=new TeamGroupMember(this.account,teamGroupTmp);
        groupManager.createGroup(teamGroupTmp, teamGroupMember);
    }

    @Override
    public List<TeamGroup> getAllGroups() {
        return groupManager.getAllGroups();
    }

    @Override
    public List<TeamGroupDTO> getAllGroupsDTO() {
        List<TeamGroup> teamGroupList=getAllGroups();
        List<TeamGroupDTO> teamGroupDTOList=new ArrayList<>();
        for(TeamGroup teamGroup : teamGroupList){
            teamGroupDTOList.add(new TeamGroupDTO(teamGroup));
        }
        return teamGroupDTOList;

    }

    @Override
    public void joinToGroup(TeamGroupDTO teamGroupDTO) {
        getYourAccount();
        this.teamGroup=getTeamGroup(teamGroupDTO.getID());
        TeamGroupMember teamGroupMember=new TeamGroupMember(this.account, this.teamGroup);
        groupManager.joinToGroup(teamGroupMember);
    }

    @Override
    public void getYourAccount() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.account=accountRepository.findByLogin(user.getUsername());
    }

    @Override
    public TeamGroup getTeamGroup(Long id) {
        this.teamGroup=groupManager.getTeamGroup(id);
        return this.teamGroup;
    }

    @Override
    public List<TeamGroupMember> getTeamGroupUser(Boolean active) {
        getYourAccount();
        List<TeamGroupMember> teamGroupMemberList=groupManager.getTeamGroup(this.account,active);
        return teamGroupMemberList;
    }

    @Override
    public String acceptApplication(List<String> data) {
        this.teamGroup=getTeamGroup(this.teamGroup.getId());
        Collection<TeamGroupMember> teamGroupMemberList=getMemberInGroup();
        for(TeamGroupMember teamGroupMember : teamGroupMemberList){
            if(teamGroupMember.getId().equals(Long.valueOf(data.get(0)))){
                if(groupManager.getTeamGroup(teamGroupMember.getEmployee(),true).size()==0) {
                    teamGroupMember.setActive(true);
                    groupManager.acceptApplication(teamGroupMember);
                }else{
//                    groupManager.removeMember(teamGroupMember);
                    return "Użytkownik należy już do innej grupy";
                }
            }
        }
        this.teamGroup=getTeamGroup();
        return "Użytkownik zaakceptowany";
    }

    @Override
    public void removeMember(List<String> data) {
        for(TeamGroupMember teamGroupMember : this.teamGroup.getTeamGroupMembers()){
            if(teamGroupMember.getId().equals(Long.valueOf(data.get(0)))){
                groupManager.removeMember(teamGroupMember);
            }
        }
        this.teamGroup=getTeamGroup();
    }

    @Override
    public List<TeamGroupMember> getMemberInGroup() {
        return groupManager.getMemberInGroup(this.teamGroup);
    }

    @Override
    public TeamGroup getTeamGroup() {
        this.teamGroup=groupManager.getTeamGroup(this.teamGroup.getId());
        return this.teamGroup;
    }

    @Override
    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    @Override
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void createImportantDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {
            date1=df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ImportantDates importantDates=new ImportantDates(this.teamGroup, date1);
        groupManager.createImportantDate(importantDates);
    }

    @Override
    public void removeImportantDate(String id) {
        this.teamGroup=getTeamGroup(this.teamGroup.getId());
        for(ImportantDates importantDates : this.teamGroup.getImportantDates()){
            if(importantDates.getId()==Long.valueOf(id)) {
                groupManager.removeImportantDate(importantDates);
            }
        }
    }

    @Override
    public List<ImportantDateDTO> getImportantDates() {
        this.teamGroup=getTeamGroup(this.teamGroup.getId());
        List<ImportantDates> importantDatesList= groupManager.getImportantDates(this.teamGroup);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date dateNow=null;
        try {
            dateNow = formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            log.warning("Exception getImportantDates "+e);
        }
        List<ImportantDateDTO> importantDateDTOList =new ArrayList<>();
        for(ImportantDates importantDates : importantDatesList){
            ImportantDateDTO importantDateDTO=new ImportantDateDTO(importantDates);
            if(dateNow.before(importantDateDTO.getDate()) | dateNow.equals(importantDateDTO.getDate())){
                importantDateDTOList.add(importantDateDTO);
            }
        }
        for(ImportantDateDTO importantDateDTO : importantDateDTOList){
            TeamGroup teamGroup=groupManager.getTeamGroup(importantDateDTO.getTeamGroupDTO().getID());
            int number =checkdate(importantDateDTO.getDate(), teamGroup);
            importantDateDTO.setNumber(number);
        }

        return importantDateDTOList;

    }

    @Override
    public List<LeaveDTO> getAllLeaveInGroup() {
        List<LeaveDTO> leaveDTOList=new ArrayList<>();
        List<Leave> leaveList=groupManager.getAllLeaveInGroup(teamGroup);
        for(Leave leave : leaveList){
            LeaveDTO leaveDTO=new LeaveDTO(leave.getId(),leave.getLeaveType(),leave.getDateStart(),leave.getDateEnd(),leave.getActive(), leave.getConfirm(),leave.getAccount());
            leaveDTOList.add(leaveDTO);
        }
        return leaveDTOList;
    }

    @Override
    public String confirmLeave(String id) {
        Leave leave=leaveRepository.findOne(Long.valueOf(id));
        LeaveDetailsDTO leaveDetailsDTO=leaveEndpoint.getLeaveDetails(leave);
        System.out.println("wczesniej sprawdzić czy dany termin nie ejst już zablokowany");
        TeamGroupMember teamGroupMember=groupManager.getTeamGroup(leave.getAccount(), true).get(0);
        TeamGroup teamGroup=groupManager.getTeamGroup(teamGroupMember.getTeamGroup().getId());


        Calendar calendarStart=Calendar.getInstance();
        calendarStart.setTime(leave.getDateStart());
        Calendar calendarEnd=Calendar.getInstance();
        calendarEnd.setTime(leave.getDateEnd());
        int tmp=0;
        while(!calendarStart.getTime().after(calendarEnd.getTime())){
            if(tmp!=0) {
                calendarStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            if(checkdate(calendarStart.getTime(), teamGroup)==0){
                rejectLeave(id);
                return "Urlop zawiera termin: "+calendarStart.getTime().toString()+" w którym osiągnięto maksymalną liczbę pracowników na urlopie";
            }
            tmp++;
        }



        if((leaveDetailsDTO.getLeaveLastYear()+leaveDetailsDTO.getLeaveThisYear()-leaveDetailsDTO.getReamainingVacationLeaveLastYear()
                -leaveDetailsDTO.getReamainingVacationLeaveThisYear())>=(leave.getLastYearDays()+leave.getCurrentYearDays())) {
            groupManager.confirmLeave(id);
            return "Urlop potwierdzony";
        }
        rejectLeave(id);
        return "Urlop nie został potwierdzony. Użytkownik nie ma wystarczającej liczby dni urlopu";
    }

    @Override
    public void rejectLeave(String id) {
        groupManager.rejectLeave(id);
    }

    @Override
    public int checkdate(Date date, TeamGroup teamGroup) {
        List<TeamGroupMember> teamGroupMemberList=groupManager.getMemberInGroup(teamGroup);
        int member=teamGroupMemberList.size();
        List<ImportantDates> importantDatesList=groupManager.getImportantDates(teamGroup,date);
        if(importantDatesList.size()==0)
            return -1;

        List<Leave> leaveList=new ArrayList<>();
        for(TeamGroupMember teamGroupMember : teamGroupMemberList){
            Account account=teamGroupMember.getEmployee();
            List<Leave> leaveList1=leaveManager.findAllByAccountAndActiveAndConfirmAndDate(account,true,true,date);
            if(leaveList1.size()>0)
                leaveList.addAll(leaveList1);
        }
        int tmp=(int) Math.ceil(member*((100-teamGroup.getNumber())/100));
        if(tmp==0)
            tmp=1;
        if(leaveList.size()>=tmp)
            return 0;
        else{
            return tmp-leaveList.size();
        }
    }

    @Override
    public void rejectPlannedLeave(Leave leave) {
        groupManager.rejectPlannedLeave(leave);
    }

    @Override
    public UserGroupDTO getUserGroup(){
        UserGroupDTO userGroupDTO=new UserGroupDTO();
        getYourAccount();
        userGroupDTO.setAccount(this.account);
        userGroupDTO.setApplyTeamGroupDTOList(getTeamGroupDTOList(false));
        userGroupDTO.setTeamGroupDTOList(getTeamGroupDTOList(true));
        return userGroupDTO;

    }

    @Override
    public List<TeamGroupDTO> getTeamGroupDTOList(Boolean active){
        List<TeamGroupDTO> teamGroupDTOList=new ArrayList<>();
        List<TeamGroupMember> teamGroupMemberList=getTeamGroupUser(active);
        for(TeamGroupMember teamGroupMember: teamGroupMemberList){
            TeamGroup teamGroup=teamGroupMember.getTeamGroup();
            TeamGroupDTO teamGroupDTO=new TeamGroupDTO();
            teamGroupDTO.setTeamGroup(teamGroup);
            teamGroupDTOList.add(teamGroupDTO);
        }
        return teamGroupDTOList;
    }

    @Override
    public TeamGroupDTO getTeamGroupDTO(){
        TeamGroupDTO teamGroupDTO=new TeamGroupDTO(this.teamGroup);
        return teamGroupDTO;
    }

    @Override
    public void removeGroup() {
        this.teamGroup=getTeamGroup(this.teamGroup.getId());
        groupManager.removeGroup(this.teamGroup);
    }

    @Override
    public void applyToGroup(String id) {
        getYourAccount();
        TeamGroup teamGroup=getTeamGroup(Long.valueOf(id));
        TeamGroupMember teamGroupMember=new TeamGroupMember(this.account,teamGroup);
        groupManager.applyToGroup(teamGroupMember);
    }
}
