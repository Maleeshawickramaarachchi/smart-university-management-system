package com.university.sums.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.university.sums.entity.Lecturer;
import com.university.sums.repository.LecturerRepository;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    public Lecturer updateLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }

    public long getLecturerCount() {
        return lecturerRepository.count();
    }

    public List<Lecturer> searchLecturers(String keyword) {

    if(keyword == null || keyword.isEmpty()) {
        return lecturerRepository.findAll();
    }

    return lecturerRepository.findByNameContainingIgnoreCase(keyword);
}

public Page<Lecturer> getLecturerPage(int pageNo, int pageSize) {

    Pageable pageable = PageRequest.of(pageNo, pageSize);

    return lecturerRepository.findAll(pageable);
}


public Page<Lecturer> searchLecturers(String keyword, int pageNo, int pageSize) {

    Pageable pageable = PageRequest.of(pageNo, pageSize);

    return lecturerRepository.findByNameContainingIgnoreCase(keyword, pageable);
}
}