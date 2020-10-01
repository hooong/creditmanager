package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        List<Course> course = CourseRepository.findAll();
        return course;
    }
}
