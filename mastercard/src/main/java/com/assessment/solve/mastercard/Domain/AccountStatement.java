package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
/**
 * POJO class for sending list of transactions for a particular account.
 * It will be the mini statement for the user account.
 * @auhor Abhijit Giri
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountStatement {
    private List<Statement> miniStatement ;
}
