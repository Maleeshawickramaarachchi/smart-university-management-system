package com.university.sums.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.university.sums.entity.Enrollment;
import com.university.sums.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // Save Enrollment
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Get Enrollment by ID
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    // Update Enrollment
    public Enrollment updateEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Delete Enrollment
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    // Get Enrollments with Pagination
    public Page<Enrollment> getEnrollmentPage(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        return enrollmentRepository.findAll(pageable);
    }

    // Search Enrollments with Pagination
    public Page<Enrollment> searchEnrollments(
            String keyword,
            int page,
            int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        return enrollmentRepository
                .findByStudentNameContainingIgnoreCase(
                        keyword,
                        pageable
                );
    }
    // Get total enrollment count
public long getEnrollmentCount() {
    return enrollmentRepository.count();
}
}