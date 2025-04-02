package com.meshgroup.meshtask.logic;

import com.meshgroup.meshtask.dao.adapter.PhoneDataDaoAdapter;
import com.meshgroup.meshtask.model.UserAddPhoneResponseSchema;
import com.meshgroup.meshtask.model.UserChangePhoneResponseSchema;
import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneOperations {
    private final PhoneDataDaoAdapter phoneDataDaoAdapter;

    public void deleteByPhoneId(Long id, Long userId) {
        phoneDataDaoAdapter.deletePhoneById(id,userId); // fix from claim
    }

    public UserChangePhoneResponseSchema changePhone(Long id, String newPhoneNumber, Long userId) {
        PhoneDataDao pDD = phoneDataDaoAdapter.changePhone(id, newPhoneNumber, userId);
        return new UserChangePhoneResponseSchema(pDD.getId(), pDD.getUserId(), pDD.getPhone());
    }

    public UserAddPhoneResponseSchema addPhone(String phoneNumber, Long userId) {
        PhoneDataDao pDD = phoneDataDaoAdapter.addPhone( phoneNumber,  userId);
        return new UserAddPhoneResponseSchema(pDD.getId(), pDD.getUserId(), pDD.getPhone());
    }



}
