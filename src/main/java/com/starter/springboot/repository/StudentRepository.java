package com.starter.springboot.repository;

import com.starter.springboot.dto.StudentDTO;
import com.starter.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
