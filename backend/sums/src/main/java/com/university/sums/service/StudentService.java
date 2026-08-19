package com.university.sums.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.university.sums.entity.Student;
import com.university.sums.repository.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

   public Page<Student> searchAndPaginate(String keyword, int page) {

    PageRequest pageable = PageRequest.of(page, 5);

    if (keyword == null || keyword.isBlank()) {
        return studentRepository.findAll(pageable);
    }

    return studentRepository.findByNameContainingIgnoreCase(keyword, pageable);

}

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public long getStudentCount() {
    return studentRepository.count();
}
}