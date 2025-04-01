package com.meshgroup.meshtask.dao.adapter;

import com.meshgroup.meshtask.dao.jdbc.AccountRepository;
import com.meshgroup.meshtask.exception.validation.BalanceLessThanZeroException;
import com.meshgroup.meshtask.model.dao.AccountDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.meshgroup.meshtask.exception.messages.ValidationExceptionMessages.EV_AmountIsTooBig;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountDaoAdapter {
    private final AccountRepository accountRepository;

    public AccountDao addAccount(Long userId) {
        return accountRepository.save(
                AccountDao.builder()
                        .userId(userId)
                        .balance(0)
                        .build()
        );
    }

    public AccountDao addBalance(Long id, double balance) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (!accountDao.isEmpty())
            accountDao.get().setBalance(accountDao.get().getBalance() + balance);
        return accountRepository.save(accountDao.get());
    }

    public AccountDao subtractBalance(Long id, double balance) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (!accountDao.isEmpty()) {
            if (accountDao.get().getBalance() - balance < 0) {
                throw new BalanceLessThanZeroException(EV_AmountIsTooBig);
            }
            accountDao.get().setBalance(accountDao.get().getBalance() - balance);
            return accountRepository.save(accountDao.get());
        }
        return null;
    }
}