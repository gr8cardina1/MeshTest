package com.meshgroup.meshtask.dao.adapter;

import com.meshgroup.meshtask.dao.jdbc.PhoneDataRepository;
import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneDataDaoAdapter {
    private final PhoneDataRepository phoneDataRepository;

    public void deletePhone(String phoneNumber, Long userId) {
        if (phoneDataRepository.countByUserId(userId) > 1)
            phoneDataRepository.deleteByPhoneAndUserId(phoneNumber, userId);
    }

    public PhoneDataDao changePhone(String phoneNumber, String newPhoneNumber, Long userId) {
        Optional<PhoneDataDao> phone = phoneDataRepository.findByPhoneNumberAndUserId(phoneNumber, userId);
        if (phone.isPresent()) {
            phone.get().setPhone(newPhoneNumber);
            return phoneDataRepository.save(phone.get());
        } else {
            return null;
        }
    }

    public PhoneDataDao addPhone(String phoneNumber, Long userId) {
        return phoneDataRepository.save(
                PhoneDataDao.builder()
                        .userId(userId)
                        .phone(phoneNumber)
                        .build()
        );
    }
}
