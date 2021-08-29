package com.assessment.solve.mastercard;

import com.assessment.solve.mastercard.Controller.AccountController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MastercardApplicationTests {

	@Autowired
	private AccountController accountController;

	@Test
	void contextLoads() {
		Assertions.assertThat(accountController).isNot(null);
	}

}
