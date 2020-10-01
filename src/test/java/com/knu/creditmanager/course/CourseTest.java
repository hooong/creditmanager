package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CourseTest {

    @Test
    public void creation(){

        Course course = Course.builder()
                .courseName("컴퓨터구조")
                .courseCord(411394L)
                .coursePoint(3)
                .courseType("전공필수")
                .build();

        assertThat(course.getCourseCord(),is(411394L));
        assertThat(course.getCourseName(),is("컴퓨터구조"));
        assertThat(course.getCoursePoint(),is(3));
        assertThat(course.getCourseType(),is("전공필수"));
    }


}
