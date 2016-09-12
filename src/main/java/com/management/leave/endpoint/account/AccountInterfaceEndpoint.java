package com.management.leave.endpoint.account;


import com.management.leave.model.RegisterDTO;

/**
 * Created by Medion on 2016-09-07.
 */
public interface AccountInterfaceEndpoint {
    /***
     * Function to register account
     * @param registerDTO
     */
    void registerAccount(RegisterDTO registerDTO);

}
