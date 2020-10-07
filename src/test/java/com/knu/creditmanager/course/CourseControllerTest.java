package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
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

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
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
        //When
        mvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseCord\": 411395}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Success")));
        //Then
        List<Course> courseList = courseRepository.findAll();
        assertEquals(2,courseList.size());
        assertEquals(411394L,courseList.get(0).getCourseCord());
        assertEquals(411395L,courseList.get(1).getCourseCord());
    }

    @Test
    @DisplayName("수업 하나 생성 - 이미 있는 수업 입력")
    void createCourseWithValid() throws Exception{
        //When
        mvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseCord\": 411394}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("error")));

        //Then
        List<Course> courseList =courseRepository.findAll();
        assertEquals(1,courseList.size());
    }

    @Test
    @DisplayName("수업 여러개 생성 - 정상 입력")
    void createCourses() throws Exception{
        //When
        mvc.perform(post("/api/courses/all2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[" +
                        "{\"courseCord\": 411395}," +
                        "{\"courseCord\": 411396}" +
                        "]"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

        //Then
        List<Course> courseList = courseRepository.findAll();
        assertEquals(3, courseList.size());
        assertEquals(411394L,courseList.get(0).getCourseCord());
        assertEquals(411395L,courseList.get(1).getCourseCord());
        assertEquals(411396L,courseList.get(2).getCourseCord());
    }


}