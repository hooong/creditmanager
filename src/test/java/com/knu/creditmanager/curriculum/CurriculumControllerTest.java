package com.knu.creditmanager.curriculum;

import com.knu.creditmanager.department.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurriculumControllerTest {
    @Autowired MockMvc mvc;

    @Test
    @DisplayName("커리큘럼 생성 - 정상 입력")
    void addCurriculum() throws Exception {
        // When
        mvc.perform(post("/api/curriculums")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"curriculumUni\":\"IT대학\",\n" +
                        "            \"curriculumName\":\"컴퓨터공학과\",\n" +
                        "            \"foundation\":10,\n" +
                        "            \"balance\":12,\n" +
                        "            \"Specialization\":1,\n" +
                        "            \"byUni\":18,\n" +
                        "            \"sumCulture\":41,\n" +
                        "            \"majorNecessary\":12,\n" +
                        "            \"majorSelection\":30,\n" +
                        "            \"majorDeepening\":12,\n" +
                        "            \"majorSum\":54,\n" +
                        "            \"freeChoice\":35,\n" +
                        "            \"allSum\":130,\n" +
                        "            \"curriculumYear\":2020\n" +
                        "    }"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Success")));

        // Then
//        List<Department> departmentList = departmentRepository.findAll();
//        assertEquals(2, departmentList.size());
//        assertEquals("컴퓨터과학", departmentList.get(0).getName());
//        assertEquals("컴퓨터정보통신", departmentList.get(1).getName());
    }
}