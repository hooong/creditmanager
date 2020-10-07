package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.CourseSession;
import com.knu.creditmanager.exception.CourseExistedException;
import com.knu.creditmanager.exception.CourseNotExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseSession> getAllCourse() {
        return courseRepository.findAll();
    }

    public CourseSession getCourse(Long courseCord) {
        return courseRepository.findByCourseCord(courseCord).orElseThrow(() -> new CourseNotExistedException(courseCord));
    }

    public CourseSession create(CourseSession course) {
        CourseSession existed = courseRepository.findByCourseCord(course.getCourseCord())
                .orElse(null);

        if (existed != null) {
            throw new CourseExistedException(course.getCourseCord());
        }

        return courseRepository.save(course);
    }

    public void createAll(List<CourseSession> courseList){
        for(CourseSession course: courseList){
            CourseSession existed = courseRepository.findByCourseCord(course.getCourseCord())
                    .orElse(null);

            if(existed == null){
                courseRepository.save(course);
            }
        }
    }
}

