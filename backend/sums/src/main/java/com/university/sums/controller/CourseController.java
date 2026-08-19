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

import com.university.sums.entity.Course;
import com.university.sums.service.CourseService;

import jakarta.validation.Valid;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/courses")
    public String listCourses(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 5;

        Page<Course> coursePage;

        if (keyword != null && !keyword.isEmpty()) {

            coursePage =
                    courseService.searchCourses(keyword, page, pageSize);

        } else {

            coursePage =
                    courseService.getCoursePage(page, pageSize);
        }

        model.addAttribute("courses",
                coursePage.getContent());

        model.addAttribute("currentPage",
                page);

        model.addAttribute("totalPages",
                coursePage.getTotalPages());

        model.addAttribute("keyword",
                keyword);

        model.addAttribute("activePage",
                "courses");

        return "courses";
    }


    @GetMapping("/courses/new")
    public String showAddForm(Model model) {

        model.addAttribute("course", new Course());

        return "course-form";
    }


    @PostMapping("/courses")
    public String saveCourse(
            @Valid @ModelAttribute("course") Course course,
            BindingResult result) {

        if (result.hasErrors()) {

            return "course-form";
        }

        courseService.saveCourse(course);

        return "redirect:/courses";
    }


    @GetMapping("/courses/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Course course =
                courseService.getCourseById(id);

        model.addAttribute("course", course);

        return "course-form";
    }


    @PostMapping("/courses/update")
    public String updateCourse(
            @Valid @ModelAttribute("course") Course course,
            BindingResult result) {

        if (result.hasErrors()) {

            return "course-form";
        }

        courseService.updateCourse(course);

        return "redirect:/courses";
    }


    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/courses";
    }

}