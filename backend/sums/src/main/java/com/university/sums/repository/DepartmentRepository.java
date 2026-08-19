package com.university.sums.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.university.sums.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Page<Department> findByDepartmentNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}