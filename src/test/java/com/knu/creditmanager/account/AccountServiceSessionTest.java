package com.knu.creditmanager.account;

import com.knu.creditmanager.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class AccountServiceSessionTest {
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountService(accountRepository, passwordEncoder);
    }

    @Test
    public void authenticateWithValid() {
        String studentId = "1234";
        String password = "1234";

        Account mockAccount = Account.builder()
                .studentId("1234").build();

        given(accountRepository.findByStudentId(studentId))
                .willReturn(Optional.of(mockAccount));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        Account account = accountService.authenticate(studentId, password);
        assertThat(account.getStudentId(), is(studentId));
    }

}