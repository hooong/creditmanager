package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.account.AccountRepository;
import com.knu.creditmanager.account.AccountService;
import com.knu.creditmanager.account.RegisterAccountDto;
import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.course.CourseRepository;
import com.knu.creditmanager.course.CourseService;
import com.knu.creditmanager.credit.Credit;
import com.knu.creditmanager.credit.CreditRepository;
import com.knu.creditmanager.curriculum.CurriculumRepository;
import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.department.DepartmentRepository;
import com.knu.creditmanager.department.DepartmentService;
import com.knu.creditmanager.grade.Grade;
import com.knu.creditmanager.grade.Semester;
import com.knu.creditmanager.major.Major;
import com.knu.creditmanager.major.MajorRepository;
import org.hamcrest.Matchers;
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
    @Autowired private DepartmentRepository departmentRepository;
    @Autowired private CreditRepository creditRepository;
    @Autowired private CurriculumRepository curriculumRepository;
    @Autowired private MajorRepository majorRepository;

    @BeforeEach
    void beforeEach() throws Exception {
        mvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"courseType\":\"기초교양\"," +
                        "\"courseFTF\":\"비대면\"," +
                        "\"courseCord\":1100004," +
                        "\"courseDivision\":2," +
                        "\"courseName\":\"글쓰기와말하기(인문사회)\"," +
                        "\"courseCredit\":\"3-3-0-0\"," +
                        "\"courseTypeDetail\":\"국어\"," +
                        "\"courseTarget\":\"춘천캠퍼스/사회과학대학//1                                                          \"," +
                        "\"coursePerson\":40," +
                        "\"courseUni\":\"교양교육원\"," +
                        "\"courseMid\":\"\"," +
                        "\"courseDep\":\"\"," +
                        "\"corseTeach\":\"김옥영\"," +
                        "\"courseTeachType\":\"강사\"," +
                        "\"courseTime\":\"월A2,목A2(60주년기념관 314)\"," +
                        "\"courseELearning\":\"N\"}"));

        Major major = Major.builder().name("컴퓨터공학과").link("").build();
        majorRepository.save(major);

        mvc.perform(post("/api/curriculums")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"curriculumUni\":\"IT대학\",\n" +
                        "        \"curriculumName\":\"컴퓨터공학과\",\n" +
                        "        \"foundation\":10,\n" +
                        "        \"balance\":12,\n" +
                        "        \"specialization\":1,\n" +
                        "        \"byUni\":18,\n" +
                        "        \"sumCulture\":41,\n" +
                        "        \"majorNecessary\":12,\n" +
                        "        \"majorSelection\":30,\n" +
                        "        \"majorDeepening\":12,\n" +
                        "        \"majorSum\":54,\n" +
                        "        \"freeChoice\":35,\n" +
                        "        \"allSum\":130,\n" +
                        "        \"curriculumYear\":\"2020\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.containsString("Success")));

        RegisterAccountDto account = new RegisterAccountDto();
        account.setName("홍석준");
        account.setPassword("1234");
        account.setMajor("컴퓨터공학과");
        account.setUniYear(4);
        account.setSemester(Semester.FALL);
        account.setStudentId("202013501");
        accountService.registerAccount(account);

    }

    @AfterEach
    void afterEach() {
        courseRepository.deleteAll();
        myCourseRepository.deleteAll();
        accountRepository.deleteAll();
        departmentRepository.deleteAll();
        creditRepository.deleteAll();
        curriculumRepository.deleteAll();
    }

    @Test
    @DisplayName("수강 내역 조회")
    void getMyCourse() throws Exception {
        // Given
        MyCourse myCourse = MyCourse.builder().studentId("201513501").courseId(1L).uniYear(4).semester(Semester.FALL).grade(Grade.AP).build();
        myCourseRepository.save(myCourse);

        // Then
        mvc.perform(get("/api/mycourses")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOiIyMDIwMTM1MDEiLCJuYW1lIjoi7ZmN7ISd7KSAIn0.-Xxbj2jEITDEaipPlSjFZGwyh8ev71P_SVbtWgdkKfA"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("수강 내역 추가 - 정상 입력")
    void createMyCourse() throws Exception {
        // Given
        Credit credit = creditRepository.findByStudentId("202013501");
        System.out.println(credit.toString());

        //When
        mvc.perform(post("/api/mycourses")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOiIyMDIwMTM1MDEiLCJuYW1lIjoi7ZmN7ISd7KSAIn0.-Xxbj2jEITDEaipPlSjFZGwyh8ev71P_SVbtWgdkKfA")
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

        credit = creditRepository.findByStudentId("202013501");
        System.out.println(credit.toString());
    }
}
