package com.assessment.solve.mastercard.Controller;

import com.assessment.solve.mastercard.Domain.*;
import com.assessment.solve.mastercard.ErrorHandler.AccountServiceExcpetion;
import com.assessment.solve.mastercard.ErrorHandler.ErrorMessage;
import com.assessment.solve.mastercard.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main Controller for Create/View/Transfer services on the account .
 * Contains all the request mapping and first level validation.
 * @auhor Abhijit Giri
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/accounts",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ErrorMessage errorMessage;

    /**
     * Accept the Json data from client and create an account in database.
     * @param account Valid account details should be entered.
     * @return Account details on successful creation of account.
     * @throws AccountServiceExcpetion
     */
    @PostMapping("/create-account")
    public ResponseEntity<Account> createNewAccount(@RequestBody CreateAccountRequest account) throws AccountServiceExcpetion {

        String[] currencyList = {"NOK", "INR", "GBP", "USD", "EUR"};

        if (account.getAccountNo().isEmpty() || account.getAccountNo().equals("") || account.getAccountNo().equals(" ") || account.getAccountNo().length() < 3) {
            throw new AccountServiceExcpetion(errorMessage.INVALID_ACCOUNT_NO);
        }
        if (account.getCurrentBalance() <= 0) {
            throw new AccountServiceExcpetion(errorMessage.INSUFFICIENT_FUNDS);
        }
        if (!Arrays.toString(currencyList).contains(account.getCurrency()) || account.getCurrency().length() < 3) {
            throw new AccountServiceExcpetion(errorMessage.INVALID_CURRENCY_TYPE);
        }
        Account createAccountResponse = accountService.createAccount(account);
        return ResponseEntity.ok().body(createAccountResponse);
    }

    /**
     * Fetching the account details of the user if exists in database.
     * @param accountNo - Should be a valid account no and an existing customer
     * @return Balance response
     * @throws AccountServiceExcpetion
     */
    @GetMapping("/{accountNo}/balance")
    public ResponseEntity<BalanceResponse> fetchBalance(@PathVariable String accountNo) throws AccountServiceExcpetion {
        if (isInValidAccount(accountNo)){
            throw new AccountServiceExcpetion(errorMessage.INVALID_ACCOUNT_NO);
        }
        Account newAccount = accountService.getAccountDetails(accountNo);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountId(newAccount.getAccountId());
        balanceResponse.setBalance(newAccount.getCurrentBalance());
        balanceResponse.setCurrency(newAccount.getCurrency());
        return ResponseEntity.ok().body(balanceResponse);
    }

    /**
     * Accepts the account transfer request if valid to/from account no is provided
     * @param transferRequest Contains the from/to account and the amount needs to transferred
     * @return Transfer Successful message
     * @throws AccountServiceExcpetion
     */
    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferMoney(@RequestBody TransferRequest transferRequest) throws AccountServiceExcpetion {
        if (isInValidAccount(transferRequest.getFromAccountNo())||isInValidAccount(transferRequest.getToAccountNo())){
            throw new AccountServiceExcpetion(errorMessage.INVALID_ACCOUNT_NO);
        }
        accountService.transferMoney(transferRequest);
        TransferResponse transferResponse = new TransferResponse();
        String message = "Amount " + transferRequest.getAmount() + " Successfully " + "credited to account: "
                + transferRequest.getToAccountNo();
        transferResponse.setMessage(message);
        return ResponseEntity.ok().body(transferResponse);
    }

    /**
     * Accept the account no and send the list of DEBIT/CREDIT transactions performed on it.
     * @param accountId - Valid account no is accepted
     * @return List of transactions for the account
     * @throws AccountServiceExcpetion
     */
    @GetMapping("/{accountId}/statements/mini")
    public ResponseEntity<AccountStatement> fetchMiniStatement(@PathVariable String accountId) throws AccountServiceExcpetion {
        if (isInValidAccount(accountId)) {
            throw new AccountServiceExcpetion(errorMessage.INVALID_ACCOUNT_NO);
        }
        Statement response = new Statement();
        AccountStatement accountStatement = accountService.getStatement(accountId);
        return ResponseEntity.created(URI.create(String.format("/Transfer/%s", accountId))).body(accountStatement);
    }
    /**
     * Will be used to check if the account no is valid .
     * @param inputValue account no
     * @return True/False
     * @throws AccountServiceExcpetion
     */
    private Boolean isInValidAccount(String inputValue) throws AccountServiceExcpetion {
        Boolean result = false;
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-];");
        Matcher hasSpecial = special.matcher(inputValue);
        result = hasSpecial.find();
        if (inputValue.isEmpty() || inputValue.equals("") || inputValue.equals(" ") || inputValue.length() < 3) {
            result = true ;
        }
        return result;
    }




}
