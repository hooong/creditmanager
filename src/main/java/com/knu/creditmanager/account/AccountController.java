package com.knu.creditmanager.account;

import com.knu.creditmanager.credit.CreditService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/api/accounts")
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountController {

    private final AccountService accountService;
    private final CreditService creditService;

    @GetMapping("/all")
    public List<Account> getAccountslist() {
        return accountService.getAllAccounts();
    }

    @GetMapping
    public Account getAccount(Authentication authentication) {
        Claims claims = (Claims)authentication.getPrincipal();
        String studentId = claims.get("studentId", String.class);

        return accountService.getAccount(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> signUp(@RequestBody RegisterAccountDto resource) throws URISyntaxException {

        Account account = accountService.registerAccount(resource);

        URI location = new URI("/api/accounts/" + account.getId());
        return ResponseEntity.created(location).body("{\"message\": \"Success Created\"}");
    }

    @DeleteMapping("/{studentId}")
    @Transactional
    public ResponseEntity<?> deleteAccount(@PathVariable String studentId) throws URISyntaxException {
        Account account = accountService.delete(studentId);

        URI location = new URI("/api/accounts/" + account.getId());
        return ResponseEntity.ok().body("{\"message\" : \"Success Delete\"}");
    }

}
