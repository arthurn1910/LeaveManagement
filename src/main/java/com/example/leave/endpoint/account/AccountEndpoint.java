package com.example.leave.endpoint.account;

import com.example.leave.dto.account.*;
import com.example.leave.entity.account.AccessLevel;
import com.example.leave.entity.account.Account;
//import com.example.leave.entity.UserData;
import com.example.leave.manager.account.AccountManager;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Medion on 2016-09-13.
 */
@Component
public class AccountEndpoint implements AccountEndpointInterface {

    private Account account;
    private Account accountToEdit;
    private AccessLevel accessLevel;

    public AccountEndpoint() {
        this.account = new Account();
        this.accessLevel = new AccessLevel();
    }

    @Autowired
    AccountManager accountManager;

    @Override
    public void registerAccount(RegisterDTO registerDTO) {
        this.account=new Account(registerDTO.getLogin(),registerDTO.getPassword());
        this.account.setUserData(registerDTO.getName(), registerDTO.getLastname(), registerDTO.getEmail());
        AccessLevel accessLevel=new AccessLevel("ROLE_EMPLOYEE",Boolean.TRUE, this.account);
        accountManager.registerAccount(this.account,accessLevel);
    }

    @Override
    public Account getYourAccount(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.account = accountManager.getAccount(user.getUsername());
        return  account;
    }

    @Override
    public Account getUserAccount(String login){
        this.account = accountManager.getAccount(login);
        return account;
    }

    @Override
    public void editYourAccountData(List<String> accountData) {
        this.account.setEmail(accountData.get(2));
        this.account.setName(accountData.get(0));
        this.account.setLastName(accountData.get(1));
        accountManager.editAccount(account);
    }

    @Override
    public void editUserAccount(List<String> data) {
        if(data.get(0).equals(accountToEdit.getLogin())) {
            this.accountToEdit.setEmail(data.get(3));
            this.accountToEdit.setName(data.get(1));
            this.accountToEdit.setLastName(data.get(2));
            this.accountToEdit.setExpirienceDay(Integer.parseInt(data.get(7)));
            this.accountToEdit.setExpirienceMonth(Integer.parseInt(data.get(6)));
            this.accountToEdit.setExpirienceYear(Integer.parseInt(data.get(5)));
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date startingDate= null;
            try {
                startingDate = df.parse(data.get(4));
            } catch (ParseException e) {
                System.out.println("Exception parsing string to date");
            }
            this.accountToEdit.setStartingDate(startingDate);
        } else{
            System.out.println("Exception editUserAccount ");
        }
        accountManager.editAccount(accountToEdit);
    }

    @Override
    public List<Account> getUsers() {
        List<Account> listUsers= accountManager.getUsers();
        return listUsers;
    }

    @Override
    public void changePassword(List<String> password) throws Exception {
        String actualPassword = new Md5PasswordEncoder().encodePassword(password.get(0),null);
        if(actualPassword.equals(account.getPassword())) {
            account.setPassword(password.get(1));
            accountManager.changePassword(account);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void changeUserPassword(String login, String newPassword) {
        if(login.equals(accountToEdit.getLogin())){
            System.out.println("1");
            accountToEdit.setPassword(newPassword);
            accountManager.changePassword(accountToEdit);
        } else{
            System.out.println("2");
            System.out.println("Exception changeUserPassword");
        }

    }

    @Override
    public void changeUserRole(List<String> changeUserRole) {
        if(changeUserRole.get(0).equals(accountToEdit.getLogin())) {
            System.out.println("changeUserRole.get(2) "+changeUserRole.get(2));
            if(changeUserRole.get(2).equals("activate")){
                accountManager.addRoleToUser("ROLE_"+changeUserRole.get(1), accountToEdit);
            }else if(changeUserRole.get(2).equals("deactivate")) {
                accountManager.removeRoleFromUser("ROLE_"+changeUserRole.get(1), accountToEdit);
            }
        }

    }

    private Boolean checkRole(String role){
        Collection<AccessLevel> accessLevelCollection=account.getAccessLevelCollection();
        for(AccessLevel accessLevel: accessLevelCollection) {
            if (accessLevel.getLevel().equals(role))
                return true;
        }
        return false;
    }

    @Override
    public Boolean isAuthenticated() {
        try{
            getYourAccount();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void changeUserActiveStatus(String login, Long version) {
        Account account=getUserAccount(login);
        if(Long.compare(account.getVersion(), version)==0) {
            account.setActive(!account.getActive());
            accountManager.editAccount(account);
        }else{
            //accountendpoint exception optimistic lock
        }
        System.out.println("Attenthion!! accountendpoint exception optimistic lock");

    }

    @Override
    public void changeUserConfirmStatus(String login, long version) {
        Account account=getUserAccount(login);
        if(Long.compare(account.getVersion(), version)==0) {
            account.setConfirm(true);
            accountManager.editAccount(account);
        }else{
            //accountendpoint exception optimistic lock
        }
        System.out.println("Attenthion!! accountendpoint exception optimistic lock");
    }

    @Override
    public void setAccountToEdit(String login) {
        accountToEdit=null;
        accountToEdit=getUserAccount(login);
    }

    @Override
    public Account getAccountToEdit() {
        return accountToEdit;
    }

    @Override
    public List<String> getUserRoleToEdit() {
        accountToEdit=getUserAccount(accountToEdit.getLogin());
        List<String> userRole = new ArrayList<>();
        userRole.add(accountToEdit.getLogin());
        for( AccessLevel accessLevel :accountToEdit.getAccessLevelCollection()){
            if(accessLevel.getActive()){
                userRole.add(accessLevel.getLevel());
            }
        }
        return userRole;
    }
}
