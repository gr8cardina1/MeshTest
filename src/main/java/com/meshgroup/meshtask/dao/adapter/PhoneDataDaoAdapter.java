package com.meshgroup.meshtask.dao.adapter;

import com.meshgroup.meshtask.dao.jdbc.PhoneDataRepository;
import com.meshgroup.meshtask.exception.validation.EntityAlreadyExists;
import com.meshgroup.meshtask.exception.validation.LessRecordsException;
import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.meshgroup.meshtask.exception.messages.ValidationExceptionMessages.EV_NotLessThanOneRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneDataDaoAdapter {
    private final PhoneDataRepository phoneDataRepository;

    public void deletePhoneById(Long id, Long userId) {
        if (phoneDataRepository.countByUserId(userId) > 1) {
            phoneDataRepository.deletePhoneById(id);
        } else {
            throw new LessRecordsException(EV_NotLessThanOneRecord);
        }
    }

    public PhoneDataDao changePhone(Long id, String newPhoneNumber, Long userId) {
        if (phoneDataRepository.findByPhone(newPhoneNumber)>0) {
            throw new EntityAlreadyExists("Phone number already exists in DB");
        }

        Optional<PhoneDataDao> phone = phoneDataRepository.findByPhoneById(id);
        if (phone.isPresent()) {
            phone.get().setPhone(newPhoneNumber);
            return phoneDataRepository.save(phone.get());
        } else {
            return null;
        }
    }

    public PhoneDataDao addPhone(String phoneNumber, Long userId) {
        if (phoneDataRepository.findByPhone(phoneNumber)>0) {
            throw new EntityAlreadyExists("Phone number already exists in DB");
        }
        return phoneDataRepository.save(
                PhoneDataDao.builder()
                        .userId(userId)
                        .phone(phoneNumber)
                        .build()
        );
    }
}
