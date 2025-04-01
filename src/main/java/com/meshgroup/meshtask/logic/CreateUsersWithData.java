package com.meshgroup.meshtask.logic;

import com.meshgroup.meshtask.dao.jdbc.AccountRepository;
import com.meshgroup.meshtask.dao.jdbc.EmailDataRepository;
import com.meshgroup.meshtask.dao.jdbc.PhoneDataRepository;
import com.meshgroup.meshtask.dao.jdbc.UserRepository;
import com.meshgroup.meshtask.model.dao.AccountDao;
import com.meshgroup.meshtask.model.dao.EmailDataDao;
import com.meshgroup.meshtask.model.dao.PhoneDataDao;
import com.meshgroup.meshtask.model.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUsersWithData {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    private long createUser(String name, String dateOfBirth, String password) {
        var user = UserDao.builder()
                .name(name)
                .dateOfBirth(dateOfBirth)
                .password(password)
                .build();
        return userRepository.save(user).getId();
    }

    private void createAccount(long userId, double balance) {
        var account = AccountDao.builder()
                .userId(userId)
                .balance(balance)
                .build();
        accountRepository.save(account);
    }

    private void createEmail(long userId, String emailAddress) {
        var email = EmailDataDao.builder()
                .userId(userId)
                .email(emailAddress)
                .build();
        emailDataRepository.save(email);
    }

    private void createPhone(long userId, String phoneNumber) {
        var phone = PhoneDataDao.builder()
                .userId(userId)
                .phone(phoneNumber)
                .build();
        phoneDataRepository.save(phone);
    }

    private void createRecord(String name,
                              String dateOfBirth,
                              String password,
                              double balance,
                              String emailAddress,
                              String phoneNumber) {
        long userId = createUser(name, dateOfBirth, password);
        createAccount(userId, balance);
        createEmail(userId, emailAddress);
        createPhone(userId, phoneNumber);
    }


// Unremark line down to populate data
//    @PostConstruct
    private void initTables() {
        Random random = new Random();

        createRecord("user1",
                 "12.04.1984",
                  "password1",
                            random.nextInt(10, 1000),
                "user1@emaildomain.dom",
                "79207865401");

        createRecord("user2",
                "31.07.1985",
                "password2",
                random.nextInt(10, 1000),
                "user2@emaildomain.dom",
                "79207865402");

        createRecord("user3",
                "12.11.1999",
                "password3",
                random.nextInt(10, 1000),
                "user3@emaildomain.dom",
                "79207865403");

        createRecord("user4",
                "04.10.1997",
                "password4",
                random.nextInt(10, 1000),
                "user4@emaildomain.dom",
                "79207865404");

        createRecord("user5",
                "04.10.1981",
                "password5",
                random.nextInt(10, 1000),
                "user5@emaildomain.dom",
                "79207865405");

        createRecord("user6",
                "06.09.1982",
                "password6",
                random.nextInt(10, 1000),
                "user6@emaildomain.dom",
                "79207865406");
    }
}
