package com.university.sums.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.sums.entity.Department;
import com.university.sums.service.DepartmentService;

import jakarta.validation.Valid;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // =========================
    // Department List
    // =========================
    @GetMapping("/departments")
    public String listDepartments(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5;

        Page<Department> departmentPage;

        if (keyword != null && !keyword.trim().isEmpty()) {

            departmentPage = departmentService.searchDepartments(
                    keyword,
                    page,
                    pageSize
            );

        } else {

            departmentPage = departmentService.getDepartmentPage(
                    page,
                    pageSize
            );
        }

        model.addAttribute(
                "departments",
                departmentPage.getContent()
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "totalPages",
                departmentPage.getTotalPages()
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        model.addAttribute(
                "activePage",
                "departments"
        );

        return "departments";
    }

    // =========================
    // Open Add Department Form
    // =========================
    @GetMapping("/departments/new")
    public String showAddForm(Model model) {

        model.addAttribute(
                "department",
                new Department()
        );

        return "department-form";
    }

    // =========================
    // Save Department
    // =========================
    @PostMapping("/departments")
    public String saveDepartment(
            @Valid @ModelAttribute("department") Department department,
            BindingResult result) {

        if (result.hasErrors()) {

            return "department-form";
        }

        departmentService.saveDepartment(department);

        return "redirect:/departments";
    }

    // =========================
    // Open Edit Department Form
    // =========================
    @GetMapping("/departments/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Department department =
                departmentService.getDepartmentById(id);

        model.addAttribute(
                "department",
                department
        );

        return "department-form";
    }

    // =========================
    // Update Department
    // =========================
    @PostMapping("/departments/update/{id}")
    public String updateDepartment(
            @PathVariable Long id,
            @Valid @ModelAttribute("department") Department department,
            BindingResult result) {

        if (result.hasErrors()) {

            return "department-form";
        }

        department.setId(id);

        departmentService.updateDepartment(department);

        return "redirect:/departments";
    }

    // =========================
    // Delete Department
    // =========================
    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "redirect:/departments";
    }

}