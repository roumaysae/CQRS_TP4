package com.roumaysae.comptecqrseventsourcing.commonapi.exceptions;

public class AmountNegativeException extends RuntimeException {
    public AmountNegativeException(String message) {
        super(message);
    }
}
