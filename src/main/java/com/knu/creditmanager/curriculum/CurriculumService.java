package com.knu.creditmanager.curriculum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;

    public Curriculum getCurriculum(Long id){
        return curriculumRepository.findById(id).orElse(null);
    }

    public Curriculum getCurriculumByMajorAndYear(String major, String year) {
        return curriculumRepository.findByCurriculumNameAndCurriculumYear(major, year);
    }

    public Long createCurriculum(Curriculum curriculum) {
        Curriculum cur = curriculumRepository.save(curriculum);

        return cur.getId();
    }
}
