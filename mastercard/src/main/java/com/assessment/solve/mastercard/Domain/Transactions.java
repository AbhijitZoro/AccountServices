package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
/**
 * Entity class for creating the data base schema of TRANSACTION table and will be further used for CURD operations.
 * @auhor Abhijit Giri
 */
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private int transactionId;
    @Column(name = "accountNo")
    private String accountNo;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "type")
    private String type;
    @Column(name = "transactionDate")
    private Date transactionDate;
    @Column(name = "toAccountNo")
    private String toAccountNo;
}
