package com.knu.creditmanager.mycourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse,Long> {

//    Optional<MyCourse> findByCourseCord(Long courseCord);

//    List<MyCourse> findAllByStudentId(String studentId);
}
