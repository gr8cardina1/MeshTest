package com.meshgroup.meshtask.controller;

import com.meshgroup.meshtask.AccountApiDelegate;
import com.meshgroup.meshtask.logic.AccountOperations;
import com.meshgroup.meshtask.model.AccountAddBalanceRequestSchema;
import com.meshgroup.meshtask.model.AccountAddBalanceResponseSchema;
import com.meshgroup.meshtask.model.AccountTransferRequestSchema;
import com.meshgroup.meshtask.model.AccountTransferResponseSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApiDelegate {
    private final AccountOperations accountOperations;

    public ResponseEntity<AccountAddBalanceResponseSchema> accountAddBalance(AccountAddBalanceRequestSchema aABRS) {
        return ResponseEntity.ok(accountOperations.addBalance(aABRS.getId(), aABRS.getBalance()));
    }

    public ResponseEntity<AccountTransferResponseSchema> accountTransfer(AccountTransferRequestSchema aTRS) {
        accountOperations.transfer(aTRS.getSrcId(),aTRS.getDstId(), aTRS.getAmount());
        return ResponseEntity.ok().build();
    }
}