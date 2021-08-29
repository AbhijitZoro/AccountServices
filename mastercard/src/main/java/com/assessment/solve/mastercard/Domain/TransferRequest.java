package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
/**
 * POJO class for storing the data required for making money transfer.
 * @auhor Abhijit Giri
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private String fromAccountNo;
    private String toAccountNo;
    private Double amount;
}
