package com.codingchallenge.creditcardtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
public class CreditCardTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testValidCardForAMEX() throws Exception {
		mockMvc.perform(get("/creditcard/validate/341111111111111")).andDo(print()).andExpect(status().isOk())
				.andExpect(result -> assertEquals("AMEX:341111111111111(valid)",
						result.getResponse().getContentAsString()));
	}

	@Test
	public void testValidCardForDiscover() throws Exception {
		mockMvc.perform(get("/creditcard/validate/6011111111111117")).andDo(print()).andExpect(status().isOk())
				.andExpect(result -> assertEquals("Discover:6011111111111117(valid)",
						result.getResponse().getContentAsString()));
	}

	@Test
	public void testInvalidCard() throws Exception {
		mockMvc.perform(get("/creditcard/validate/9111111111111111")).andDo(print()).andExpect(status().isOk())
				.andExpect(result -> assertEquals("Unknown:9111111111111111(invalid)",
						result.getResponse().getContentAsString()));
	}

}