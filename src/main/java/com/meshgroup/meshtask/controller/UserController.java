package com.meshgroup.meshtask.controller;

import com.meshgroup.meshtask.UserApiDelegate;
import com.meshgroup.meshtask.auth.service.AuthService;
import com.meshgroup.meshtask.auth.service.JwtUtils;
import com.meshgroup.meshtask.logic.AccountOperations;
import com.meshgroup.meshtask.logic.PhoneOperations;
import com.meshgroup.meshtask.logic.UserOperations;
import com.meshgroup.meshtask.model.UserRequestSchema;
import com.meshgroup.meshtask.model.UserResponseSchema;
import com.meshgroup.meshtask.model.UserTokenRequestSchema;
import com.meshgroup.meshtask.model.UserTokenResponseSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApiDelegate {
    private final UserOperations userOperations;
    private final PhoneOperations  phoneOperations;
    private final AccountOperations accountOperations;

    public ResponseEntity<UserTokenResponseSchema> userGenerateJwt(UserTokenRequestSchema userRequestSchema) {
        accountOperations.transferAccount(2L,300L,100);
        // phoneOperations.changePhone("79207865402", "12341234567", 2L);
        return ResponseEntity.ok(new UserTokenResponseSchema(JwtUtils.getNewJwtToken(userRequestSchema.getUserId())));
    }

    public ResponseEntity<UserResponseSchema> addUser(UserRequestSchema userRequestSchema) {
        return  ResponseEntity.ok(new UserResponseSchema(987L));
    }

}
