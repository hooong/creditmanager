package com.knu.creditmanager.curriculum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;

    public Curriculum getCurriculum(String year){
        return curriculumRepository.findByCurriculumYear(year).orElse(null);
    }
}
