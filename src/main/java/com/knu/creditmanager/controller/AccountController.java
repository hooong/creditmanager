package com.knu.creditmanager.controller;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.domain.StudentIdExistedException;
import com.knu.creditmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/api/accounts")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAccountslist() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUp(@RequestBody Account resource) throws URISyntaxException {
        Account account = accountService.registerAccount(resource);

        URI location = new URI("/api/accounts/" + account.getId());
        return ResponseEntity.created(location).body("{\"message\": \"Success Created\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) throws URISyntaxException {
        // TODO: 이메일이 존재하는지 검사

        Account account = accountService.delete(id);

        URI location = new URI("/api/accounts/" + account.getId());
        return ResponseEntity.ok().body("{\"message\" : \"Success Delete\"}");
    }

}
