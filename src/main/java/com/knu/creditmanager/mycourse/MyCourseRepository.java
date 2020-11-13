package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse,Long> {
    List<MyCourse> findAllByStudentId(String studentId);

    Optional<MyCourse> findByCourseId(Long courseId);
}
