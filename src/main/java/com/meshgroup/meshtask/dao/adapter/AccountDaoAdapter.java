package com.meshgroup.meshtask.dao.adapter;

import com.meshgroup.meshtask.dao.jdbc.AccountRepository;
import com.meshgroup.meshtask.exception.validation.BalanceLessThanZeroException;
import com.meshgroup.meshtask.model.dao.AccountDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                        .balance(BigDecimal.valueOf(0))
                        .build()
        );
    }

    public AccountDao addBalance(Long id, double balance) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (!accountDao.isEmpty())
            accountDao.get().setBalance(
                    accountDao.get().getBalance().add(BigDecimal.valueOf(balance))
            );
        return accountRepository.save(accountDao.get());
    }

    public AccountDao subtractBalance(Long id, double balance) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        if (!accountDao.isEmpty()) {
            if (accountDao.get().getBalance().subtract(BigDecimal.valueOf(balance)).doubleValue() < 0) {
                throw new BalanceLessThanZeroException(EV_AmountIsTooBig);
            }
            accountDao.get().setBalance(
                    accountDao.get().getBalance().subtract(BigDecimal.valueOf(balance))
            );
            return accountRepository.save(accountDao.get());
        }
        return null;
    }
}