package com.example.leave.dto.account;

/**
 * Created by Medion on 2016-10-13.
 */
public class AuthDTO {
    String login;
    Boolean roleManager, roleAdministrator, roleAccountant, roleEmployee, authenticated;

    public AuthDTO() {
        this.login="";
        this.roleAccountant=false;
        this.roleAdministrator=false;
        this.roleEmployee=false;
        this.roleManager=false;
        this.authenticated=false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        this.authenticated=true;
    }

    public Boolean getRoleManager() {
        return roleManager;
    }

    public void setRoleManager(Boolean roleManager) {
        this.roleManager = roleManager;
    }

    public Boolean getRoleAdministrator() {
        return roleAdministrator;
    }

    public void setRoleAdministrator(Boolean roleAdministrator) {
        this.roleAdministrator = roleAdministrator;
    }

    public Boolean getRoleAccountant() {
        return roleAccountant;
    }

    public void setRoleAccountant(Boolean roleAccountant) {
        this.roleAccountant = roleAccountant;
    }

    public Boolean getRoleEmployee() {
        return roleEmployee;
    }

    public void setRoleEmployee(Boolean roleEmployee) {
        this.roleEmployee = roleEmployee;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        authenticated = authenticated;
    }
}
