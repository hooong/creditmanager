package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
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

    public Course getCourse(Long courseCord) {
        return courseRepository.findByCourseCord(courseCord).orElseThrow(() -> new CourseNotExistedException(courseCord));
    }

    public Course create(Course course) {
        Course existed = courseRepository.findByCourseCord(course.getCourseCord())
                .orElse(null);

        if (existed != null) {
            throw new CourseExistedException(course.getCourseCord());
        }

        return courseRepository.save(course);
    }

    public void createAll(List<Course> courseList){
        for(Course course: courseList){
            Course existed = courseRepository.findByCourseCord(course.getCourseCord())
                    .orElse(null);

            if(existed == null){
                courseRepository.save(course);
            }
        }
    }
}

