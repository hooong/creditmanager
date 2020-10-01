package com.knu.creditmanager.department;

import com.knu.creditmanager.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/departments")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentController {

    private final DepartmentService departmentService;

    // 모든 확과 조회
    @GetMapping
    public List<Department> allDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{name}")
    public Department Department(@PathVariable String name) {
        return departmentService.getDepartment(name);
    }

    // 학과 하나 생성
    @PostMapping
    @Transactional
    public ResponseEntity<?> createDepartment(@RequestBody Department resource) throws URISyntaxException {
        Department department = departmentService.create(resource);

        URI location = new URI("/api/departments/" + department.getId());
        return ResponseEntity.created(location).body("{\"message\" : \"Success Create\"}");
    }

    // 배열로 입력 받은 학과 모두 생성
    @PostMapping
    @Transactional
    public ResponseEntity<?> createDepartments(
            @RequestBody List<Department> departmentList) {
        departmentService.createAll(departmentList);

        return ResponseEntity.ok().body("{\"message\" : \"Success Create (Without Overlap Name)\"}");
    }

}
