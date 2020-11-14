package com.knu.creditmanager.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseCord(Long courseCord);

    @Query(value = "SELECT x FROM Course x WHERE x.courseType = :type and x.courseTarget LIKE %:target%")
    List<Course> findByCourseTypeAndCourseTarget(@Param("type")String courseType,@Param("target")String courseTarget);
}
