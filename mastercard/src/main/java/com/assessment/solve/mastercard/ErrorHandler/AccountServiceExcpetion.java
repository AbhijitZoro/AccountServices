package com.assessment.solve.mastercard.ErrorHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception handler class to send customized response status code and message.
 * It will catch exceptions and attach a customized business message to it.
 */
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class AccountServiceExcpetion extends Exception {
    private static final long serialVersionUID = 1L;
    public AccountServiceExcpetion(String errorMessage) {
        super(errorMessage);
    }
}
