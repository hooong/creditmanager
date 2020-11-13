package com.knu.creditmanager.mycourse;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/mycourses")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyCourseController {

    private final MyCourseService myCourseService;

    @GetMapping
    public List<MyCourse> allMyCourses(
            Authentication authentication) {
        Claims claims = (Claims)authentication.getPrincipal();
        String studentId = claims.get("studentId", String.class);

        return myCourseService.getAllMyCourse(studentId);
    }

    //수강하나신청
//    @PostMapping
//    @Transactional
//    public ResponseEntity<?> createMyCourse(
//            @RequestBody MyCourseDto resource)
//            throws URISyntaxException{
//        MyCourse myCourse = myCourseService.create(resource);
//
//        URI location = new URI("/api/mycourses" + myCourse.getCourseCord());
//        return ResponseEntity.created(location).body("{\"message\" : \"Success Create\"}");
//    }

    //배열로 입력받은 수업 모두 생성
    @PostMapping
    @Transactional
    public ResponseEntity<?> createMyCourses(
            Authentication authentication,
            @RequestBody List<MyCourseDto> resources){
        Claims claims = (Claims)authentication.getPrincipal();
        String studentId = claims.get("studentId", String.class);

        myCourseService.createAll(resources, studentId);

        return ResponseEntity.ok().body("{\"message\" : \"Success Create (Without Overlap Name)\"}");
    }

}
