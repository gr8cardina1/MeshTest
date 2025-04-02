package com.meshgroup.meshtask.controller;

import com.meshgroup.meshtask.UserApiDelegate;
import com.meshgroup.meshtask.auth.service.AuthService;
import com.meshgroup.meshtask.auth.service.JwtUtils;
import com.meshgroup.meshtask.logic.PhoneOperations;
import com.meshgroup.meshtask.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApiDelegate {
    private final AuthService authService;
    private final PhoneOperations phoneOperations;


    public ResponseEntity<UserTokenResponseSchema> userGenerateJwt(UserTokenRequestSchema userRequestSchema) {
        return ResponseEntity.ok(new UserTokenResponseSchema(JwtUtils.getNewJwtToken(userRequestSchema.getUserId())));
    }

    public ResponseEntity<UserAddPhoneResponseSchema> userAddPhone(UserAddPhoneRequestSchema userAddPhoneRequestSchema) {
        return ResponseEntity.ok(phoneOperations.addPhone(userAddPhoneRequestSchema.getPhone(), authService.getClaimUserId()));
    }

    public ResponseEntity<UserChangePhoneResponseSchema> userChangePhone(UserChangePhoneRequestSchema userChangePhoneRequestSchema) {
        return ResponseEntity.ok(phoneOperations.changePhone(userChangePhoneRequestSchema.getId(), userChangePhoneRequestSchema.getPhone(), authService.getClaimUserId()));
    }

    public ResponseEntity<Void> userDeletePhone(Long id) {
        phoneOperations.deleteByPhoneId(id, authService.getClaimUserId());
        return ResponseEntity.ok().build();
    }

}
