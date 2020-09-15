package com.knu.creditmanager.session;

import com.knu.creditmanager.account.AccountRepository;
import com.knu.creditmanager.account.AccountService;
import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    private AccountService accountService;

    @Test
    public void createTokenWithValid() throws Exception {
        String studentId = "1234";
        String password = "1234";
        String name = "hong";

        Account account = Account.builder().studentId(studentId).name(name).build();

        given(accountService.authenticate(studentId, password)).willReturn(account);

        given(jwtUtil.createToken(studentId, name)).willReturn("header.payload.signature");

        mvc.perform(post("/api/session")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\"studentId\":\"1234\", \"password\":\"1234\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/api/session"))
                .andExpect(content().string(
                        containsString("{\"token\":\"header.payload.signature\"}")
                ));
        verify(accountService).authenticate(eq(studentId), eq(password));
    }


}