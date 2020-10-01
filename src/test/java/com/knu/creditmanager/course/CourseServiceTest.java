package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


class CourseServiceTest {

    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockCourseRepository();
        courseService = new CourseService(courseRepository);
    }

    private void mockCourseRepository(){
        List<Course> courses =new ArrayList<>();
        Course course = Course.builder()
                .courseName("컴퓨터구조")
                .courseCord(411394L)
                .coursePoint(3)
                .courseType("전공필수")
                .build();
        courses.add(course);
        given(CourseRepository.findAll()).willReturn(courses);
        given(courseRepository.findById(411394L)).willReturn(Optional.of(course));
    }

    @Test
    public void getCourses(){
        List<Course> courses = courseService.getCourses();

        Course course = courses.get(0);

    }
}