package com.knu.creditmanager.service;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.domain.StudentIdExistedException;
import com.knu.creditmanager.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();

        assertThat(accounts.get(0).getName()).isEqualTo("hong");

        log.info("accounts : {}", accountRepository.findAll());
    }

    @Test
    void getAccount() {
        Account account = accountService.getAccount(1L);

        assertThat(account.getName()).isEqualTo("hong");
    }

    @Test
    void create() {
        Account account = Account.builder()
                                .name("seokjun")
                                .studentId("1234")
                                .build();

        Long id = accountService.create(account);

        assertThat(id).isEqualTo(4L);
    }

    @Test
    void createWithExistenceStudentId() {
        Account account = Account.builder()
                .name("seokjun")
                .studentId("1111")
                .build();

        Exception exception = assertThrows(
                StudentIdExistedException.class,
                () -> accountService.create(account)
        );

        assertTrue(exception.getMessage().contains("학번"));
    }

}