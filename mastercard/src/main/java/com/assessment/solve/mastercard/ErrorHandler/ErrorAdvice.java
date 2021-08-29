package com.assessment.solve.mastercard.ErrorHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * It will act as single exception handler for multiple service classes and controllers.
 */
@ControllerAdvice
public class ErrorAdvice {

    @Autowired
    private ErrorMessageHandler errorMessageHandler;

    /**
     * Act as a general exception handler occuring anywhere in the project.
     * @param e - Exception trace
     * @return - 418 and message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageHandler> handleGeneralException(Exception e) {
        String msg = e.getMessage();
        if(msg.isEmpty())
            msg = ErrorMessage.GENERAL_ERROR;
        errorMessageHandler.setErrorMessage(msg);
        errorMessageHandler.setStatusCode(HttpStatus.I_AM_A_TEAPOT.value());
        return new ResponseEntity<ErrorMessageHandler>(errorMessageHandler,HttpStatus.I_AM_A_TEAPOT);
    }

    /**
     * Act as the exception handler for all classes where business is needed to be thrown.
     * @param e - Exception details
     * @return 417 and message
     */
    @ExceptionHandler(AccountServiceExcpetion.class)
    public ResponseEntity<ErrorMessageHandler> handleAccountValidationException(AccountServiceExcpetion e){
        String msg = e.getMessage();
        if(msg.isEmpty())
            msg = ErrorMessage.GENERAL_ERROR;
        errorMessageHandler.setErrorMessage(msg);
        errorMessageHandler.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<ErrorMessageHandler>(errorMessageHandler,HttpStatus.EXPECTATION_FAILED);
    }


}
