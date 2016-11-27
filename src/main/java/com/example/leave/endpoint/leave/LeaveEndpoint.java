package com.example.leave.endpoint.leave;

import com.example.leave.dto.leave.LeaveDTO;
import com.example.leave.dto.leave.LeaveDetailsDTO;
import com.example.leave.entity.account.Account;
import com.example.leave.entity.leave.Leave;
import com.example.leave.entity.leave.LeaveType;
import com.example.leave.manager.leave.LeaveManager;
import com.example.leave.repository.account.AccountRepository;
import com.example.leave.repository.group.TeamGroupRepository;
import com.example.leave.repository.leave.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    LeaveTypeRepository leaveTypeRepository;
    @Override
    public List<LeaveType> getListLeaveType() {
       return leaveManager.getListLeaveType();
    }

    @Override
    public void createLeave(LeaveDTO leaveDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
//        Leave leave=new Leave(account,leaveDTO.getLeaveType(), leaveDTO.getDateStart(), leaveDTO.getDateEnd(), true);
//        leaveManager.createLeave(leave);
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
        System.out.println(leaveListDTO.size());
        return leaveListDTO;
    }

    @Override
    public LeaveDetailsDTO getLeaveDetails() {
        System.out.println("()*");
        LeaveDetailsDTO leaveDetailsDTO=new LeaveDetailsDTO();
        List<Leave> leaveList=leaveManager.getLeaveUserWithType(getAccount(), leaveTypeRepository.findOne(1L));
        setPaidVacationDetails(leaveList,leaveDetailsDTO);
        return null;

    }

    private Account getAccount(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account=accountRepository.findByLogin(user.getUsername());
        return account;
    }

    private void setPaidVacationDetails(List<Leave> leaveList, LeaveDetailsDTO leaveDetailsDTO){
        Account account=getAccount();
        setLeaveDaysLastYear(account, leaveDetailsDTO);
        setLeaveDaysCurrentYear(account, leaveDetailsDTO);
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
                    int countMonth = (int) Math.floor(days / 30);
                    int daysLeave = (int) Math.ceil(countMonth * 20 / 12);
                    daysLeave += (int) Math.ceil((12 - countMonth) * 26 / 12);
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
                } else {
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
                    return;
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
                        System.out.println(Math.ceil(((int) Math.floor(((workDays-days)/30))*26/12)));
                        System.out.println(((int) Math.floor(((workDays-days)/30))*26/12));
                        System.out.println((workDays-days/30));
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
