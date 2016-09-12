package com.management.leave.facade.account;

import com.management.leave.entity.Account;
import com.management.leave.facade.AbstractFacade;
import com.management.leave.repository.AccountRepository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Medion on 2016-09-07.
 */
@Component
public class AccountFacade{

        @Autowired
        AccountRepository accountRepository;


        @Transactional
        public void zrobCos(Account account) {
            System.out.println("1");
            Account a=new Account();
            accountRepository.save(account);
            if(a!=null)
                System.out.println(a.getLogin());
            System.out.println("brak");
        }
}