package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.CourseRepository;
import com.knu.creditmanager.course.CourseService;
import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
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
public class MyCourseControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired private CourseService courseService;
    @Autowired private MyCourseRepository myCourseRepository;

    @BeforeEach
    void beforeEach(){
        MyCourse myCourse = new MyCourse(411394L,"컴퓨터구조","전공선택",3, Grade.AP,3, Semester.SPRING);
        myCourseRepository.save(myCourse);
    }

    @AfterEach
    void afterEach(){myCourseRepository.deleteAll();}

    @Test
    @DisplayName("수강 내역 조회")
    void getMyCourse() throws Exception{
        mvc.perform(get("/api/mycourses"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"courseName\":\"컴퓨터구조\"")));

    }

    @Test
    @DisplayName("수강 내역 추가 - 정상 입력")
    void createMyCourse() throws Exception{
        //When
        mvc.perform(post("/api/mycourses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseCord\": 411395}"))
                .andExpect(status().isCreated())
                .andExpect(content().string((containsString("Success"))));
        //Then
        List<MyCourse> myCourseList = myCourseRepository.findAll();
        assertEquals(2,myCourseList.size());
        assertEquals(411394L,myCourseList.get(0).getCourseCord());
        assertEquals(411395L,myCourseList.get(1).getCourseCord());
    }

    @Test
    @DisplayName("수강 내역 하나 생성 - 이미 있는 수강 내역 입력")
    void createMyCourseWithValid() throws  Exception{
        //When
        mvc.perform(post("/api/mycourses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseCord\": 411394}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error")));
        //Then
        List<MyCourse> myCourseList = myCourseRepository.findAll();
        assertEquals(1,myCourseList.size());
    }

    @Test
    @DisplayName("수강 내역 여러개 생성 - 정상 입력")
    void createMyCourses() throws Exception{
        //When
        mvc.perform(post("/api/mycourses/all3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[" +
                        "{\"courseCord\": 411395}," +
                        "{\"courseCord\": 411396}" +
                        "]"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
        //Then
        List<MyCourse> myCourseList = myCourseRepository.findAll();
        assertEquals(3, myCourseList.size());
        assertEquals(411394L,myCourseList.get(0).getCourseCord());
        assertEquals(411395L,myCourseList.get(1).getCourseCord());
        assertEquals(411396L,myCourseList.get(2).getCourseCord());
    }
}
