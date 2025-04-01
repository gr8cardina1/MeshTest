package com.meshgroup.meshtask.service;

import com.meshgroup.meshtask.dao.jdbc.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceAdderScheduler {
    private final AccountRepository accountRepository;

    ConcurrentHashMap<Long, BigDecimal> oAcc = new ConcurrentHashMap<>();
    @Scheduled(initialDelay = 10, fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void scheduledAddEvery30Sec() {
        accountRepository.findAll().forEach((accountDao) -> {
            double maxBalance = oAcc.get(accountDao.getId()).doubleValue() / 100 * 207;
            double balanceAdded10perc = accountDao.getBalance().doubleValue() * 0.1 + accountDao.getBalance().doubleValue();
            if  (balanceAdded10perc < maxBalance ) {
                accountDao.setBalance(BigDecimal.valueOf(balanceAdded10perc));
                accountRepository.save(accountDao);
            }
        });
    }


    @PostConstruct
    void initBalances() {
        accountRepository.findAll().forEach((accountDao) -> oAcc.put(accountDao.getId(), accountDao.getBalance()));
    }
}
