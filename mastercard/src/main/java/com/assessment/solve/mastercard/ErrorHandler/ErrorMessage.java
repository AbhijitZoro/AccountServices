package com.assessment.solve.mastercard.ErrorHandler;

import org.springframework.stereotype.Component;

/**
 * Constant class to store all the message that needs to be show to user / client.
 */
@Component
public class ErrorMessage {
    public static final String INVALID_CURRENCY_TYPE = "Invalid currency type selected";
    public static final String ACCOUNT_ALREADY_EXITS= "The Account number already exists";
    public static final String INVALID_ACCOUNT_NO = "Invalid account number";
    public static final String GENERAL_ERROR = "General Error . Please try again";
    public static final String NO_ACCOUNT_EXITS = "Invalid account number";
    public static final String INVALID_AMOUNT = "Transfer Amount Not valid";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds available";
    public static final String UNAVAILABLE_DATA = "No transactions available";
}
