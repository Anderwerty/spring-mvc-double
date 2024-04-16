package org.example.bank.service.exception;

public class RegistrationRuntimeException extends RuntimeException {
    public RegistrationRuntimeException() {
    }

    public RegistrationRuntimeException(String message) {
        super(message);
    }
}
