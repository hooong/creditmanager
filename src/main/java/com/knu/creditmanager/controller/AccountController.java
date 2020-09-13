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
    public void signUp(@RequestBody Account resource){
        accountService.create(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

}
