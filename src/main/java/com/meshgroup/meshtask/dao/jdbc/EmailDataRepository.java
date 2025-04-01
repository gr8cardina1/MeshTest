package com.meshgroup.meshtask.dao.jdbc;

import com.meshgroup.meshtask.model.dao.EmailDataDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailDataDao, Long> {
}
