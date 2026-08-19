package com.university.sums.repository;

import com.university.sums.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Page<Enrollment> findByStudentNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}