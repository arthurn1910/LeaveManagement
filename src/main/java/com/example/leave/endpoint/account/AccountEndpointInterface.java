package com.example.leave.endpoint.account;

import com.example.leave.dto.account.*;
import com.example.leave.entity.account.Account;

import java.util.List;

/**
 * Created by Medion on 2016-09-13.
 */
public interface AccountEndpointInterface {

    void registerAccount(RegisterDTO registerDTO);

    Account getYourAccount();

    Account getUserAccount(String login);

    void editYourAccountData(List<String> accountData);

    void editUserAccount(List<String> data);

    List<Account> getUsers();

    void changePassword(List<String> password) throws Exception;

    void changeUserPassword(String login, String newPassword);

    void changeUserRole(List<String> changeUserRole);

    Boolean isAuthenticated();

    void changeUserActiveStatus(String login, Long version);

    void changeUserConfirmStatus(String login, long version);

    void setAccountToEdit(String login);

    Account getAccountToEdit();

    List<String> getUserRoleToEdit();
}
