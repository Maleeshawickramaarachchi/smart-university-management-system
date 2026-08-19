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

import com.university.sums.entity.Enrollment;
import com.university.sums.service.EnrollmentService;

import jakarta.validation.Valid;

@Controller
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // =========================
    // Enrollment List
    // =========================
    @GetMapping("/enrollments")
    public String listEnrollments(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5;

        Page<Enrollment> enrollmentPage;

        if (keyword != null && !keyword.trim().isEmpty()) {

            enrollmentPage = enrollmentService.searchEnrollments(
                    keyword,
                    page,
                    pageSize
            );

        } else {

            enrollmentPage = enrollmentService.getEnrollmentPage(
                    page,
                    pageSize
            );
        }

        model.addAttribute(
                "enrollments",
                enrollmentPage.getContent()
        );

        model.addAttribute(
                "currentPage",
                page
        );

        model.addAttribute(
                "totalPages",
                enrollmentPage.getTotalPages()
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        model.addAttribute(
                "activePage",
                "enrollments"
        );

        return "enrollments";
    }

    // =========================
    // Open Add Enrollment Form
    // =========================
    @GetMapping("/enrollments/new")
    public String showAddForm(Model model) {

        model.addAttribute(
                "enrollment",
                new Enrollment()
        );

        return "enrollment-form";
    }

    // =========================
    // Save Enrollment
    // =========================
    @PostMapping("/enrollments")
    public String saveEnrollment(
            @Valid @ModelAttribute("enrollment") Enrollment enrollment,
            BindingResult result) {

        if (result.hasErrors()) {
            return "enrollment-form";
        }

        enrollmentService.saveEnrollment(enrollment);

        return "redirect:/enrollments";
    }

    // =========================
    // Open Edit Enrollment Form
    // =========================
    @GetMapping("/enrollments/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Enrollment enrollment =
                enrollmentService.getEnrollmentById(id);

        model.addAttribute(
                "enrollment",
                enrollment
        );

        return "enrollment-form";
    }

    // =========================
    // Update Enrollment
    // =========================
    @PostMapping("/enrollments/update/{id}")
    public String updateEnrollment(
            @PathVariable Long id,
            @Valid @ModelAttribute("enrollment") Enrollment enrollment,
            BindingResult result) {

        enrollment.setId(id);

        if (result.hasErrors()) {
            return "enrollment-form";
        }

        enrollmentService.updateEnrollment(enrollment);

        return "redirect:/enrollments";
    }

    // =========================
    // Delete Enrollment
    // =========================
    @GetMapping("/enrollments/delete/{id}")
    public String deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return "redirect:/enrollments";
    }
}