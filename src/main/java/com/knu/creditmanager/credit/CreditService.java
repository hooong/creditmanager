package com.knu.creditmanager.credit;

import com.knu.creditmanager.curriculum.Curriculum;
import com.knu.creditmanager.curriculum.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CurriculumRepository curriculumRepository;

    public void saveCredit(String studentId, String year) {
        Curriculum curriculum = curriculumRepository.findByCurriculumYear(year).orElse(null);

        if (curriculum == null) {
            // TODO : 커리큘럼을 찾을 수 없는 에러
        }

        Credit credit = createCredit(studentId, year, curriculum);

        creditRepository.save(credit);
    }

    private Credit createCredit(String studentId, String year, Curriculum curriculum) {
        return Credit.builder()
                .studentId(studentId)
                .curriculumYear(year)
                .balance(curriculum.getBalance())
                .foundation(curriculum.getFoundation())
                .freeChoice(curriculum.getFreeChoice())
                .majorDeepening(curriculum.getMajorDeepening())
                .majorNecessary(curriculum.getMajorNecessary())
                .majorSelection(curriculum.getMajorSelection())
                .specialization(curriculum.getSpecialization())
                .allSum(curriculum.getAllSum())
                .sumCulture(curriculum.getSumCulture())
                .byUni(curriculum.getByUni())
                .majorSum(curriculum.getMajorSum()).build();
    }

}
