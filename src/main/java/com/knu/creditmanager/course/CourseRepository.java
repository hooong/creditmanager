package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,Long> {

    List<Course> findAll();

    Optional<Course> findByCourseCord(Long courseCord);

    Course save(Course course);
}
