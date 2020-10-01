package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    public Course getCourse(Long courseCord){
        Course course = CourseRepository.findByCourseCord(courseCord);
    }

    public Course addCourse(Course course) {
        return CourseRepository.save(course);
    }

    @Transactional
    public Course updateCourses(Long courseCord, String courseName, String courseType, int coursePoint) {
        Course course = CourseRepository.findByCourseCord(courseCord).orElse(null);

        course.updateInformation(courseName,courseType,coursePoint);
        return course;
    }
}
