package com.school.estimate.service.impl;

import com.school.estimate.dao.StudentDao;
import com.school.estimate.domain.Student;
import com.school.estimate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentDao.findStudentById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }

    @Override
    public Long saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public Long updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public Long deleteStudent(Long id) {
        return studentDao.deleteStudent(id);
    }
}
