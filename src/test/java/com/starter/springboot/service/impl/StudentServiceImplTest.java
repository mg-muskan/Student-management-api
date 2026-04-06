package com.starter.springboot.service.impl;

import com.starter.springboot.dto.AddStudentRequestDTO;
import com.starter.springboot.dto.StudentDTO;
import com.starter.springboot.entity.Student;
import com.starter.springboot.exception.StudentNotFoundException;
import com.starter.springboot.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    // Using @Spy with a real instance so ModelMapper actually maps fields
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private StudentServiceImpl studentService;

    // Helper: build a Student without a constructor (entity has only setters)
    private Student buildStudent(Long id, String name, String email) {
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setEmail(email);
        return s;
    }

    @Test
    void getAllStudents_returnsMappedList() {
        when(studentRepository.findAll()).thenReturn(List.of(
                buildStudent(1L, "Alice", "alice@example.com"),
                buildStudent(2L, "Bob", "bob@example.com")
        ));

        List<StudentDTO> result = studentService.getAllStudents();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Alice");
        assertThat(result.get(1).getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    void getStudentById_found_returnsStudentDTO() {
        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(buildStudent(1L, "Alice", "alice@example.com")));

        StudentDTO result = studentService.getStudentById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Alice");
    }

    @Test
    void getStudentById_notFound_throwsStudentNotFoundException() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createNewStudent_savesAndReturnsDTO() {
        AddStudentRequestDTO request = new AddStudentRequestDTO();
        request.setName("Carol");
        request.setEmail("carol@example.com");

        when(studentRepository.save(any(Student.class)))
                .thenReturn(buildStudent(3L, "Carol", "carol@example.com"));

        StudentDTO result = studentService.createNewStudent(request);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("Carol");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void deleteStudent_notFound_throwsAndNeverDeletes() {
        when(studentRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> studentService.deleteStudent(99L))
                .isInstanceOf(StudentNotFoundException.class);

        verify(studentRepository, never()).deleteById(any());
    }
}
