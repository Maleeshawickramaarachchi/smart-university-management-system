package com.university.sums.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.university.sums.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByCourseNameContainingIgnoreCase(String keyword, Pageable pageable);

}