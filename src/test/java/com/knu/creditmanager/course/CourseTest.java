package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.CourseSession;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CourseTest {

    @Test
    public void creation(){

        CourseSession course = new CourseSession(411394L, "컴퓨터구조", "전공필수", 3);

        assertThat(course.getCourseCord(),is(411394L));
        assertThat(course.getCourseName(),is("컴퓨터구조"));
        assertThat(course.getCoursePoint(),is(3));
        assertThat(course.getCourseType(),is("전공필수"));
    }


}
