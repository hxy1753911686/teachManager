package com.school.estimate.action;

import java.util.List;

import com.school.estimate.domain.Student;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.User;
import com.school.estimate.service.StudentService;
import com.school.estimate.service.TeacherService;

public class PageAction extends BaseAction{
	
	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private TeacherService teacherService;
	
	
	public TeacherService getTeacherService() {
		return teacherService;
	}


	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}


	public String toIndex() throws Exception{
		User user = (User) session.get("_CURRENT_USER");
		if(user != null && !user.equals(null)){
			String hql = "from Teacher where number = '" + user.getNumber() +"'";
			List<Teacher> teacherList = teacherService.find(hql, Teacher.class,null);
			
			if(teacherList != null && teacherList.size() != 0){
				session.put("_user_name", teacherList.get(0).getName());
			}else{
				String hql1 = "from Student where number = '" + user.getNumber() +"'";
				List<Student> studentList = studentService.find(hql1, Student.class, null);
				
				if(studentList != null && studentList.size() != 0){
					session.put("_user_name", studentList.get(0).getName());
				}
			}
		}
		return "index";
	}
}
