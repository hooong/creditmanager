package com.knu.creditmanager.department;

import com.knu.creditmanager.exception.DepartmentExistedException;
import com.knu.creditmanager.exception.DepartmentNotExistedException;
import com.knu.creditmanager.major.Major;
import com.knu.creditmanager.major.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final MajorService majorService;

    public List<Department> getAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();

        for (Department department: departmentList) {
            department.setMajor(majorService.getMajorByDepartment(department.getId()));
        }

        return departmentList;
    }

    public Department getDepartment(String name) {
        return departmentRepository.findByName(name).orElseThrow(() -> new DepartmentNotExistedException(name));
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department create(Department department) {
        Department existed = departmentRepository.findByName(department.getName())
                .orElse(null);

        if (existed != null) {
            throw new DepartmentExistedException(department.getName());
        }

        return departmentRepository.save(department);
    }

    public Department create(Department resource, Long univId) {
        Department department =
                Department.builder().name(resource.getName()).universityId(univId).build();

        department = departmentRepository.save(department);

        for (Major major: resource.getMajor()) {
            majorService.createMajor(major, department.getId());
        }

        return department;
    }

    public void createAll(List<Department> departmentList) {
        for (Department department: departmentList) {
            Department existed = departmentRepository.findByName(department.getName())
                    .orElse(null);

            if (existed == null) {
                departmentRepository.save(department);
            }
        }
    }

    public List<Department> getAllDepartmentByUniv(Long id) {
        return departmentRepository.findByUniversityId(id);
    }
}
