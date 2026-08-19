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

import com.university.sums.entity.Lecturer;
import com.university.sums.service.LecturerService;

import jakarta.validation.Valid;

@Controller
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }


    // Display all lecturers
    @GetMapping("/lecturers")
    public String listLecturers(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5;

        Page<Lecturer> lecturerPage;

        if (keyword != null && !keyword.isEmpty()) {

            lecturerPage =
                    lecturerService.searchLecturers(keyword, page, pageSize);

        } else {

            lecturerPage =
                    lecturerService.getLecturerPage(page, pageSize);
        }

        model.addAttribute("lecturers",
                lecturerPage.getContent());

        model.addAttribute("currentPage",
                page);

        model.addAttribute("totalPages",
                lecturerPage.getTotalPages());

        model.addAttribute("keyword",
                keyword);

        model.addAttribute("activePage",
                "lecturers");

        return "lecturers";
    }


    // Open Add Lecturer Form
    @GetMapping("/lecturers/new")
    public String showAddForm(Model model) {

        model.addAttribute("lecturer", new Lecturer());

        return "lecturer-form";
    }


    // Save Lecturer
    @PostMapping("/lecturers")
    public String saveLecturer(
            @Valid @ModelAttribute("lecturer") Lecturer lecturer,
            BindingResult result) {

        if (result.hasErrors()) {

            return "lecturer-form";
        }

        lecturerService.saveLecturer(lecturer);

        return "redirect:/lecturers";
    }


    // Open Edit Lecturer Form
    @GetMapping("/lecturers/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Lecturer lecturer =
                lecturerService.getLecturerById(id);

        model.addAttribute("lecturer", lecturer);

        return "lecturer-form";
    }


    // Update Lecturer
    @PostMapping("/lecturers/update")
    public String updateLecturer(
            @Valid @ModelAttribute("lecturer") Lecturer lecturer,
            BindingResult result) {

        if (result.hasErrors()) {

            return "lecturer-form";
        }

        lecturerService.updateLecturer(lecturer);

        return "redirect:/lecturers";
    }


    // Delete Lecturer
    @GetMapping("/lecturers/delete/{id}")
    public String deleteLecturer(
            @PathVariable Long id) {

        lecturerService.deleteLecturer(id);

        return "redirect:/lecturers";
    }

}