package ru.sber.client.processing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BalanceNotFoundException extends RuntimeException {

    public BalanceNotFoundException() {
        super();
    }

    public BalanceNotFoundException(String message) {
        super(message);
    }

    public BalanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BalanceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected BalanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
