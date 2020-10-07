package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyCourseRepository extends JpaRepository<MyCourse,Long > {

    Optional<MyCourse> findByCourseCord(Long courseCord);
}
