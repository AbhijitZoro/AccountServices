package com.assessment.solve.mastercard.Repository;

import com.assessment.solve.mastercard.Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for carrying out all the activities towards database
 * The table impacted is ACCOUNT table.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Customized query to accept the account number as input and fetch all the details for it from Account table.
     * @param accountNo
     * @return - Account details for the account
     */
    Account getAccountByAccountNo(String accountNo);

}
