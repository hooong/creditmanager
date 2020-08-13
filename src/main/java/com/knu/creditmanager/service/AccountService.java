package com.knu.creditmanager.service;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않음."));

        return account;
    }

    @Transactional
    public Long create(Account account) {
        if(checkExistence(account.getStudentId())) {
            throw new StudentIdExistedException(account.getStudentId());
        }

        return accountRepository.save(account).getId();
    }

    // TODO: 회원가입시 valid 구현

    private boolean checkExistence(String studentId) {
        Account account = accountRepository.findByStudentId(studentId).orElse(null);

        return account != null;
    }
}
