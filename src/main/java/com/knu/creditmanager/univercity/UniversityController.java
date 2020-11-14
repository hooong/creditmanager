package com.knu.creditmanager.univercity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping
    public ResponseEntity<?> createUniv(
            @RequestBody University resource) throws URISyntaxException {
        University university = universityService.registUniversity(resource);

        URI location = new URI("/api/university/" + university.getId());
        return ResponseEntity.created(location).body("{\"message\": \"Success Created\"}");
    }
}
