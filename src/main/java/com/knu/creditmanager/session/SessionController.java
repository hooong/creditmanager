package com.knu.creditmanager.session;

import com.knu.creditmanager.account.AccountService;
import com.knu.creditmanager.domain.Account;
import com.knu.creditmanager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/session")
    public ResponseEntity<SessionResponseDto> createToken(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {
        String studentId = resource.getStudentId();
        String password = resource.getPassword();

        Account account = accountService.authenticate(studentId, password);

        String token = jwtUtil.createToken(
                account.getStudentId(),
                account.getName());

        String url = "/api/session";
        return ResponseEntity.created(new URI(url)).body(
                SessionResponseDto.builder().token(token).build());
    }

}
