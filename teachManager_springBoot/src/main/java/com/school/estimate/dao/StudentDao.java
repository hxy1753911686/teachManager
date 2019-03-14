package com.school.estimate.dao;

import com.school.estimate.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao{
    List<Student> findByName(@Param("name") String name);
    Student findStudentById(@Param("id") Long id);
    List<Student> findAllStudent();
    Long saveStudent(Student student);
    Long updateStudent(Student student);
    Long deleteStudent(@Param("id") Long id);
}
