package com.example.leave.endpoint.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.dto.leave.LeaveDetailsDTO;
import com.example.leave.endpoint.account.AccountEndpoint;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.group.ImportantDates;
import com.example.leave.entity.group.TeamGroup;
import com.example.leave.entity.group.TeamGroupMember;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.example.leave.manager.leave.LeaveManager;
import com.example.leave.repository.account.AccountRepository;
import com.example.leave.repository.group.ImportantDatesRepository;
import com.example.leave.repository.group.TeamGroupRepository;
import com.example.leave.repository.leave.LeaveTypeRepository;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Medion on 2016-09-27.
 */
@Component
public class LeaveEndpoint implements LeaveEndpointInterface {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LeaveManager leaveManager;
    @Autowired
    GroupEndpoint groupEndpoint;
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    @Autowired
    ImportantDatesRepository importantDatesRepository;
    @Override
    public List<LeaveType> getListLeaveType() {
       return leaveManager.getListLeaveType();
    }

    Logger log = Logger.getLogger(LeaveEndpoint.class.getName());

    @Override
    public String createParentalLeave(List<String> data) {
        Account account=getAccount();
        LeaveType leaveType=leaveTypeRepository.findOne(Long.valueOf(data.get(0)));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {
            date1=df.parse(data.get(1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date1);
        int week=Integer.parseInt(data.get(2));
        calendar.add(Calendar.WEEK_OF_YEAR,week);
        Leave leave=new Leave(account,leaveType,date1,0,0,calendar.getTime());
        leaveManager.createLeave(leave);
        return JSONParser.quote("Urlop zostal stworzony");

    }

    @Override
    public List<Date> getBlockDate() {
        Collection<TeamGroupMember> teamGroupMemberCollection= getAccount().getTeamGroupMemberCollection();
        List<Date> dateList=new ArrayList<>();
        if(teamGroupMemberCollection.size()==0)
            return dateList;
        TeamGroup teamGroup=null;
        for(TeamGroupMember teamGroupMember : teamGroupMemberCollection){
            teamGroup=teamGroupMember.getTeamGroup();
            break;
        }
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR,0);
        List<ImportantDates> importantDatesList= importantDatesRepository.findAllByTeamGroupAfterNow(teamGroup,calendar.getTime());
        int sizeTeam=teamGroup.getTeamGroupMembers().size();
        for(ImportantDates importantDates : importantDatesList){
            /*List<Leave> leaveList=leaveManager.getLeaveWithTypeAndTeamAndDate(leaveTypeRepository.findOne(1L),teamGroup,importantDates.getDate());
            leaveList.addAll(leaveManager.getLeaveWithTypeAndTeamAndDate(leaveTypeRepository.findOne(2L),teamGroup,importantDates.getDate()));
            int member=(int)((100-teamGroup.getNumber())/100)*sizeTeam;
            if(member==0)
                member=1;
            if(member<=leaveList.size())
                dateList.add(importantDates.getDate());*/
            if(groupEndpoint.checkdate(importantDates.getDate(), teamGroup)==0){
                dateList.add(importantDates.getDate());
            }

        }

        return dateList;


    }

    @Override
    public List<LeaveDTO> getAllLeave() {
        List<Leave> leaveList= leaveManager.getAllLeave();
        List<LeaveDTO> leaveDTOList=new ArrayList<>();
        for(Leave leave : leaveList){
            leaveDTOList.add(new LeaveDTO(leave));
        }
        return leaveDTOList;
    }

    @Override
    public String createLeave(List<String> data) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dateStart=Calendar.getInstance();
        Calendar dateEnd=Calendar.getInstance();
        try {
            dateStart.setTime(df.parse(data.get(1)));
            dateEnd.setTime(df.parse(data.get(2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateEnd.add(Calendar.DAY_OF_YEAR,1);
        Leave leave=new Leave(account, leaveTypeRepository.findOne(Long.valueOf(data.get(0))),dateStart.getTime(),0,0,dateEnd.getTime());;
        List<Date> dateList=getBlockDate();
        for(Date date : dateList){
            if(dateStart.before(date)&& dateEnd.after(date)) {
                log.warning("Exception in createLeave. Date is blocked " + data);
                return "Data jest zablokowana";
            }

        }
        if(leave.getLeaveType().getId().equals(1L)) {
            LeaveDetailsDTO leaveDetailsDTO = getLeaveDetails();
            int days = countDays(dateStart.getTime(), dateEnd.getTime());
            if (days <= (leaveDetailsDTO.getLeaveThisYear()+leaveDetailsDTO.getLeaveLastYear() - leaveDetailsDTO.getReamainingVacationLeaveLastYear()
                    - leaveDetailsDTO.getReamainingVacationLeaveLastYear())) {
                if (days <= (leaveDetailsDTO.getLeaveLastYear()- leaveDetailsDTO.getReamainingVacationLeaveLastYear())) {
                    leave.setLastYearDays(days);
                } else {
                    leave.setLastYearDays(leaveDetailsDTO.getLeaveLastYear()-leaveDetailsDTO.getReamainingVacationLeaveLastYear());
                    leave.setCurrentYearDays(days - leave.getLastYearDays());
                }
            } else {
                log.warning("Exception in createLeave. Not days to create paid leave");
                return "Nie wystarczajaca liczba dni do stworzenia urlopu";
            }
        }

        leaveManager.createLeave(leave);
        return "Urlop zostal stworzony";
    }

    private int countDays(Date dateStart, Date dateEnd) {
        if(dateStart.before(dateEnd) || dateStart.equals(dateEnd)){
            Calendar calendarStart=Calendar.getInstance();
            calendarStart.setTime(dateStart);
            Calendar calendarEnd=Calendar.getInstance();
            calendarEnd.setTime(dateEnd);
            int count=1;
            while(!calendarStart.getTime().equals(calendarEnd.getTime())){
                if(calendarStart.get(Calendar.DAY_OF_WEEK)!=6 && calendarStart.get(Calendar.DAY_OF_WEEK)!=7)
                    count++;
                calendarStart.add(Calendar.DAY_OF_YEAR,1);
            }
            return count;
        }else{
            log.warning("Exception in caountDays. Date start is before date end");
        }
        return 0;
    }

    @Override
    public void removeLeave(Long id) {
        leaveManager.removeLeave(id);
    }

    @Override
    public List<LeaveDTO> getYourLeave() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
        List<Leave> leaveList= leaveManager.getLeave(account);
        List<LeaveDTO> leaveListDTO=new ArrayList<>();
        for(Leave leave : leaveList){
            leaveListDTO.add(new LeaveDTO(leave));
        }
        return leaveListDTO;
    }

    @Override
    public LeaveDetailsDTO getLeaveDetails() {
        LeaveDetailsDTO leaveDetailsDTO=new LeaveDetailsDTO();
        List<Leave> leaveList=leaveManager.getLeaveUserWithType(getAccount(), leaveTypeRepository.findOne(1L));
        Account account=getAccount();
        setPaidVacationDetails(leaveList,leaveDetailsDTO, account);
        return leaveDetailsDTO;

    }

    public LeaveDetailsDTO getLeaveDetails(Leave leave){
        LeaveDetailsDTO leaveDetailsDTO=new LeaveDetailsDTO();
        List<Leave> leaveList=leaveManager.getLeaveUserWithType(leave.getAccount(), leaveTypeRepository.findOne(1L));
        setPaidVacationDetails(leaveList,leaveDetailsDTO, leave.getAccount());
        return leaveDetailsDTO;
    }

    private Account getAccount(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
        return account;
    }

    public void setPaidVacationDetails(List<Leave> leaveList, LeaveDetailsDTO leaveDetailsDTO, Account account){
        setLeaveDaysLastYear(account, leaveDetailsDTO);
        setLeaveDaysCurrentYear(account, leaveDetailsDTO);
        setUsingVacationDays(account, leaveDetailsDTO);
    }

    private void setUsingVacationDays(Account account, LeaveDetailsDTO leaveDetailsDTO) {
        int lastYear=0;
        int currentYear=0;
        Calendar calendarLastYearStart=new GregorianCalendar();
        int year=Calendar.getInstance().get(Calendar.YEAR)-1;
        calendarLastYearStart.set(year,Calendar.JANUARY,1);
        Calendar calendarLastYearEnd=new GregorianCalendar();
        calendarLastYearEnd.set(year,Calendar.DECEMBER,31);
        year+=1;
        Calendar calendarCurrentYearStart=new GregorianCalendar();
        Calendar calendarCurrentYearEnd=new GregorianCalendar();
        calendarCurrentYearStart.set(year,Calendar.JANUARY,1);
        calendarCurrentYearEnd.set(year,Calendar.DECEMBER,31);

        List<Leave> leaveList=leaveManager.getLeaveUserWithType(account,leaveTypeRepository.findOne(1L));
        for(Leave leave : leaveList){
            if(leave.getActive()==true && leave.getConfirm()==true) {
                if (leave.getDateStart().after(calendarCurrentYearStart.getTime()) && leave.getDateStart().before(calendarCurrentYearEnd.getTime())) {
                    lastYear += leave.getLastYearDays();
                    currentYear += leave.getCurrentYearDays();
                } else if (leave.getDateStart().after(calendarLastYearStart.getTime()) && leave.getDateStart().before(calendarLastYearEnd.getTime())) {
                    lastYear += leave.getCurrentYearDays();
                }
            }
        }
        leaveDetailsDTO.setReamainingVacationLeaveThisYear(currentYear);
        leaveDetailsDTO.setReamainingVacationLeaveLastYear(lastYear);
    }

    private void setLeaveDaysCurrentYear(Account account, LeaveDetailsDTO leaveDetailsDTO){
        int expirience=account.getExpirienceYear()*12*30+account.getExpirienceMonth()*30+account.getExpirienceDay();
        int year=Calendar.getInstance().get(Calendar.YEAR);
        Calendar calendarYearStart=new GregorianCalendar();
        calendarYearStart.set(year,Calendar.JANUARY,1);
        Calendar calendarYearEnd=new GregorianCalendar();
        calendarYearEnd.set(year,Calendar.DECEMBER,31);
        Calendar accountStarting = new GregorianCalendar();
        accountStarting.setTime(account.getStartingDate());
        if(calendarYearStart.after(accountStarting)){
            expirience+=Math.toIntExact(TimeUnit.MILLISECONDS.toDays(Math.abs(calendarYearStart.getTimeInMillis() - accountStarting.getTimeInMillis())));
            if(expirience>=10*12*30){
                int days=(int) Math.ceil((double) 26*account.getWorkTime()/100);
                leaveDetailsDTO.setLeaveThisYear(days);
                return;
            }else{
                int workDays= Math.toIntExact(TimeUnit.MILLISECONDS.toDays(Math.abs(Calendar.getInstance().getTimeInMillis() - calendarYearStart.getTimeInMillis())));
                int daysToHigherLeave=10*12*30-expirience;
                if(daysToHigherLeave>=workDays){
                    int days=(int) Math.ceil((double) 20*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveThisYear(days);
                    return;
                }else{
                    int days=workDays-daysToHigherLeave;
                    int countMonth=(int) Math.floor(days/30);
                    int daysLeave=(int) Math.ceil(countMonth*20/12);
                    daysLeave+=(int) Math.ceil((12-countMonth)*26/12);
                    if(countMonth<2 && daysLeave>26){
                        daysLeave=26;
                    }else if(countMonth>=12 && daysLeave>20){
                        daysLeave=20;
                    }
                    daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveThisYear(daysLeave);
                    return;
                }
            }
        }else{
            int workDays= Math.toIntExact(TimeUnit.MILLISECONDS.toDays(Math.abs(Calendar.getInstance().getTimeInMillis() - accountStarting.getTimeInMillis())));
            if(expirience>=10*12*30){
                int countMonth=(int) Math.floor(workDays/30);
                int daysLeave=(int) Math.ceil(countMonth*26/12);
                if(daysLeave>26)
                    daysLeave=26;
                daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                leaveDetailsDTO.setLeaveThisYear(daysLeave);
                return;
            }else{
                int daysToHigherLeave=10*12*30-expirience;
                if(daysToHigherLeave>=workDays){
                    int countMonth=(int) Math.floor(workDays/30);
                    int daysLeave=(int) Math.ceil(countMonth*20/12);
                    if(countMonth>=12 && daysLeave>20){
                        daysLeave=20;
                    }
                    daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveLastYear(daysLeave);
                    return;
                }else {
                    int days = workDays - daysToHigherLeave;
                    int workMonth=(int) Math.ceil(workDays/30);
                    int countMonth = (int) Math.ceil(days / 30);
                    int daysLeave = (int) Math.ceil(countMonth * 20 / 12);
                    daysLeave += (int) Math.ceil((workMonth - countMonth) * 26 / 12);
                    if (countMonth < 2 && daysLeave > 26) {
                        daysLeave = 26;
                    } else if (countMonth >= 12 && daysLeave > 20) {
                        daysLeave = 20;
                    }
                    daysLeave = (int) Math.ceil((double) daysLeave * account.getWorkTime() / 100);
                    leaveDetailsDTO.setLeaveThisYear(daysLeave);
                    return;
                }
            }
        }
    }


    private void setLeaveDaysLastYear(Account account, LeaveDetailsDTO leaveDetailsDTO){
        int expirience=account.getExpirienceYear()*12*30+account.getExpirienceMonth()*30+account.getExpirienceDay();
        int lastYear=Calendar.getInstance().get(Calendar.YEAR)-1;
        Calendar calendar=new GregorianCalendar();
        calendar.set(lastYear,Calendar.DECEMBER,31);
        Calendar accountStarting = new GregorianCalendar();
        accountStarting.setTime(account.getStartingDate());
        if(calendar.before(accountStarting)){
            leaveDetailsDTO.setLeaveLastYear(0);
            leaveDetailsDTO.setReamainingVacationLeaveLastYear(0);
        }else{
            calendar.set(lastYear, Calendar.JANUARY,1);
            if(calendar.after(accountStarting)) {
                if (expirience >= 10*12*30) {
                    int days=(int) Math.ceil((double) 26*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveLastYear(days);
                    return;
                } else if((expirience + 365)<=10*12*30){
                    int days=(int) Math.ceil((double) 20*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveLastYear(days);
                    return;
                }else{
                    int days=10*12*30-expirience;
                    int countMonth=(int) Math.floor(days/30);
                    int daysLeave=(int) Math.ceil(countMonth*20/12);
                    daysLeave+=(int) Math.ceil((12-countMonth)*26/12);
                    if(countMonth<2 && daysLeave>26){
                        daysLeave=26;
                    }else if(countMonth>=12 && daysLeave>20){
                        daysLeave=20;
                    }
                    daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveLastYear(daysLeave);
                }
            }else{
                Calendar calendar1=new GregorianCalendar();
                calendar1.set(lastYear,Calendar.DECEMBER,31);
                int workDays= Math.toIntExact(TimeUnit.MILLISECONDS.toDays(Math.abs(calendar1.getTimeInMillis() - accountStarting.getTimeInMillis())));
                int daysLeave;
                if(account.getExpirienceYear()>=10){
                    int countMonth=(int) Math.floor(workDays/30);
                    daysLeave=(int) Math.ceil(countMonth*26/12);
                    daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                    leaveDetailsDTO.setLeaveLastYear(daysLeave);
                    return;
                }else{
                    int days=10*12*30-expirience;
                    if(workDays<=days){
                        int countMonth=(int) Math.floor(workDays/30);
                        daysLeave=(int) Math.ceil(countMonth*20/12);
                        if(daysLeave>20)
                            daysLeave=20;
                        daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                        leaveDetailsDTO.setLeaveLastYear(daysLeave);
                        return;
                    }else{
                        int countMonth=(int) Math.floor(days/30);
                        daysLeave=(int) Math.ceil(countMonth*20/12);
                        daysLeave+=(int) Math.ceil(((int) Math.floor(((workDays-days)/30))*26/12));
                        if(daysLeave>26)
                            daysLeave=26;
                        daysLeave=(int) Math.ceil((double) daysLeave*account.getWorkTime()/100);
                        leaveDetailsDTO.setLeaveLastYear(daysLeave);
                        return;
                    }
                }
            }
        }

    }
}
