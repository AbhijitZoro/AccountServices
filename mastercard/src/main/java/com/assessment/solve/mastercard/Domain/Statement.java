package com.assessment.solve.mastercard.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;
/**
 * POJO class for storing and masking the data received from Transaction table.
 * Will be further used to make the transaction list.
 * @auhor Abhijit Giri
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Statement {
    private Double amount;
    private String currency;
    private String type;
    private Date transactionDate;
    private String AccountNo;
}
