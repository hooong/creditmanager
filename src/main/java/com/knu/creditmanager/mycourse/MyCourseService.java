package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.course.CourseRepository;
import com.knu.creditmanager.course.CourseService;
import com.knu.creditmanager.domain.CourseSession;
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

    public MyCourse getCourse(Long courseCord) {
        return myCourseRepository.findByCourseCord(courseCord).orElseThrow(() -> new MyCourseNotExistedException(courseCord));
    }

    public MyCourse create(MyCourse myCourse){
        MyCourse existed = myCourseRepository.findByCourseCord(myCourse.getCourseCord())
                .orElse(null);

        if(existed != null){
            throw  new MyCourseExistedException(myCourse.getCourseCord());
        }

        return myCourseRepository.save(myCourse);
    }

    public void createAll(List<MyCourseDto> myCourseDtos, String studentId){
        for(MyCourseDto myCourseDto: myCourseDtos){
            CourseSession courseSession = courseService.getCourse(myCourseDto.getCourseCord());


            MyCourse myCourse = new MyCourse(
                    courseSession,
                    studentId,
                    myCourseDto.getGrade(),
                    myCourseDto.getUniYear(),
                    myCourseDto.getSemester());

            myCourseRepository.save(myCourse);
        }
    }
}
