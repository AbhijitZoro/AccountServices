package com.assessment.solve.mastercard.ErrorHandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Handler class that maps the code and message for the exception.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ErrorMessageHandler {
    private int statusCode;
    private String errorMessage;

    public ErrorMessageHandler(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
