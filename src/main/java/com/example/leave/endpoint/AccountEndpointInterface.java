package com.example.leave.endpoint;

import com.example.leave.dto.EditAccountDTO;
import com.example.leave.dto.RegisterDTO;
import com.example.leave.entity.Account;

/**
 * Created by Medion on 2016-09-13.
 */
public interface AccountEndpointInterface {

    void registerAccount(RegisterDTO registerDTO);

    Account getYourAccountToEdit();

    Account getAccountToEdit(String login);

    void editYourAccountData(EditAccountDTO editAccountDTO);
}
