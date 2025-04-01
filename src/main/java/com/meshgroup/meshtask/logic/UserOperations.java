package com.meshgroup.meshtask.logic;

import com.meshgroup.meshtask.dao.adapter.UserDaoAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOperations {
    private final UserDaoAdapter userDaoAdapter;

    public void deleteUser(Long userId) {
        userDaoAdapter.deleteByUserId(userId);
    }

}
