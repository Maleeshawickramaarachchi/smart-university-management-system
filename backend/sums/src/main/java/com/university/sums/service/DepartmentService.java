package com.university.sums.service;

import com.university.sums.entity.Department;
import com.university.sums.repository.DepartmentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Save Department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get Department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // Update Department
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Delete Department
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    // Get Departments with Pagination
    public Page<Department> getDepartmentPage(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        return departmentRepository.findAll(pageable);
    }

    // Search Departments with Pagination
    public Page<Department> searchDepartments(
            String keyword,
            int page,
            int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        return departmentRepository
                .findByDepartmentNameContainingIgnoreCase(
                        keyword,
                        pageable
                );
    }
    public long getDepartmentCount() {
    return departmentRepository.count();
}
}