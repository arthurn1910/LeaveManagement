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

    void editYourAccountData(UserDTO userDto);

    void editUserAccount(List<String> data);

    List<Account> getUsers();

    void changePassword(ChangePasswordDTO changePasswordDTO) throws Exception;

    void changeUserPassword(String login, String newPassword);

    void changeUserRole(List<String> changeUserRole);

    Account getAccount();

    Boolean isAuthenticated();

    void changeUserActiveStatus(String login, Long version);

    void changeUserConfirmStatus(String login, long version);

    void setAccountToEdit(String login);

    Account getAccountToEdit();

    List<String> getUserRoleToEdit();
}
