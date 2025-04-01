package com.meshgroup.meshtask.dao.jdbc;

import com.meshgroup.meshtask.model.dao.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="DELETE FROM \"user\" WHERE id = :userId")
    void deleteByUserId(Long userId);

}
