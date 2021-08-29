package com.assessment.solve.mastercard.Repository;

import com.assessment.solve.mastercard.Domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for carrying out all the activities towards database
 * The table impacted is TRANSACTION table.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    /**
     * Customized query to accept the account number as input and fetch all the details for it from TRANSACTION table.
     * @param accountNo
     * @return - Transaction details for the account
     */
    List<Transactions> getStatementByAccountNo(String accountNo);
}
