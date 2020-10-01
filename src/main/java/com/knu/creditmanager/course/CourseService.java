package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        List<Course> course = courseRepository.findAll();
        return course;
    }

    public Course getCourse(Long courseCord){
        Course course = courseRepository.findByCourseCord(courseCord).orElse(null);
        return course;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourses(Long courseCord, String courseName, String courseType, int coursePoint) {
        Course course = courseRepository.findByCourseCord(courseCord).orElse(null);

        course.updateInformation(courseName,courseType,coursePoint);
        return course;
    }
}
