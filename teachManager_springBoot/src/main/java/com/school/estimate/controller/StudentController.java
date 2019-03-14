package com.school.estimate.controller;

import com.school.estimate.domain.Student;
import com.school.estimate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String gotoIndex(Model model) {
        List<Student> list = studentService.findAllStudent();
        model.addAttribute("studentList", list);
        return "/manage/student/jStudentView";
    }
}
