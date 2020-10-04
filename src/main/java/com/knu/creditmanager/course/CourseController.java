package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Courses")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseController {


    private final CourseService courseService;

    @GetMapping
    public List<Course> allCourses(){
        return courseService.getAllCourse();
    }

    @GetMapping("/{courseCord}")
    public Course Course(@PathVariable Long courseCord){
        return courseService.getCourse(courseCord);
    }

    //수업 하나 생성
    @PostMapping
    @Transactional
    public ResponseEntity<?> createCourse(@RequestBody Course resource) throws URISyntaxException{        Course course = courseService.create(resource);

        URI location = new URI("/api/Courses/" + course.getCourseCord());
        return ResponseEntity.created(location).body("{\"message\" : \"Success Create\"}");
    }

    //배열로 입력 받은 수업 모두 생성
    @PostMapping("/all2")
    @Transactional
    public ResponseEntity<?> createCourses(
            @RequestBody List<Course> courseList){
        courseService.createAll(courseList);

        return ResponseEntity.ok().body("{\"message\" : \"Success Create (Without Overlap Name)\"}");
    }

}
