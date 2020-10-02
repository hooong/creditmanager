package com.knu.creditmanager.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByStudentId(String studentId);
    @Query(value = "select * from Account account where account.is_deleted = true", nativeQuery = true)
    List<Account> findAccountDeleted();
}
