package com.knu.creditmanager.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    public void createCredit(String studentId, String year) {
        Credit credit = Credit.builder()
                .studentId(studentId)
                .curriculumYear(year).build();

        creditRepository.save(credit);
    }
}
