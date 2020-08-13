package com.knu.creditmanager.controller;

import com.knu.creditmanager.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@SpringBootTest
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountRepository accountRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void getAllAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("hong")));
    }

    @Test
    void getAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("hong")));
    }

    @Test
    void signUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{ \"name\": \"seokjun\", \"stdentId\" : \"1234\"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void signUpWithExistence() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"name\": \"seokjun\", \"studentId\" : \"1111\"}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        log.info("accounts : {}", accountRepository.findAll());
    }

}