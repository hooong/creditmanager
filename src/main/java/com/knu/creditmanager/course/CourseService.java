package com.knu.creditmanager.course;

import com.knu.creditmanager.exception.CourseExistedException;
import com.knu.creditmanager.exception.CourseNotExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotExistedException(id));
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public void createAll(List<Course> courseList){
        for(Course course: courseList){
            courseRepository.save(course);
        }
    }
}

