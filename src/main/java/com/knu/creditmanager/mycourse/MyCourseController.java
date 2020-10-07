package com.knu.creditmanager.mycourse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mycourses")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyCourseController {

    private final MyCourseService myCourseService;

    @GetMapping
    public List<MyCourse> allMyCourses() {
        return myCourseService.getAllMyCourse();
    }

    @GetMapping("/{courseCord}")
    public MyCourse MyCourse(@PathVariable Long courseCord) {return myCourseService.getCourse(courseCord);}

    //수강하나신청
    @PostMapping
    @Transactional
    public ResponseEntity<?> createMyCourse(@RequestBody MyCourse resource) throws URISyntaxException{
        MyCourse myCourse = myCourseService.create(resource);

        URI location = new URI("/api/mycourses" + myCourse.getCourseCord());
        return ResponseEntity.created(location).body("{\"message\" : \"Success Create\"}");
    }

    //배열로 입력받은 수업 모두 생성
    @PostMapping("/all3")
    @Transactional
    public ResponseEntity<?> createMyCourses(
            @RequestBody List<MyCourse> myCourseList){
        myCourseService.createAll(myCourseList);

        return ResponseEntity.ok().body("{\"message\" : \"Success Create (Without Overlap Name)\"}");
    }

}
