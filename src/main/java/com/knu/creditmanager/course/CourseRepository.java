package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import com.knu.creditmanager.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseCord(Long courseCord);

}
