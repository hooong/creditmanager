package com.knu.creditmanager.department;

import com.knu.creditmanager.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void createAll(List<Department> departmentList) {
        for (Department department: departmentList) {
            Department existed = departmentRepository.findAllByName(department.getName())
                    .orElse(null);

            if (existed == null) {
                departmentRepository.save(department);
            }
        }
    }
}
