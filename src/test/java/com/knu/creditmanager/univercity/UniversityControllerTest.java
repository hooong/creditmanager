package com.knu.creditmanager.univercity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UniversityControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName(value = "학과추가")
    void create() throws Exception {
        mvc.perform(post("/api/university")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"경영대학\"," +
                "\"department\" : [{\"name\" : \"경영·회계학부\", \"major\" : [{\"name\": \"경영학전공\",\"link\": \"http://biz.kangwon.ac.kr/main.php\"},{\"name\": \"회계학전공\",\"link\": \"http://account.kangwon.ac.kr/\"}]\n" +
                "}]}"))
                .andExpect(status().isCreated());
    }


}