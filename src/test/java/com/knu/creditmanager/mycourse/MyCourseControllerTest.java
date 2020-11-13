package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.account.AccountRepository;
import com.knu.creditmanager.account.AccountService;
import com.knu.creditmanager.account.RegisterAccountDto;
import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.course.CourseRepository;
import com.knu.creditmanager.course.CourseService;
import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.department.DepartmentRepository;
import com.knu.creditmanager.department.DepartmentService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyCourseControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private CourseService courseService;
    @Autowired
    private MyCourseRepository myCourseRepository;
    @Autowired CourseRepository courseRepository;
    @Autowired private AccountService accountService;
    @Autowired private AccountRepository accountRepository;
    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentRepository departmentRepository;

    @BeforeEach
    void beforeEach() throws Exception {
        mvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseType\":\"전공필수\"," +
                        "\"courseFTF\":\"비대면\"," +
                        "\"courseCord\":4471016," +
                        "\"courseDivision\":3," +
                        "\"courseName\":\"알고리즘\"," +
                        "\"courseCredit\":\"3-3-0-0\"," +
                        "\"courseTypeDetail\":\"\"," +
                        "\"courseTarget\":\"컴퓨터공학과()2\"," +
                        "\"coursePerson\":40," +
                        "\"courseUni\":\"IT대학\"," +
                        "\"courseMid\":\"컴퓨터공학과\"," +
                        "\"courseDep\":\"\"," +
                        "\"corseTeach\":\"문양세\"," +
                        "\"courseTeachType\":\"전임교원\"," +
                        "\"courseTime\":\"월A1,목A1(한빛관 412)\"," +
                        "\"courseELearning\":\"N\"}"));

        Department department = new Department("컴퓨터과학", "");
        departmentService.create(department);

        RegisterAccountDto account = new RegisterAccountDto();
        account.setName("홍석준");
        account.setPassword("1234");
        account.setMajor("컴퓨터과학");
        account.setUniYear(4);
        account.setSemester(Semester.FALL);
        account.setStudentId("201513501");
        accountService.registerAccount(account);
    }

    @AfterEach
    void afterEach() {
        courseRepository.deleteAll();
        myCourseRepository.deleteAll();
        accountRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    @DisplayName("수강 내역 조회")
    void getMyCourse() throws Exception {
        // Given
        MyCourse myCourse = MyCourse.builder().studentId("201513501").courseId(1L).uniYear(4).semester(Semester.FALL).grade(Grade.AP).build();
        myCourseRepository.save(myCourse);

        // Then
        mvc.perform(get("/api/mycourses")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOiIyMDE1MTM1MDEiLCJuYW1lIjoi7ZmN7ISd7KSAIn0.43yzmbjYFAxNq7StIpH7QpuZp8M8lrj8A3X-CLJm78M"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("수강 내역 추가 - 정상 입력")
    void createMyCourse() throws Exception {
        // Given

        //When
        mvc.perform(post("/api/mycourses")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOiIyMDE1MTM1MDEiLCJuYW1lIjoi7ZmN7ISd7KSAIn0.43yzmbjYFAxNq7StIpH7QpuZp8M8lrj8A3X-CLJm78M")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"courseId\": 1," +
                        "\"grade\": \"AP\"," +
                        "\"uniYear\": 3," +
                        "\"semester\": \"FALL\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string((containsString("Success"))));
        //Then
        List<MyCourse> myCourseList = myCourseRepository.findAll();
        assertEquals(1, myCourseList.size());
        assertEquals(1L, myCourseList.get(0).getCourseId());
        System.out.println(myCourseList.get(0).toString());
    }
}
