package com.school.estimate.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.school.estimate.domain.Classes;
import com.school.estimate.domain.Score;
import com.school.estimate.domain.Student;
import com.school.estimate.domain.Teacher;
import com.school.estimate.service.ClassesService;
import com.school.estimate.service.ScoreService;
import com.school.estimate.service.StudentService;
import com.school.estimate.service.TeacherService;


public class PrintAction extends BaseAction{

	private ClassesService classesService;
	
	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	private TeacherService teacherService;

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	private ScoreService scoreService;

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	private List classesList = new ArrayList();

	public List getClassesList() {
		return classesList;
	}

	public void setClassesList(List classesList) {
		this.classesList = classesList;
	}


	public String toList() throws Exception{
		//获取班级列表
		List<Classes> allClasses = classesService.find("from Classes", Classes.class, null);
		for (Classes classes : allClasses) {
			Map classMap = new HashMap();
			classMap.put("id", classes.getId());
			classMap.put("name", classes.getName());
			Teacher teacher = teacherService.get(Teacher.class, classes.getBanId());
			classMap.put("ban", teacher.getName());
			
			
			classesList.add(classMap);
		}
		
		return "list";
	}
	
	private String classesId;

	public String getClassesId() {
		return classesId;
	}

	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}
	
	public String printOne() throws Exception{
		System.err.println(classesId);
		
		// 1.导入一个模板  File.separator,由系统决定是/还是\
		String path = ServletActionContext.getServletContext().getRealPath(File.separator);
		String textPath = path + "make/xlsprint/estimate.xls";
		Workbook workbook = new HSSFWorkbook(new FileInputStream(textPath));
		
		// 2.获取工作表
		Sheet sheet = workbook.getSheetAt(0);
		
		int rowIndex = 1;
		
		//查询所有classesId班级的所有学生
		Classes classes = classesService.get(Classes.class, classesId);
		
		Date startTime = classes.getStartTime();
		long startTimeMillis = startTime.getTime();
		System.err.println(startTime);
		
		long currentTimeMillis = System.currentTimeMillis();
		
		long time = currentTimeMillis - startTimeMillis;
		long days = time/(1000 * 60 * 60 * 24);
		
		int term = -1;
		
		if(days >= 0 && days < 177){
			term = 1; 
		}else if(days >= 177 && days < 365){
			term = 2;
		}else if(days >=365 && days < 542){
			term = 3;
		}else if(days >= 542 && days < 730){
			term = 4;
		}else if(days >= 907 && days < 1095){
			term = 5;
		}else if(days >= 1272 && days < 1460){
			term = 6;
		}else{
			term = -1;
		}
		
		//设置班名和学期
		Row miliTitleRow = sheet.getRow(rowIndex);
		//miliTitleRow.setHeightInPoints(18); // 设置货物信息字号
		
	    Cell miliCell1 = miliTitleRow.createCell(1);
	    miliCell1.setCellValue(classes.getName());
	    
	    Cell miliCell2 = miliTitleRow.createCell(3);
	    miliCell2.setCellValue("第"+term+"学期");
	    
	    Teacher teacher = teacherService.get(Teacher.class, classes.getBanId());
	    Cell miliCell3 = miliTitleRow.createCell(9);
	    miliCell3.setCellValue(teacher.getName());
	    
	    CellRangeAddress region = new CellRangeAddress(1,1,(short)3,(short)4);
	    sheet.addMergedRegion(region);
		
	    rowIndex = 3;
	    TreeSet<Student> studentsSet = new TreeSet<Student>(new MyComparator());
		Set<Student> students = classes.getStudents();
		for (Student student : students) {
			studentsSet.add(student);
		}
		
		for (Student student : studentsSet) {
			Row textRow = sheet.createRow(rowIndex++);
		    textRow.setHeightInPoints(18); // 设置货物信息字号
		    Cell textCell1 = textRow.createCell(0);
		    textCell1.setCellValue(student.getName());
		    
		    Cell textCell2 = textRow.createCell(1);
		    textCell2.setCellValue(student.getNo());
		    
		    Cell textCell3 = textRow.createCell(2);
		    textCell3.setCellValue(student.getNumber());
		    
		    Cell textCell4 = textRow.createCell(3);
		    String gender = student.getGender().toString();
		    if("0".equals(gender)){
		    	textCell4.setCellValue("女");
		    }else if("1".equals(gender)){
		    	textCell4.setCellValue("男");
		    }
		    
		    Cell textCell5 = textRow.createCell(4);
		    textCell5.setCellValue(student.getBackground());
		    
		    Cell textCell6 = textRow.createCell(5);
		    textCell6.setCellValue(student.getJob());
		    
		    Cell textCell7 = textRow.createCell(6);
		    textCell7.setCellValue(student.getViliage());
		    
		    Cell textCell8 = textRow.createCell(7);
		    textCell8.setCellValue(student.getAddress());
		    
		    Cell textCell9 = textRow.createCell(8);
		    textCell9.setCellValue(student.getParentName());
		    
		    Cell textCell10 = textRow.createCell(9);
		    textCell10.setCellValue(student.getParentTel());
		    
		    Cell textCell11 = textRow.createCell(10);
		    String isSingle = student.getIsSingle();
		    if("0".equals(isSingle)){
		    	textCell11.setCellValue("是");
		    }else if("1".equals(isSingle)){
		    	textCell11.setCellValue("否");
		    }
		    
		    Cell textCell12 = textRow.createCell(11);
		    
		    String hql = "from Score where studentId = " + student.getId() + " and term = " + term;
			Double sum = 0.0;
			List<Score> scoreList = scoreService.find(hql, Score.class, null);
			for (Score score : scoreList) {
				if("心理健康".equals(score.getSorbName())){
					sum  = sum + (score.getGrade()/2);
				}
				if("书法".equals(score.getSorbName())){
					sum = sum + (score.getGrade()/2);
				}
				sum = sum + score.getGrade();
			}
			textCell12.setCellValue(sum);
		    
		}
		
		// 7.向外输出excel文件
		HttpServletResponse response = ServletActionContext.getResponse();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		// 8.下载
		DownloadUtil util = new DownloadUtil();
		util.download(outputStream, response, classes.getName() + "评价.xls");
		
		
		return toList();
	}
}

class MyComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getId() - o2.getId();
	}
	
}
