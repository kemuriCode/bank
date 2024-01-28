package com.bank.bank.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAccount_endpointTest() throws Exception {
        mockMvc.perform(post("/accounts/create")
                .contentType("application/json")
                .content("{\"pesel\": \"12345678901\", \"balance\": \"100.00\", \"currency\": \"PLN\", \"firstName\": \"Jan\", \"lastName\": \"Kowalski\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pesel").value("12345678901"));
    }

    @Test
    public void getAccountById_endpointTest() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void getAccountsByBalanceGreaterThan_endpointTest() throws Exception {
        mockMvc.perform(get("/accounts/balance-greater-than/100.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].balance").value("250.00"));
    }

}
