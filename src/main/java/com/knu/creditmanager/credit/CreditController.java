package com.knu.creditmanager.credit;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public Credit getCredit(
            Authentication authentication) {
        Claims claims = (Claims) authentication.getPrincipal();
        String studentId = claims.get("studentId", String.class);

        return creditService.findCredit(studentId);
    }

}
