package com.knu.creditmanager.department;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentRepository departmentRepository;

    @BeforeEach
    void beforeEach() {

    }

    @AfterEach
    void afterEach() {
        departmentRepository.deleteAll();
    }

    @Test
    @DisplayName("학과 하나 조회")
    void getDepartment() throws Exception {
        // When
        mvc.perform(get("/api/departments/컴퓨터과학"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test.com")));
    }

    @Test
    @DisplayName("모든 학과 조회")
    void getDepartments() throws Exception {
        // When
        mvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("컴퓨터과학")));
    }

    @Test
    @DisplayName("학과 하나 생성 - 정상 입력")
    void createDepartment() throws Exception {
        // When
        mvc.perform(post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"컴퓨터정보통신\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Success")));

        // Then
        List<Department> departmentList = departmentRepository.findAll();
        assertEquals(2, departmentList.size());
        assertEquals("컴퓨터과학", departmentList.get(0).getName());
        assertEquals("컴퓨터정보통신", departmentList.get(1).getName());
    }

    @Test
    @DisplayName("학과 하나 생성 - 이미 있는 학과 입력")
    void createDepartmentWithValid() throws Exception {
        // When
        mvc.perform(post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"컴퓨터과학\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("error")));

        // Then
        List<Department> departmentList = departmentRepository.findAll();
        assertEquals(1, departmentList.size());
    }

    @Test
    @DisplayName("학과 하나 생성 - 정상 입력")
    void createDepartments() throws Exception {
        // When
        mvc.perform(post("/api/departments/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[" +
                        "{\"name\": \"컴퓨터정보통신\"}," +
                        "{\"name\": \"음악학과\"}" +
                        "]"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

        // Then
        List<Department> departmentList = departmentRepository.findAll();
        assertEquals(3, departmentList.size());
        assertEquals("컴퓨터과학", departmentList.get(0).getName());
        assertEquals("컴퓨터정보통신", departmentList.get(1).getName());
        assertEquals("음악학과", departmentList.get(2).getName());
    }

}