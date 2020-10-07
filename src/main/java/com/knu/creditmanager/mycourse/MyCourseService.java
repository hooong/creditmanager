package com.knu.creditmanager.mycourse;

import com.knu.creditmanager.exception.MyCourseExistedException;
import com.knu.creditmanager.exception.MyCourseNotExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyCourseService {

    private  final MyCourseRepository myCourseRepository;

    public List<MyCourse> getAllMyCourse(){
        return myCourseRepository.findAll();
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

    public void createAll(List<MyCourse> myCourseList){
        for(MyCourse myCourse: myCourseList){
            MyCourse existed = myCourseRepository.findByCourseCord(myCourse.getCourseCord())
                    .orElse(null);

            if(existed == null){
                myCourseRepository.save(myCourse);
            }
        }
    }
}
