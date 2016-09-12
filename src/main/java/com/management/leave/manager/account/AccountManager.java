package com.management.leave.manager.account;

import com.management.leave.entity.AccessLevel;
import com.management.leave.entity.Account;
import com.management.leave.facade.account.AccountFacade;
import com.management.leave.model.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Medion on 2016-09-07.
 */
@Component
public class AccountManager implements AccountInterfaceManager {
    @Autowired
    AccountFacade accountFacade;

    @Override
    public void registerAccount(RegisterDTO registerDTO) {
        System.out.println("Mnager register in");
        Account account=new Account(registerDTO.getLogin(), registerDTO.getPassword());//, registerDTO.getName(),
                //registerDTO.getLastname(), registerDTO.getEmail());
        account.setActive(Boolean.TRUE);
        account.setConfirm(Boolean.FALSE);
        //account.setCreateDate(new Date());
        account.setVersion(0L);
        AccessLevel accessLevel=new AccessLevel("ROLE_USER", account, Boolean.TRUE, 0L);

        accountFacade.zrobCos(account);

        System.out.println("Manager register out");
    }
}
