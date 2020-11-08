package com.knu.creditmanager.credit;

import com.knu.creditmanager.curriculum.Curriculum;
import com.knu.creditmanager.curriculum.CurriculumRepository;
import com.knu.creditmanager.domain.CourseSession;
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
                .specialization(curriculum.getSpecialization())
                .byUni(curriculum.getByUni())
                .sumCulture(curriculum.getSumCulture())
                .majorNecessary(curriculum.getMajorNecessary())
                .majorSelection(curriculum.getMajorSelection())
                .majorDeepening(curriculum.getMajorDeepening())
                .freeChoice(curriculum.getFreeChoice())
                .majorSum(curriculum.getMajorSum())
                .allSum(curriculum.getAllSum()).build();
    }

    public void calcCredit(CourseSession courseSession, String studentId) {
        Credit credit = creditRepository.findByStudentId(studentId);

        String type = courseSession.getCourseType();

        if (type.equals("균형교양") || type.equals("기초교양") || type.equals("특화교양") || type.equals("대학별교양")) {
            if (credit.getSumCulture() > 0) {

            }
        } else if (type.equals("전공필수") || type.equals("전공선택") || type.equals("전공심화")) {

        } else if (type.equals("자유선택")) {

        }

    }

}
