package com.starter.springboot.service.impl;

import com.starter.springboot.dto.AddStudentRequestDTO;
import com.starter.springboot.dto.StudentDTO;
import com.starter.springboot.entity.Student;
import com.starter.springboot.repository.StudentRepository;
import com.starter.springboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail())).toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));
        // Instead of stream using modelMapper
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO) {
        Student newStudent = modelMapper.map(addStudentRequestDTO, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
//        studentRepository.delete(studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not dounf with id " + id)));
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist for id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));
        modelMapper.map(addStudentRequestDTO, student);
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }

    @Override
    public StudentDTO updatePatchStudent(Long id, Map<Object, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));
        updates.forEach((key, value) -> {
            if (key.equals("name")) {
                student.setName(value.toString());
            } else if (key.equals("email")) {
                student.setEmail(value.toString());
            } else {
                throw new IllegalArgumentException("Student cannot be updated for the field: " + key);
            }
        });
        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }
}
