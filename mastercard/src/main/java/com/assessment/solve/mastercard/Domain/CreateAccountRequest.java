package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Accept the JSON data from client. Store the data that will be used as request for Account creation.
 * @auhor Abhijit Giri
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {
    private String accountNo;
    private Double currentBalance;
    private String currency ;
}
