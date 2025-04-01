package com.meshgroup.meshtask.logic;

import com.meshgroup.meshtask.dao.adapter.AccountDaoAdapter;
import com.meshgroup.meshtask.exception.validation.BalanceLessThanZeroException;
import com.meshgroup.meshtask.model.AccountAddBalanceResponseSchema;
import com.meshgroup.meshtask.model.dao.AccountDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import static com.meshgroup.meshtask.exception.messages.ValidationExceptionMessages.EV_BalanceLessThanZero;
import static com.meshgroup.meshtask.exception.messages.ValidationExceptionMessages.EV_TargetAccountDoesNotExist;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountOperations {
    private final AccountDaoAdapter accountDaoAdapter;


    public AccountAddBalanceResponseSchema addBalance(Long id, double balance) {
        if (balance <= 0) {
            log.error(EV_BalanceLessThanZero);
            throw new BalanceLessThanZeroException(EV_BalanceLessThanZero);
        }
        AccountDao accountDao = accountDaoAdapter.addBalance(id, balance);
        return new AccountAddBalanceResponseSchema(accountDao.getId(), accountDao.getUserId(), accountDao.getBalance());
    }

    public AccountDao subtractBalance(Long id, double balance) {
        if (balance <= 0) {
            log.error(EV_BalanceLessThanZero);
            throw new BalanceLessThanZeroException(EV_BalanceLessThanZero);
        }
        return accountDaoAdapter.subtractBalance(id, balance);
    }

    public void transfer(Long idSrc, Long idTarget, double amount) {
        subtractBalance(idSrc, amount);
        try {
            addBalance(idTarget, amount);
        } catch (NoSuchElementException e) {
            log.error(EV_TargetAccountDoesNotExist);
            addBalance(idSrc, amount);
        }
        log.error("Transfer amount {} from {} to {} completed successfully", amount, idSrc, idTarget);
    }
}
