package com.knu.creditmanager.credit;

import com.knu.creditmanager.curriculum.Curriculum;
import com.knu.creditmanager.curriculum.CurriculumRepository;
import com.knu.creditmanager.curriculum.CurriculumService;
import com.knu.creditmanager.domain.CourseSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CurriculumService curriculumService;

    public void saveCredit(String studentId, String year) {
        Credit credit = createCredit(studentId, year);

        creditRepository.save(credit);
    }

    private Credit createCredit(String studentId, String year) {
        Curriculum curriculum = curriculumService.getCurriculum(year);

        if (curriculum == null) {
            // TODO : 커리큘럼을 찾을 수 없는 에러
        }

        return new Credit(studentId, year,
                curriculum.getFoundation(),
                curriculum.getBalance(),
                curriculum.getSpecialization(),
                curriculum.getByUni(),
                curriculum.getSumCulture(),
                curriculum.getMajorNecessary(),
                curriculum.getMajorSelection(),
                curriculum.getMajorDeepening(),
                curriculum.getMajorSum(),
                curriculum.getFreeChoice(),
                curriculum.getAllSum());
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
