package com.knu.creditmanager.department;

import com.knu.creditmanager.exception.DepartmentExistedException;
import com.knu.creditmanager.exception.DepartmentNotExistedException;
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

    public Department getDepartment(String name) {
        return departmentRepository.findAllByName(name).orElseThrow(() -> new DepartmentNotExistedException(name));
    }

    public Department create(Department department) {
        Department existed = departmentRepository.findAllByName(department.getName())
                .orElse(null);

        if (existed != null) {
            throw new DepartmentExistedException(department.getName());
        }

        return departmentRepository.save(department);
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
