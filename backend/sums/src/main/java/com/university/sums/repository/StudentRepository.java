package com.university.sums.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.university.sums.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
Page<Student> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}