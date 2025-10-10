package com.starter.springboot.controller;

import com.starter.springboot.dto.StudentDTO;
import com.starter.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Deprecated
@RestController
public class StudentAPIDeprecatedController {

    @Autowired
    private StudentService studentService;

    // 2nd way - Constructor DI
//    private final StudentRepository studentRepository;
//
//    public StudentAPIController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    @GetMapping("/students/get")
    public List<StudentDTO> getStudents() {
//        return studentRepository.findAll();
        return studentService.getAllStudents();
    }

    @GetMapping("/students/get/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

}
