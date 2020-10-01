package com.knu.creditmanager.course;

import com.knu.creditmanager.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/Courses")
    public List<Course> list(){
        List<Course> courses = courseService.getCourses();
        return courses;
    }

    @GetMapping("/Courses/{id}")
    public Course detail(@PathVariable("id") Long courseCord){
        Course course = courseService.getCourse(courseCord);

        return course;
    }

    @PostMapping("/Courses")
    public ResponseEntity<?> create(@Valid @RequestBody Course resource) throws URISyntaxException{

        Course course = courseService.addCourse(
                Course.builder()
                .courseCord(resource.getCourseCord())
                .courseName(resource.getCourseName())
                .courseType(resource.getCourseType())
                .coursePoint(resource.getCoursePoint())
                .build());
        URI location = new URI("/Courses/" + course.getCourseCord());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/Courses/{id}")
    public String update(@PathVariable("id") Long courseCord,
                         @Valid @RequestBody Course resource){
        String courseName = resource.getCourseName();
        String courseType = resource.getCourseType();
        int coursePoint = resource.getCoursePoint();

        courseService.updateCourses(courseCord,courseName,courseType,coursePoint);

        return "{}";
    }
}
