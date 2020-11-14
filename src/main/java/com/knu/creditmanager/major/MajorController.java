package com.knu.creditmanager.major;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MajorController {

    private final MajorService majorService;

    @GetMapping("/api/majors")
    public List<Major> getAll() {
        return majorService.getAllMajor();
    }

}
