package com.meshgroup.meshtask.controller;

import com.meshgroup.meshtask.UserApiDelegate;
import com.meshgroup.meshtask.auth.service.JwtUtils;
import com.meshgroup.meshtask.logic.PhoneOperations;
import com.meshgroup.meshtask.logic.UserOperations;
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


    public ResponseEntity<UserTokenResponseSchema> userGenerateJwt(UserTokenRequestSchema userRequestSchema) {
        return ResponseEntity.ok(new UserTokenResponseSchema(JwtUtils.getNewJwtToken(userRequestSchema.getUserId())));
    }

}
