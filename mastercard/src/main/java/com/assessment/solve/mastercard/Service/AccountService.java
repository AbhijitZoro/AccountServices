package com.assessment.solve.mastercard.Service;

import com.assessment.solve.mastercard.Domain.*;
import com.assessment.solve.mastercard.ErrorHandler.AccountServiceExcpetion;

/**
 * The interface layers for the defining the behaviours that all implementation classes has to follow.
 */
public interface AccountService {
    /**
     * Abstract method for account creation.
     * @param account
     * @return
     * @throws AccountServiceExcpetion
     */
    public Account createAccount(CreateAccountRequest account) throws AccountServiceExcpetion;

    /**
     * Abstract method for fetching account details.
     * @param accountNo
     * @return
     * @throws AccountServiceExcpetion
     */
    public Account getAccountDetails(String accountNo) throws AccountServiceExcpetion;

    /**
     * Abstract method for making the transfer between two accounts.
     * @param transferRequest
     * @return Successful message
     * @throws AccountServiceExcpetion
     */
    public Transactions transferMoney(TransferRequest transferRequest) throws AccountServiceExcpetion;

    /**
     * Abstract method for getting the list of transactions
     * @param accountNo
     * @return
     * @throws AccountServiceExcpetion
     */
    public AccountStatement getStatement(String accountNo) throws AccountServiceExcpetion;

}
