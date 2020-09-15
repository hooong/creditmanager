package com.knu.creditmanager.account;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.exception.PasswordWrongException;
import com.knu.creditmanager.exception.StudentIdExistedException;
import com.knu.creditmanager.exception.StudentIdNotExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않음."));

        return account;
    }

    public Account registerAccount(RegisterAccountDto registerAccountDto) {
        // TODO: Dto 만들고 회원가입 폼에서 받아오는 값 설정
        if(checkExistence(registerAccountDto.getStudentId())) {
            throw new StudentIdExistedException(registerAccountDto.getStudentId());
       }

        String encodedPassWord = passwordEncoder.encode(registerAccountDto.getPassword());

        Account account = Account.builder()
                .studentId(registerAccountDto.getStudentId())
                .password(encodedPassWord)
                .name(registerAccountDto.getName()).build();

        return accountRepository.save(account);
    }

    // TODO: 회원가입시 valid 구현

    // 학번 존재 확인 메서드
    private boolean checkExistence(String studentId) {
        Account account = accountRepository.findByStudentId(studentId).orElse(null);

        return account != null;
    }

    @Transactional
    public Account delete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new StudentIdNotExistedException());

        account.setDeleted(true);
        return accountRepository.save(account);
    }

    public Account authenticate(String studentId, String password) {
        Account account = accountRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentIdNotExistedException());

        if(!passwordEncoder.matches(password, account.getPassword())) {
            throw new PasswordWrongException();
        }

        return account;
    }
}
