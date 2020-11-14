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

        university = universityRepository.save(university);

        for (Department department: resource.getDepartment()) {
            departmentService.create(department, university.getId());
        }

        return university;
    }

    public University getUniv(Long univId) {
        return universityRepository.findById(univId).orElse(null);
    }

    public List<University> getAllUniversity() {
        List<University> universityList = universityRepository.findAll();

        for (University univ: universityList) {
            univ.setDepartment(departmentService.getAllDepartmentByUniv(univ.getId()));
        }

        return universityList;
    }
}
