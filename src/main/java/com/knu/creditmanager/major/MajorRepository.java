package com.knu.creditmanager.major;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByName(String name);
}
