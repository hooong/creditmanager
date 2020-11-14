package com.knu.creditmanager.account;

import com.knu.creditmanager.credit.CreditService;
import com.knu.creditmanager.department.DepartmentService;
import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.exception.PasswordWrongException;
import com.knu.creditmanager.exception.StudentIdExistedException;
import com.knu.creditmanager.exception.StudentIdNotExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    private final CreditService creditService;

    @Transactional(readOnly = true)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccount(String studentId) {
        Account account = accountRepository.findByStudentId(studentId).orElseThrow(() -> new StudentIdNotExistedException());

        return account;
    }

    public Account registerAccount(RegisterAccountDto registerAccountDto) {
        if(checkExistence(registerAccountDto.getStudentId())) {
            throw new StudentIdExistedException(registerAccountDto.getStudentId());
       }

        String encodedPassWord = passwordEncoder.encode(registerAccountDto.getPassword());
        Department department = departmentService.getDepartment(registerAccountDto.getMajor());

        Account account = new Account(registerAccountDto.getName(),
                registerAccountDto.getStudentId(),
                encodedPassWord,
                registerAccountDto.getUniYear(),
                registerAccountDto.getSemester(),
                department
                );
        account.setAdmissionYear();

        creditService.firstCreateCredit(account);

        return accountRepository.save(account);
    }

    // TODO: 회원가입시 valid 구현

    // 학번 존재 확인 메서드
    private boolean checkExistence(String studentId) {
        Account account = accountRepository.findByStudentId(studentId).orElse(null);

        return account != null;
    }

    @Transactional
    public Account delete(String studentId) {
        Account account = accountRepository.findByStudentId(studentId).orElseThrow(
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
