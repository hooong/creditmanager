package com.knu.creditmanager.controller;

import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/api/accounts")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAccountslist() {
        return accountService.getAllAccounts();
    }

}
