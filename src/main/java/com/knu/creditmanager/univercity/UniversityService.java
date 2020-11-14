package com.knu.creditmanager.univercity;

import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final DepartmentService departmentService;

    public University registUniversity(University resource) {
        University university = University.builder()
                .name(resource.getName()).build();

        for (Department department: resource.getDepartment()) {
            departmentService.create(department, university.getId());
        }

        return universityRepository.save(university);
    }

    public University getUniv(Long univId) {
        return universityRepository.findById(univId).orElse(null);
    }
}
