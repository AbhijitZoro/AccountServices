package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * POJO class for sending the account details,
 * @author abhijitgiri
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BalanceResponse {
    private int accountId ;
    private Double balance;
    private String currency ;
}
