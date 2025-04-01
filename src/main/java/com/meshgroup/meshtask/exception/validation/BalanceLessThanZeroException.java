package com.meshgroup.meshtask.exception.validation;

public class BalanceLessThanZeroException extends RuntimeException {
    public BalanceLessThanZeroException(String message) {
        super(message);
    }
}
