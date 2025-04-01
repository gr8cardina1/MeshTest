package com.meshgroup.meshtask.dao.jdbc;

import com.meshgroup.meshtask.model.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, Long> {


}
