package com.leavemanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Medion on 2016-08-15.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
