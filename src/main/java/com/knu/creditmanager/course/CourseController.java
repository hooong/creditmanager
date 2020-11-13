package com.knu.creditmanager.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/courses")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> allCourses(){
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public Course Course(@PathVariable Long id){
        return courseService.getCourse(id);
    }

    //수업 하나 생성
    @PostMapping
    @Transactional
    public ResponseEntity<?> createCourse(@RequestBody Course resource) throws URISyntaxException{
        Course course = courseService.create(resource);

        URI location = new URI("/api/courses/" + course.getId());
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
