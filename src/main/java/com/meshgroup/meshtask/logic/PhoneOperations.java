package com.meshgroup.meshtask.logic;

import com.meshgroup.meshtask.dao.adapter.PhoneDataDaoAdapter;
import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneOperations {
    private final PhoneDataDaoAdapter phoneDataDaoAdapter;

    public void deleteByNumberAndUserId(String phoneNumber, Long userId) {
        phoneDataDaoAdapter.deletePhone(phoneNumber, userId);
    }

    public PhoneDataDao changePhone(String phoneNumber, String newPhoneNumber, Long userId) {
        return phoneDataDaoAdapter.changePhone(phoneNumber, newPhoneNumber, userId);
    }

    public PhoneDataDao addPhone(String phoneNumber, Long userId) {
        return phoneDataDaoAdapter.addPhone( phoneNumber,  userId);
    }



}
