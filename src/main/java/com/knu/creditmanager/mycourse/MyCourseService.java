package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.Course;
import com.knu.creditmanager.course.CourseService;
import com.knu.creditmanager.exception.MyCourseExistedException;
import com.knu.creditmanager.exception.MyCourseNotExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyCourseService {

    private final MyCourseRepository myCourseRepository;
    private final CourseService courseService;

    public List<MyCourse> getAllMyCourse(String studentId){
        return myCourseRepository.findAllByStudentId(studentId);
    }

//    public MyCourse create(MyCourseDto myCourseDto){
//        MyCourse existed = myCourseRepository.findByCourseId(myCourseDto.getCourseId())
//                .orElse(null);
//
//        if(existed != null){
//            throw  new MyCourseExistedException(myCourseDto.getCourseId());
//        }
//
//        MyCourse myCourse =
//
//        return myCourseRepository.save();
//    }

    public void createAll(List<MyCourseDto> myCourseDtos, String studentId){
        for(MyCourseDto myCourseDto: myCourseDtos){
            Course course = courseService.getCourse(myCourseDto.getCourseId());

            // 학점 추가 메소드

            MyCourse myCourse = MyCourse.builder()
                    .courseId(myCourseDto.getCourseId())
                    .studentId(studentId)
                    .grade(myCourseDto.getGrade())
                    .uniYear(myCourseDto.getUniYear())
                    .semester(myCourseDto.getSemester())
                    .credit(getCredit(course))
                    .build();

            myCourseRepository.save(myCourse);
        }
    }

    private Integer getCredit(Course course) {
        return Integer.parseInt(course.getCourseCredit().substring(0,1));
    }
}
