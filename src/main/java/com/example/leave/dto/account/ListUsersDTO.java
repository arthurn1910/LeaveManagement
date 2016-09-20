package com.example.leave.dto.account;

import java.util.List;

/**
 * Created by Medion on 2016-09-17.
 */
public class ListUsersDTO {
    private List<UserDTO> accountList;

    public ListUsersDTO() {
    }

    public ListUsersDTO(List<UserDTO> accountList) {
        this.accountList = accountList;
    }

    public List<UserDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<UserDTO> accountList) {
        this.accountList = accountList;
    }
}
