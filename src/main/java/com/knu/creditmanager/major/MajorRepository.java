package com.knu.creditmanager.major;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByName(String name);

    List<Major> findAllByDepartmentId(Long id);
}
