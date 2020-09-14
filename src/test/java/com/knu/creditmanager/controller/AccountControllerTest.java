package com.knu.creditmanager.controller;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.domain.StudentIdExistedException;
import com.knu.creditmanager.repository.AccountRepository;
import com.knu.creditmanager.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllAccounts() throws Exception {
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.builder()
                .id(1L)
                .studentId("201513501")
                .name("hong").build());

        given(accountService.getAllAccounts()).willReturn(accountList);

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hong")));
    }

    @Test
    public void getAccount() throws Exception {
        Account account = Account.builder()
                .id(1L)
                .studentId("201513501")
                .name("hong").build();

        given(accountService.getAccount(1L)).willReturn(account);

        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hong")));
    }

    @Test
    public void signUpWithValidData() throws Exception {
        given(accountService.registerAccount(any())).will(invocation -> {
            Account account = invocation.getArgument(0);
            return Account.builder()
                    .id(1L)
                    .studentId("1234")
                    .name("seokjun").build();
        });

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"name\": \"seokjun\", \"stdentId\" : \"1234\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Success")));
        verify(accountService).registerAccount(any());
    }

    @Test
    public void signUpWithExistence() throws Exception {
        given(accountService.registerAccount(any())).willThrow(new StudentIdExistedException("1234"));

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"name\": \"seokjun\", \"studentId\" : \"1111\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAccount() throws Exception {
        Account account = Account.builder()
                .id(1L)
                .studentId("201513501")
                .name("hong").build();

        given(accountService.delete(1L)).willReturn(account);

        mockMvc.perform(delete("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }
}