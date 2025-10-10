package com.starter.springboot.service;

import com.starter.springboot.dto.AddStudentRequestDTO;
import com.starter.springboot.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO);

    void deleteStudent(Long id);

    StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO);

    StudentDTO updatePatchStudent(Long id, Map<Object, Object> updates);
}
