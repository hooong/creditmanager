package com.knu.creditmanager.account;

import com.knu.creditmanager.department.DepartmentRepository;
import com.knu.creditmanager.department.DepartmentService;
import com.knu.creditmanager.department.Department;
import com.knu.creditmanager.grade.Semester;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired DepartmentService departmentService;
    @Autowired DepartmentRepository departmentRepository;

    @BeforeEach
    void beforeEach() {
        Department department = Department.builder().name("컴퓨터과학").link("").build();
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
        departmentRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("모든 회원 조회")
    void getAllAccountsTest() throws Exception {
        //When
        mvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("201513501")));
    }

    @Test
    @DisplayName("특정 id 회원 조회")
    void getAccountTest() throws Exception {
        mvc.perform(get("/api/accounts/201513501"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("201513501")));
    }

    @Test
    @DisplayName("회원 가입 - 정상적인 입력")
    void signupTest() throws Exception {
        //When
        mvc.perform(post("/api/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"studentId\": \"201511111\", " +
                    "\"password\": \"1234\", " +
                    "\"name\": \"박지원\", " +
                    "\"major\": \"컴퓨터과학\", " +
                    "\"uniYear\": 4, " +
                    "\"semester\":\"FALL\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Success")));

        // Then
        List<Account> accountList = accountService.getAllAccounts();
        assertEquals(accountList.size(), 2);
    }

    @Test
    @DisplayName("회원 가입 - 이미 가입된 학번 입력")
    void signupWithExsitedStudentIdTest() throws Exception {
        //When
        mvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"studentId\": \"201513501\", " +
                        "\"password\": \"1234\", " +
                        "\"name\": \"홍석준\", " +
                        "\"major\": \"컴퓨터과학\", " +
                        "\"uniYear\": 4, " +
                        "\"semester\":\"FALL\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("error")));

        // Then
        List<Account> accountList = accountService.getAllAccounts();
        assertEquals(1, accountList.size());
    }

    @Test
    @DisplayName("회원 탈퇴 - 존재하는 학번")
    void deleteTest() throws Exception {
        // When
        mvc.perform(delete("/api/accounts/201513501"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

        // Then
        Account account = accountService.getAccount("201513501");
        assertEquals(true, account.isDeleted());
    }

    @Test
    @DisplayName("회원 탈퇴 - 존재하지 않는 학번")
    void deleteWithNotExistedTest() throws Exception {
        mvc.perform(delete("/api/accounts/201511111"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("error")));
    }

}