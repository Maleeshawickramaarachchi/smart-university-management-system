package com.university.sums.controller;

import com.university.sums.service.CourseService;
import com.university.sums.service.DepartmentService;
import com.university.sums.service.LecturerService;
import com.university.sums.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final LecturerService lecturerService;
    private final DepartmentService departmentService;

    public HomeController(
            StudentService studentService,
            CourseService courseService,
            LecturerService lecturerService,
            DepartmentService departmentService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.lecturerService = lecturerService;
        this.departmentService = departmentService;
    }

   @GetMapping("/")
public String dashboard(Model model) {

    model.addAttribute("studentCount", studentService.getStudentCount());
    model.addAttribute("courseCount", courseService.getCourseCount());
    model.addAttribute("lecturerCount", lecturerService.getLecturerCount());
    model.addAttribute("departmentCount", departmentService.getDepartmentCount());

    model.addAttribute("activePage", "dashboard");

    return "dashboard";
}
}