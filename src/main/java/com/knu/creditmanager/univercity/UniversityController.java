package com.knu.creditmanager.univercity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping
    public ResponseEntity<?> createUniv(
            @RequestBody List<University> resource) throws URISyntaxException {
        for (University univ: resource) {
            universityService.registUniversity(univ);
        }

        return ResponseEntity.created(new URI("")).body("{\"message\": \"Success Created\"}");
    }

    @GetMapping
    public List<University> getAllUniv() {
        return universityService.getAllUniversity();
    }
}
