package com.university.sums.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.university.sums.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    List<Lecturer> findByNameContainingIgnoreCase(String keyword);
    Page<Lecturer> findByNameContainingIgnoreCase(String keyword, Pageable pageable);


}