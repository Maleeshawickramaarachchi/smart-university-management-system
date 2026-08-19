package com.university.sums.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.university.sums.entity.Course;
import com.university.sums.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Save Course
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get All Courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get Course by ID
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Update Course
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    // Delete Course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Pagination
    public Page<Course> getCoursePage(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }
    public long getCourseCount() {
    return courseRepository.count();
}

    // Search + Pagination
    public Page<Course> searchCourses(String keyword, int page, int size) {
        return courseRepository.findByCourseNameContainingIgnoreCase(
                keyword,
                PageRequest.of(page, size));
    }
}