package com.knu.creditmanager.department;

import com.knu.creditmanager.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 모든 확과 조회
    @GetMapping
    public List<Department> allDepartments() {
        return departmentService.getAllDepartment();
    }

    // 배열로 입력 받은 학과 모두 입력
    @PostMapping
    public ResponseEntity<?> createDepartments(
            @RequestBody List<Department> departmentList) {
        departmentService.createAll(departmentList);

        return ResponseEntity.ok().body("{\"message\" : \"Success Create\"}");
    }

}
