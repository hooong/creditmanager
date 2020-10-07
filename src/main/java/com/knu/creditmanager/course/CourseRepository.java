package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseSession, Long> {

    Optional<CourseSession> findByCourseCord(Long courseCord);

}
