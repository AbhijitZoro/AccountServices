package com.assessment.solve.mastercard.ControllerTest;

import com.assessment.solve.mastercard.Controller.AccountController;
import com.assessment.solve.mastercard.Domain.Account;
import com.assessment.solve.mastercard.Domain.CreateAccountRequest;
import com.assessment.solve.mastercard.ErrorHandler.ErrorAdvice;
import com.assessment.solve.mastercard.Repository.AccountRepository;
import com.assessment.solve.mastercard.Service.AccountService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({AccountController.class})
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    public void setup(){

        MockitoAnnotations.initMocks(this);

        this.mockMvc= MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new ErrorAdvice()).build();
    }

    @Test
    void verifyCreateAccountWithNoData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create-account").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void verifyCreateAccountWithData() throws Exception {
        CreateAccountRequest car = new CreateAccountRequest();
        car.setAccountNo("111");
        car.setCurrentBalance(1000.0);
        car.setCurrency("NOK");

        Account acc = new Account();
        acc.setAccountNo("111");
        acc.setAccountId(1);
        acc.setCurrentBalance(10000.0);
        acc.setCurrency("NOK");

        when(accountRepository.save(acc)).thenReturn(acc);
        when(accountService.createAccount(car)).thenReturn(acc);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create-account")
                .content(car.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
