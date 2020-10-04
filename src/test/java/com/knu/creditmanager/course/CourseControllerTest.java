package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired private CourseService courseService;
    @Autowired private CourseRepository courseRepository;

    @BeforeEach
    void beforeEach(){
        Course course = new Course(411394L,"컴퓨터구조");
        courseRepository.save(course);
    }

    @AfterEach
    void afterEach(){courseRepository.deleteAll();}

    @org.junit.jupiter.api.Test
    @DisplayName("모든 수업 조회")
    void getCourse() throws Exception{
        mvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"courseName\":\"컴퓨터구조\"")));
    }

    @Test
    @DisplayName("모든 수업 조회")
    void getCourses() throws Exception{
        mvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("컴퓨터구조")));
    }

    @Test
    @DisplayName("수업 하나 생성 - 정상 입력")
    void createCourse() throws Exception{
        mvc.perform()
    }


}