package com.management.leave.manager.account;

import com.management.leave.model.RegisterDTO;

/**
 * Created by Medion on 2016-09-07.
 */
public interface AccountInterfaceManager {

    /***
     * Function to register account
     * @param registerDTO
     */
    void registerAccount(RegisterDTO registerDTO);
}
