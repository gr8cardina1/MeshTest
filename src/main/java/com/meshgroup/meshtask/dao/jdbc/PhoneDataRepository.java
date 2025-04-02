package com.meshgroup.meshtask.dao.jdbc;

import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneDataDao, Long> {
    @Query(nativeQuery = true, value="SELECT COUNT(ID) FROM phone_data WHERE user_id = :userId")
    Integer countByUserId(Long userId);

    @Query(nativeQuery = true, value="SELECT COUNT(ID) FROM phone_data WHERE phone = :phoneNumber")
    Integer findByPhone(String phoneNumber);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="DELETE FROM phone_data WHERE phone=:phoneNumber AND user_id=:userId")
    void deleteByPhoneAndUserId(String phoneNumber, Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="DELETE FROM phone_data WHERE id=:id")
    void deletePhoneById(Long id);

    @Query(nativeQuery = true, value="SELECT * FROM phone_data WHERE id=:id")
    Optional<PhoneDataDao> findByPhoneById(Long id);
}
