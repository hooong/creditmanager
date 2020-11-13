package com.knu.creditmanager.curriculum;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService curriculumService;

    @PostMapping("/api/curriculums")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCurriculum(Curriculum curriculum) throws URISyntaxException {
        Long curId = curriculumService.createCurriculum(curriculum);

        URI location = new URI("/api/curriculums/" + curId);
        return ResponseEntity.created(location).body("{\"message\": \"Success Created\"}");
    }

}
