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

    void editUserAccount(UserDTO userDto);

    List<Account> getUsers();

    void changePassword(ChangePasswordDTO changePasswordDTO) throws Exception;

    void changeUserPassword(ChangeUserPasswordDTO changeUserPasswordDTO);

    void changeUserRole(ChangeUserRoleDTO changeUserRoleDTO);

    Account getAccount();

    Boolean isAuthenticated();
}
