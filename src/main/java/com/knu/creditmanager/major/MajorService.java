package com.knu.creditmanager.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    public void createMajor(Major resource, Long id) {
        Major major = Major.builder()
                .departmentId(id)
                .name(resource.getName())
                .link(resource.getLink()).build();

        majorRepository.save(major);
    }

    public Major getMajor(String name) {
        return majorRepository.findByName(name);
    }

    public List<Major> getAllMajor() {
        return majorRepository.findAll();
    }

    public List<Major> getMajorByDepartment(Long id) {
        return majorRepository.findAllByDepartmentId(id);
    }
}
