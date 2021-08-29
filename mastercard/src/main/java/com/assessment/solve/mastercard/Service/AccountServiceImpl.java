package com.assessment.solve.mastercard.Service;

import com.assessment.solve.mastercard.Domain.*;
import com.assessment.solve.mastercard.ErrorHandler.AccountServiceExcpetion;
import com.assessment.solve.mastercard.ErrorHandler.ErrorMessage;
import com.assessment.solve.mastercard.Repository.AccountRepository;
import com.assessment.solve.mastercard.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation class to perform operations on all the methods of interface.
 * Contains all the logic for services.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ErrorMessage errorMessage;

    /**
     * Accepts the details and store them in database for account creation.
     * @param account
     * @return Account Details
     * @throws AccountServiceExcpetion
     */
    @Override
    public Account createAccount(CreateAccountRequest account) throws AccountServiceExcpetion {
        if (accountRepository.getAccountByAccountNo(account.getAccountNo()) != null) {
            throw new AccountServiceExcpetion(ErrorMessage.ACCOUNT_ALREADY_EXITS);
        }
        Account account1 = new Account();
        account1.setAccountNo(account.getAccountNo());
        account1.setCurrency(account.getCurrency());
        account1.setCurrentBalance(account.getCurrentBalance());
        return accountRepository.save(account1);
    }

    /**
     * Accept the account no and returns the list of details for the account if it exists and is valid.
     * @param accountNo
     * @return
     * @throws AccountServiceExcpetion
     */
    @Override
    public Account getAccountDetails(String accountNo) throws AccountServiceExcpetion {
        Account account = new Account();
        account = accountRepository.getAccountByAccountNo(accountNo);
        if (account == null) {
            throw new AccountServiceExcpetion(errorMessage.NO_ACCOUNT_EXITS);
        }
        return account;
    }

    /**
     * Contains the logic to transfer money bewteen two accounts if they both are valid .
     * @param transferRequest
     * @return Success Message
     * @throws AccountServiceExcpetion
     */
    @Override
    public Transactions transferMoney(TransferRequest transferRequest) throws AccountServiceExcpetion {
        String fromAccountNo = transferRequest.getFromAccountNo();
        String toAccountNo = transferRequest.getToAccountNo();
        Double amount = transferRequest.getAmount();

        if (amount <= 0) {
            throw new AccountServiceExcpetion(errorMessage.INVALID_AMOUNT);
        }

        Account fromAccount = accountRepository.getAccountByAccountNo(fromAccountNo);
        if (fromAccount == null) {
            throw new AccountServiceExcpetion("Sender " + errorMessage.NO_ACCOUNT_EXITS + " : " + fromAccountNo);
        }
        Account toAccount = accountRepository.getAccountByAccountNo(toAccountNo);
        if (toAccount == null) {
            throw new AccountServiceExcpetion("Receiver " + errorMessage.NO_ACCOUNT_EXITS + " : " + toAccountNo);
        }
        Double initialFromAmount = fromAccount.getCurrentBalance();
        Double initialToAmount = fromAccount.getCurrentBalance();

        if ((initialFromAmount - amount) < 0) {
            throw new AccountServiceExcpetion(errorMessage.INSUFFICIENT_FUNDS);
        }
        fromAccount.setCurrentBalance(fromAccount.getCurrentBalance() - amount);
        accountRepository.save(fromAccount);
        toAccount.setCurrentBalance(toAccount.getCurrentBalance() + amount);
        accountRepository.save(toAccount);

        Transactions transactionsFromAccount = new Transactions();
        Transactions transactionsToAccount = new Transactions();
        transactionsFromAccount.setAccountNo(fromAccountNo);
        transactionsFromAccount.setAmount(amount);
        transactionsFromAccount.setCurrency(fromAccount.getCurrency());
        transactionsFromAccount.setTransactionDate(new Date());
        transactionsFromAccount.setType("DEBIT");
        transactionsFromAccount.setToAccountNo(toAccountNo);

        transactionRepository.save(transactionsFromAccount);

        transactionsToAccount.setAccountNo(toAccountNo);
        transactionsToAccount.setAmount(amount);
        transactionsToAccount.setCurrency(toAccount.getCurrency());
        transactionsToAccount.setTransactionDate(new Date());
        transactionsToAccount.setType("CREDIT");
        transactionsToAccount.setToAccountNo(fromAccountNo);

        transactionRepository.save(transactionsToAccount);

        return transactionsFromAccount;
    }

    /**
     * Accepts the account number and return all the transaction performed on or by that account.
     * @param accountNo
     * @return
     * @throws AccountServiceExcpetion
     */
    @Override
    public AccountStatement getStatement(String accountNo) throws AccountServiceExcpetion {
        AccountStatement accountStatement = new AccountStatement();
        List<Statement> statements = new ArrayList<>();

        Account accountCheck = accountRepository.getAccountByAccountNo(accountNo);
        if (accountCheck == null) {
            throw new AccountServiceExcpetion(errorMessage.NO_ACCOUNT_EXITS);
        }

        Stream<Transactions> transactionsStream = transactionRepository.getStatementByAccountNo(accountNo).stream();
        List<Transactions> transactionList = transactionsStream.limit(20).collect(Collectors.toList());

        if (transactionList.isEmpty()) {
            throw new AccountServiceExcpetion(errorMessage.UNAVAILABLE_DATA);
        }

        for (Transactions ts : transactionList) {
            Statement st = new Statement();
            st.setAccountNo(ts.getToAccountNo());
            st.setAmount(ts.getAmount());
            st.setCurrency(ts.getCurrency());
            st.setTransactionDate(ts.getTransactionDate());
            st.setType(ts.getType());
            statements.add(st);
        }
        accountStatement.setMiniStatement(statements);
        return accountStatement;
    }
}
