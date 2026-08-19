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

import com.university.sums.entity.Student;
import com.university.sums.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // Display all students
 @GetMapping("/students")
public String listStudents(

        @RequestParam(defaultValue = "") String keyword,

        @RequestParam(defaultValue = "0") int page,

        Model model) {

    Page<Student> studentPage =
            studentService.searchAndPaginate(keyword, page);

    model.addAttribute("students", studentPage.getContent());

    model.addAttribute("currentPage", page);

    model.addAttribute("totalPages", studentPage.getTotalPages());

    model.addAttribute("keyword", keyword);

    model.addAttribute("activePage", "students");

    return "students";
}


    // Open add student form
    @GetMapping("/students/new")
    public String addStudentForm(Model model) {

        Student student = new Student();

        model.addAttribute("student", student);

        return "add-student";
    }


    // Save student
    
   @PostMapping("/students/save")
public String saveStudent(@Valid @ModelAttribute("student") Student student,
                          BindingResult result) {

    if (result.hasErrors()) {
        return "add-student";
    }

    studentService.saveStudent(student);

    return "redirect:/students";
}

    @GetMapping("/students/edit/{id}")
public String editStudentForm(@PathVariable Long id, Model model) {

    Student student = studentService.getStudentById(id);

    model.addAttribute("student", student);

    return "edit-student";
}

@PostMapping("/students/update")
public String updateStudent(@Valid @ModelAttribute("student") Student student,
                            BindingResult result) {

    if (result.hasErrors()) {
        return "edit-student";
    }

    studentService.updateStudent(student);

    return "redirect:/students";
}



@GetMapping("/students/delete/{id}")
public String deleteStudent(@PathVariable Long id) {

    studentService.deleteStudent(id);

    return "redirect:/students";
}
}