package com.meshgroup.meshtask.dao.adapter;

import com.meshgroup.meshtask.dao.jdbc.UserRepository;
import com.meshgroup.meshtask.model.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDaoAdapter {
    private final UserRepository userRepository;

    public UserDao save(UserDao userDao) {
        return userRepository.save(userDao);
    }

    public void deleteByUserId(Long userId) {
        userRepository.deleteByUserId(userId);
    }
}
