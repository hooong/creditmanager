package com.knu.creditmanager.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Optional<Curriculum> findByCurriculumYear(String year);

    Curriculum findByCurriculumYearAndCurriculumName(String year, String major);
}
