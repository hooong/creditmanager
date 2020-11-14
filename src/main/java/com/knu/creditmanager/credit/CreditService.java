package com.knu.creditmanager.credit;

import com.knu.creditmanager.account.Account;
import com.knu.creditmanager.account.AccountService;
import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.curriculum.Curriculum;
import com.knu.creditmanager.curriculum.CurriculumService;
import com.knu.creditmanager.mycourse.MyCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CurriculumService curriculumService;

    public Long saveCredit(Credit credit) {
        return creditRepository.save(credit).getId();
    }

    public Long firstCreateCredit(Account account) {
        Credit credit = createCredit(account);

        return creditRepository.save(credit).getId();
    }

    private Credit createCredit(Account account) {
        Curriculum curriculum =
                curriculumService.getCurriculumByMajorAndYear(account.getMajor().getName(), account.getAdmissionYear());

        if (curriculum == null) {
            // TODO : 커리큘럼을 찾을 수 없는 에러
        }

        Credit credit = new Credit(account.getStudentId(),
                curriculum.getId(),
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

        return credit;
    }

    public Credit findCredit(String studentId) {
        return creditRepository.findByStudentId(studentId);
    }

    public void calcCredit(MyCourse course) {
        Credit credit = findCredit(course.getStudentId());

        credit.cutCredit(course);
        creditRepository.save(credit);
    }
}
