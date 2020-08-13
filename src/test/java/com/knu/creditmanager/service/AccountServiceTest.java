package com.knu.creditmanager.service;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        assertThat(accounts.get(0).getName()).isEqualTo("hong");

        log.info("accounts : {}", accountRepository.findAll());
    }

}