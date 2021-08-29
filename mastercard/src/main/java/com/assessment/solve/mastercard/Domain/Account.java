package com.assessment.solve.mastercard.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Entity class for creating the data base schema for ACCOUNT table and will be further used for CURD operations.
 * @auhor Abhijit Giri
 */
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private int accountId;
    @Column(name = "accountNo" ,unique=true)
    private String accountNo;
    @Column(name = "currentBalance")
    private Double currentBalance;
    @Column(name = "currency")
    private String currency ;
}
