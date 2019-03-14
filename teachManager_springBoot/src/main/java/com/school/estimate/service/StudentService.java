package com.school.estimate.service;

import com.school.estimate.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> findByName(String name);

    Student findStudentById(Long id);

    List<Student> findAllStudent();

    Long saveStudent(Student student);

    Long updateStudent(Student student);

    Long deleteStudent(Long id);
}
