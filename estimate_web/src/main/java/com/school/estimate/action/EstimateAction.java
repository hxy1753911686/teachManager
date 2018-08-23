package com.school.estimate.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.domain.Classes;
import com.school.estimate.domain.Estimate;
import com.school.estimate.domain.Score;
import com.school.estimate.domain.Student;
import com.school.estimate.domain.Subject;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.TeacherClassManage;
import com.school.estimate.domain.User;
import com.school.estimate.service.ClassesService;
import com.school.estimate.service.EstimateService;
import com.school.estimate.service.ScoreService;
import com.school.estimate.service.StudentService;
import com.school.estimate.service.SubjectService;
import com.school.estimate.service.TeacherClassManageService;
import com.school.estimate.service.TeacherService;

public class EstimateAction extends BaseAction implements ModelDriven<Estimate>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * TODO
	 * 先用全局异常处理 来导向到错误页面，后期再添加这些模块
	 * @return
	 * @throws Exception
	 */
	public String toList() {
		return "toList";
	}
	
	private EstimateService estimateService;
	
	public void setEstimateService(EstimateService estimateService) {
		this.estimateService = estimateService;
	}

	private SubjectService subjectService;
	
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	private TeacherClassManageService teacherClassManageService;
	
	public void setTeacherClassManageService(TeacherClassManageService teacherClassManageService) {
		this.teacherClassManageService = teacherClassManageService;
	}

	private ScoreService scoreService;
	
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private ClassesService classesService;
	
	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	private Estimate role = new Estimate();
	
	public Estimate getModel() {
		return role;
	}
	
	private Integer studentId;
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	private Integer term;
	

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String toClassStudent() throws Exception{
		//通过当前登陆的session用户,来找到所带的班级
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		
		String hql1 = "from Classes where banId = " + teacherId;
		List<Classes> classesList = classesService.find(hql1, Classes.class, null);
		Classes classes = null;
		if(classesList != null && classesList.size() != 0){
			classes = classesList.get(0);
		}
		if(classes != null){
			ActionContext.getContext().getValueStack().push(classes);
			
			Set<Student> studentsSet = classes.getStudents();
			ActionContext.getContext().put("studentsSet", studentsSet);
			
			Date startTime = classes.getStartTime();
			long startTimeMillis = startTime.getTime();
			System.err.println(startTime);
			
			long currentTimeMillis = System.currentTimeMillis();
			
			long time = currentTimeMillis - startTimeMillis;
			long days = time/(1000 * 60 * 60 * 24);
			
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
			
		}
		
		return "toClassStudent";
	}
	
	

	public String toBanEstimate() throws Exception{
		//通过studentId找到对应的学生
		Student student = studentService.get(Student.class, studentId);
		ActionContext.getContext().getValueStack().push(student);
		
		String hql = "from Score where studentId = " + studentId + " and term = " + term +" and sorbName = '班主任'";
		List<Score> scoreList = scoreService.find(hql, Score.class,	null);
		for (Score score : scoreList) {
			if("3028f5816137c664016137c8f4900000".equals(score.getEstimateId())){
				honourValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					honourDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900001".equals(score.getEstimateId())){
				bigThingValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					bigThingDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900002".equals(score.getEstimateId())){
				nationalFlagValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					nationalFlagDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900003".equals(score.getEstimateId())){
				nationalAnthemValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					nationalAnthemDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900004".equals(score.getEstimateId())){
				upAndDownValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					upAndDownDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900005".equals(score.getEstimateId())){
				classMeettingValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					classMeettingDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900006".equals(score.getEstimateId())){
				layerValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					layerDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900007".equals(score.getEstimateId())){
				activityValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				System.err.println((currentTimeMillis - oldTime)/(100*60*60*24));
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					activityDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900008".equals(score.getEstimateId())){
				civilizationValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					civilizationDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900009".equals(score.getEstimateId())){
				creditValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					creditDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900010".equals(score.getEstimateId())){
				respectValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					respectDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900011".equals(score.getEstimateId())){
				dignityValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					dignityDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900012".equals(score.getEstimateId())){
				desireAdvanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					desireAdvanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900013".equals(score.getEstimateId())){
				wrongAndRightValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					wrongAndRightDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900014".equals(score.getEstimateId())){
				collectiveValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					collectiveDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900015".equals(score.getEstimateId())){
				andOtherValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					andOtherDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900016".equals(score.getEstimateId())){
				classHealthValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					classHealthDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900017".equals(score.getEstimateId())){
				schoolHealthValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					schoolHealthDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900018".equals(score.getEstimateId())){
				attendValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					attendDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900019".equals(score.getEstimateId())){
				cleanValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					cleanDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900020".equals(score.getEstimateId())){
				learnAttitudeValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					learnAttitudeDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900021".equals(score.getEstimateId())){
				learnHabitsValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					learnHabitsDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900022".equals(score.getEstimateId())){
				ownLearnValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					ownLearnDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900023".equals(score.getEstimateId())){
				teacherTaskValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					teacherTaskDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900024".equals(score.getEstimateId())){
				moreExamValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					moreExamDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900025".equals(score.getEstimateId())){
				topicValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					topicDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900026".equals(score.getEstimateId())){
				planValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					planDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900027".equals(score.getEstimateId())){
				processValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					processDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900028".equals(score.getEstimateId())){
				researchValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					researchDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900029".equals(score.getEstimateId())){
				photoValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					photoDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900030".equals(score.getEstimateId())){
				summaryValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					summaryDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900031".equals(score.getEstimateId())){
				scienceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					scienceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900032".equals(score.getEstimateId())){
				canjiaValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					canjiaDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900033".equals(score.getEstimateId())){
				awardValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					awardDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900034".equals(score.getEstimateId())){
				doMeatValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					doMeatDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900035".equals(score.getEstimateId())){
				musicValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					musicDisabled = "yes";
				}
			}
		}
		
		return "toBanEstimate";
	}
	
	private Double honourValue;			//维护国家荣耀
	private String honourDisabled;
	
	private Double bigThingValue;		//关心国家大事
	private String bigThingDisabled;
	
	private Double nationalFlagValue;	//尊重国旗国徽
	private String nationalFlagDisabled;
	
	private Double nationalAnthemValue;	//会唱国歌团歌校歌
	private String nationalAnthemDisabled;
	
	private Double upAndDownValue;		//升降国旗有礼仪	
	private String upAndDownDisabled;
	
	private Double classMeettingValue;	//班会活动
	private String classMeettingDisabled;
	
	private Double layerValue;			//遵守校纪校规
	private String layerDisabled;
	
	private Double activityValue;		//集体活动
	private String activityDisabled;
	
	private Double civilizationValue;	//讲文明,懂礼仪
	private String civilizationDisabled;
	
	private Double creditValue;			//讲信用,守诺言
	private String creditDisabled;
	
	private Double respectValue;		//尊敬师长,有感恩之心
	private String respectDisabled;
	
	private Double dignityValue;		//维护人格尊严
	private String dignityDisabled;
	
	private Double desireAdvanceValue;	//有上进心
	private String desireAdvanceDisabled;
	
	private Double wrongAndRightValue;	//正确对待优缺点，有错就改
	private String wrongAndRightDisabled;
	
	private Double collectiveValue;		//关心集体,珍惜荣誉
	private String collectiveDisabled;
	
	private Double andOtherValue;		//善于和他人合作共同完成任务
	private String andOtherDisabled;
	
	private Double classHealthValue;	//爱护教室环境卫生
	private String classHealthDisabled;
	
	private Double schoolHealthValue;	//爱护校园卫生
	private String schoolHealthDisabled;
	
	private Double attendValue;			//积极参加环保活动
	private String attendDisabled;
	
	private Double cleanValue;			//值日次数
	private String cleanDisabled;
	

	public Double getHonourValue() {
		return honourValue;
	}

	public void setHonourValue(Double honourValue) {
		this.honourValue = honourValue;
	}

	public String getHonourDisabled() {
		return honourDisabled;
	}

	public void setHonourDisabled(String honourDisabled) {
		this.honourDisabled = honourDisabled;
	}

	public Double getBigThingValue() {
		return bigThingValue;
	}

	public void setBigThingValue(Double bigThingValue) {
		this.bigThingValue = bigThingValue;
	}

	public String getBigThingDisabled() {
		return bigThingDisabled;
	}

	public void setBigThingDisabled(String bigThingDisabled) {
		this.bigThingDisabled = bigThingDisabled;
	}

	public Double getNationalFlagValue() {
		return nationalFlagValue;
	}

	public void setNationalFlagValue(Double nationalFlagValue) {
		this.nationalFlagValue = nationalFlagValue;
	}

	public String getNationalFlagDisabled() {
		return nationalFlagDisabled;
	}

	public void setNationalFlagDisabled(String nationalFlagDisabled) {
		this.nationalFlagDisabled = nationalFlagDisabled;
	}

	public Double getNationalAnthemValue() {
		return nationalAnthemValue;
	}

	public void setNationalAnthemValue(Double nationalAnthemValue) {
		this.nationalAnthemValue = nationalAnthemValue;
	}

	public String getNationalAnthemDisabled() {
		return nationalAnthemDisabled;
	}

	public void setNationalAnthemDisabled(String nationalAnthemDisabled) {
		this.nationalAnthemDisabled = nationalAnthemDisabled;
	}

	public Double getUpAndDownValue() {
		return upAndDownValue;
	}

	public void setUpAndDownValue(Double upAndDownValue) {
		this.upAndDownValue = upAndDownValue;
	}

	public String getUpAndDownDisabled() {
		return upAndDownDisabled;
	}

	public void setUpAndDownDisabled(String upAndDownDisabled) {
		this.upAndDownDisabled = upAndDownDisabled;
	}

	public Double getClassMeettingValue() {
		return classMeettingValue;
	}

	public void setClassMeettingValue(Double classMeettingValue) {
		this.classMeettingValue = classMeettingValue;
	}

	public String getClassMeettingDisabled() {
		return classMeettingDisabled;
	}

	public void setClassMeettingDisabled(String classMeettingDisabled) {
		this.classMeettingDisabled = classMeettingDisabled;
	}

	public Double getLayerValue() {
		return layerValue;
	}

	public void setLayerValue(Double layerValue) {
		this.layerValue = layerValue;
	}

	public String getLayerDisabled() {
		return layerDisabled;
	}

	public void setLayerDisabled(String layerDisabled) {
		this.layerDisabled = layerDisabled;
	}

	public Double getActivityValue() {
		return activityValue;
	}

	public void setActivityValue(Double activityValue) {
		this.activityValue = activityValue;
	}

	public String getActivityDisabled() {
		return activityDisabled;
	}

	public void setActivityDisabled(String activityDisabled) {
		this.activityDisabled = activityDisabled;
	}

	public Double getCivilizationValue() {
		return civilizationValue;
	}

	public void setCivilizationValue(Double civilizationValue) {
		this.civilizationValue = civilizationValue;
	}

	public String getCivilizationDisabled() {
		return civilizationDisabled;
	}

	public void setCivilizationDisabled(String civilizationDisabled) {
		this.civilizationDisabled = civilizationDisabled;
	}

	public Double getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(Double creditValue) {
		this.creditValue = creditValue;
	}

	public String getCreditDisabled() {
		return creditDisabled;
	}

	public void setCreditDisabled(String creditDisabled) {
		this.creditDisabled = creditDisabled;
	}

	public Double getRespectValue() {
		return respectValue;
	}

	public void setRespectValue(Double respectValue) {
		this.respectValue = respectValue;
	}

	public String getRespectDisabled() {
		return respectDisabled;
	}

	public void setRespectDisabled(String respectDisabled) {
		this.respectDisabled = respectDisabled;
	}

	public Double getDignityValue() {
		return dignityValue;
	}

	public void setDignityValue(Double dignityValue) {
		this.dignityValue = dignityValue;
	}

	public String getDignityDisabled() {
		return dignityDisabled;
	}

	public void setDignityDisabled(String dignityDisabled) {
		this.dignityDisabled = dignityDisabled;
	}

	public Double getDesireAdvanceValue() {
		return desireAdvanceValue;
	}

	public void setDesireAdvanceValue(Double desireAdvanceValue) {
		this.desireAdvanceValue = desireAdvanceValue;
	}

	public String getDesireAdvanceDisabled() {
		return desireAdvanceDisabled;
	}

	public void setDesireAdvanceDisabled(String desireAdvanceDisabled) {
		this.desireAdvanceDisabled = desireAdvanceDisabled;
	}

	public Double getWrongAndRightValue() {
		return wrongAndRightValue;
	}

	public void setWrongAndRightValue(Double wrongAndRightValue) {
		this.wrongAndRightValue = wrongAndRightValue;
	}

	public String getWrongAndRightDisabled() {
		return wrongAndRightDisabled;
	}

	public void setWrongAndRightDisabled(String wrongAndRightDisabled) {
		this.wrongAndRightDisabled = wrongAndRightDisabled;
	}

	public Double getCollectiveValue() {
		return collectiveValue;
	}

	public void setCollectiveValue(Double collectiveValue) {
		this.collectiveValue = collectiveValue;
	}

	public String getCollectiveDisabled() {
		return collectiveDisabled;
	}

	public void setCollectiveDisabled(String collectiveDisabled) {
		this.collectiveDisabled = collectiveDisabled;
	}

	public Double getAndOtherValue() {
		return andOtherValue;
	}

	public void setAndOtherValue(Double andOtherValue) {
		this.andOtherValue = andOtherValue;
	}

	public String getAndOtherDisabled() {
		return andOtherDisabled;
	}

	public void setAndOtherDisabled(String andOtherDisabled) {
		this.andOtherDisabled = andOtherDisabled;
	}

	public Double getClassHealthValue() {
		return classHealthValue;
	}

	public void setClassHealthValue(Double classHealthValue) {
		this.classHealthValue = classHealthValue;
	}

	public String getClassHealthDisabled() {
		return classHealthDisabled;
	}

	public void setClassHealthDisabled(String classHealthDisabled) {
		this.classHealthDisabled = classHealthDisabled;
	}

	public Double getSchoolHealthValue() {
		return schoolHealthValue;
	}

	public void setSchoolHealthValue(Double schoolHealthValue) {
		this.schoolHealthValue = schoolHealthValue;
	}

	public String getSchoolHealthDisabled() {
		return schoolHealthDisabled;
	}

	public void setSchoolHealthDisabled(String schoolHealthDisabled) {
		this.schoolHealthDisabled = schoolHealthDisabled;
	}

	public Double getAttendValue() {
		return attendValue;
	}

	public void setAttendValue(Double attendValue) {
		this.attendValue = attendValue;
	}

	public String getAttendDisabled() {
		return attendDisabled;
	}

	public void setAttendDisabled(String attendDisabled) {
		this.attendDisabled = attendDisabled;
	}

	public Double getCleanValue() {
		return cleanValue;
	}

	public void setCleanValue(Double cleanValue) {
		this.cleanValue = cleanValue;
	}

	public String getCleanDisabled() {
		return cleanDisabled;
	}

	public void setCleanDisabled(String cleanDisabled) {
		this.cleanDisabled = cleanDisabled;
	}

	public ClassesService getClassesService() {
		return classesService;
	}

	public String saveLoveCountry() throws Exception{
		//班主任操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(honourValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900000' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(honourValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900000");
				score.setGrade(honourValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(bigThingValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900001' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(bigThingValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900001");
				score.setGrade(bigThingValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(nationalFlagValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900002' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(nationalFlagValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900002");
				score.setGrade(nationalFlagValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(nationalAnthemValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900003' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(nationalAnthemValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900003");
				score.setGrade(nationalAnthemValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(upAndDownValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900004' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(upAndDownValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900004");
				score.setGrade(upAndDownValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(classMeettingValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900005' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(classMeettingValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900005");
				score.setGrade(classMeettingValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(layerValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900006' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(layerValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900006");
				score.setGrade(layerValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(activityValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900007' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(activityValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900007");
				score.setGrade(activityValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(civilizationValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900008' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(civilizationValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900008");
				score.setGrade(civilizationValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(creditValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900009' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(creditValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900009");
				score.setGrade(creditValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(respectValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900010' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(respectValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900010");
				score.setGrade(respectValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(dignityValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900011' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(dignityValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900011");
				score.setGrade(dignityValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(desireAdvanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900012' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(desireAdvanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900012");
				score.setGrade(desireAdvanceValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(wrongAndRightValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900013' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(wrongAndRightValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900013");
				score.setGrade(wrongAndRightValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(collectiveValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900014' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(collectiveValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900014");
				score.setGrade(collectiveValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(andOtherValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900015' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(andOtherValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900015");
				score.setGrade(andOtherValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(classHealthValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900016' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(classHealthValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900016");
				score.setGrade(classHealthValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(schoolHealthValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900017' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(schoolHealthValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900017");
				score.setGrade(schoolHealthValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(attendValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900018' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(attendValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900018");
				score.setGrade(attendValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(cleanValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900019' and term = " + term +" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(cleanValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900019");
				score.setGrade(cleanValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		return "toClass";
	}
	
	private Double learnAttitudeValue;		//学习态度积极
	private String learnAttitudeDisabled;
	
	private Double learnHabitsValue;		//学习习惯良好
	private String learnHabitsDisabled;
	
	private Double ownLearnValue;		//有自主学习能力
	private String ownLearnDisabled;
	
	private Double teacherTaskValue;		//能够完成老师下达的学习任务
	private String teacherTaskDisabled;
	
	private Double moreExamValue;		//阶段性考试成绩良好
	private String moreExamDisabled;
	
	public Double getLearnAttitudeValue() {
		return learnAttitudeValue;
	}

	public void setLearnAttitudeValue(Double learnAttitudeValue) {
		this.learnAttitudeValue = learnAttitudeValue;
	}

	public String getLearnAttitudeDisabled() {
		return learnAttitudeDisabled;
	}

	public void setLearnAttitudeDisabled(String learnAttitudeDisabled) {
		this.learnAttitudeDisabled = learnAttitudeDisabled;
	}

	public Double getLearnHabitsValue() {
		return learnHabitsValue;
	}

	public void setLearnHabitsValue(Double learnHabitsValue) {
		this.learnHabitsValue = learnHabitsValue;
	}

	public String getLearnHabitsDisabled() {
		return learnHabitsDisabled;
	}

	public void setLearnHabitsDisabled(String learnHabitsDisabled) {
		this.learnHabitsDisabled = learnHabitsDisabled;
	}

	public Double getOwnLearnValue() {
		return ownLearnValue;
	}

	public void setOwnLearnValue(Double ownLearnValue) {
		this.ownLearnValue = ownLearnValue;
	}

	public String getOwnLearnDisabled() {
		return ownLearnDisabled;
	}

	public void setOwnLearnDisabled(String ownLearnDisabled) {
		this.ownLearnDisabled = ownLearnDisabled;
	}

	public Double getTeacherTaskValue() {
		return teacherTaskValue;
	}

	public void setTeacherTaskValue(Double teacherTaskValue) {
		this.teacherTaskValue = teacherTaskValue;
	}

	public String getTeacherTaskDisabled() {
		return teacherTaskDisabled;
	}

	public void setTeacherTaskDisabled(String teacherTaskDisabled) {
		this.teacherTaskDisabled = teacherTaskDisabled;
	}

	public Double getMoreExamValue() {
		return moreExamValue;
	}

	public void setMoreExamValue(Double moreExamValue) {
		this.moreExamValue = moreExamValue;
	}

	public String getMoreExamDisabled() {
		return moreExamDisabled;
	}

	public void setMoreExamDisabled(String moreExamDisabled) {
		this.moreExamDisabled = moreExamDisabled;
	}

	public String saveMethod() throws Exception{
		//班主任操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(learnAttitudeValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900020' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(learnAttitudeValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900020");
				score.setGrade(learnAttitudeValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(learnHabitsValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900021' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(learnHabitsValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900021");
				score.setGrade(learnHabitsValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(ownLearnValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900022' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(ownLearnValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900022");
				score.setGrade(ownLearnValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(teacherTaskValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900023' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(teacherTaskValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900023");
				score.setGrade(teacherTaskValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(moreExamValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900024' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(moreExamValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900024");
				score.setGrade(moreExamValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		return "toClass";
	}
	
	private Double topicValue;			//选择好课题
	private String topicDisabled;
	
	private Double planValue;			//制定计划
	private String planDisabled;
	
	private Double processValue;			//过程性资料
	private String processDisabled;
	
	private Double researchValue;			//研究报告
	private String researchDisabled;
	
	private Double photoValue;			//有照片，视频
	private String photoDisabled;
	
	private Double summaryValue;			//总结
	private String summaryDisabled;
	
	private Double scienceValue;			//科技创新
	private String scienceDisabled;
	
	public Double getTopicValue() {
		return topicValue;
	}

	public void setTopicValue(Double topicValue) {
		this.topicValue = topicValue;
	}

	public String getTopicDisabled() {
		return topicDisabled;
	}

	public void setTopicDisabled(String topicDisabled) {
		this.topicDisabled = topicDisabled;
	}

	public Double getPlanValue() {
		return planValue;
	}

	public void setPlanValue(Double planValue) {
		this.planValue = planValue;
	}

	public String getPlanDisabled() {
		return planDisabled;
	}

	public void setPlanDisabled(String planDisabled) {
		this.planDisabled = planDisabled;
	}

	public Double getProcessValue() {
		return processValue;
	}

	public void setProcessValue(Double processValue) {
		this.processValue = processValue;
	}

	public String getProcessDisabled() {
		return processDisabled;
	}

	public void setProcessDisabled(String processDisabled) {
		this.processDisabled = processDisabled;
	}

	public Double getResearchValue() {
		return researchValue;
	}

	public void setResearchValue(Double researchValue) {
		this.researchValue = researchValue;
	}

	public String getResearchDisabled() {
		return researchDisabled;
	}

	public void setResearchDisabled(String researchDisabled) {
		this.researchDisabled = researchDisabled;
	}

	public Double getPhotoValue() {
		return photoValue;
	}

	public void setPhotoValue(Double photoValue) {
		this.photoValue = photoValue;
	}

	public String getPhotoDisabled() {
		return photoDisabled;
	}

	public void setPhotoDisabled(String photoDisabled) {
		this.photoDisabled = photoDisabled;
	}

	public Double getSummaryValue() {
		return summaryValue;
	}

	public void setSummaryValue(Double summaryValue) {
		this.summaryValue = summaryValue;
	}

	public String getSummaryDisabled() {
		return summaryDisabled;
	}

	public void setSummaryDisabled(String summaryDisabled) {
		this.summaryDisabled = summaryDisabled;
	}

	public Double getScienceValue() {
		return scienceValue;
	}

	public void setScienceValue(Double scienceValue) {
		this.scienceValue = scienceValue;
	}

	public String getScienceDisabled() {
		return scienceDisabled;
	}

	public void setScienceDisabled(String scienceDisabled) {
		this.scienceDisabled = scienceDisabled;
	}

	public String saveStudy() throws Exception{
		//班主任操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(topicValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900025' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(topicValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900025");
				score.setGrade(topicValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(planValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900026' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(planValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900026");
				score.setGrade(planValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(processValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900027' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(processValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900027");
				score.setGrade(processValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(researchValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900028' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(researchValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900028");
				score.setGrade(researchValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(photoValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900029' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(photoValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900029");
				score.setGrade(photoValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(summaryValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900030' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(summaryValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900030");
				score.setGrade(summaryValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(scienceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900031' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(scienceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900031");
				score.setGrade(scienceValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		
		return "toClass";
	}
	
	private Double canjiaValue;		//参加与效果
	private String canjiaDisabled;
	
	private Double awardValue;		//获奖
	private String awardDisabled;
	
	private Double doMeatValue;		//做饭
	private String doMeatDisabled;
	
	private Double musicValue;		//音乐或美术技能
	private String musicDisabled;
	
	
	public Double getCanjiaValue() {
		return canjiaValue;
	}

	public void setCanjiaValue(Double canjiaValue) {
		this.canjiaValue = canjiaValue;
	}

	public String getCanjiaDisabled() {
		return canjiaDisabled;
	}

	public void setCanjiaDisabled(String canjiaDisabled) {
		this.canjiaDisabled = canjiaDisabled;
	}

	public Double getAwardValue() {
		return awardValue;
	}

	public void setAwardValue(Double awardValue) {
		this.awardValue = awardValue;
	}

	public String getAwardDisabled() {
		return awardDisabled;
	}

	public void setAwardDisabled(String awardDisabled) {
		this.awardDisabled = awardDisabled;
	}

	public Double getDoMeatValue() {
		return doMeatValue;
	}

	public void setDoMeatValue(Double doMeatValue) {
		this.doMeatValue = doMeatValue;
	}

	public String getDoMeatDisabled() {
		return doMeatDisabled;
	}

	public void setDoMeatDisabled(String doMeatDisabled) {
		this.doMeatDisabled = doMeatDisabled;
	}

	public Double getMusicValue() {
		return musicValue;
	}

	public void setMusicValue(Double musicValue) {
		this.musicValue = musicValue;
	}

	public String getMusicDisabled() {
		return musicDisabled;
	}

	public void setMusicDisabled(String musicDisabled) {
		this.musicDisabled = musicDisabled;
	}

	public String saveHealth() throws Exception{
		
		//班主任操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(canjiaValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900032' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(canjiaValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900032");
				score.setGrade(canjiaValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(awardValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900033' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(awardValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900033");
				score.setGrade(awardValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(doMeatValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900034' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(doMeatValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900034");
				score.setGrade(doMeatValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(musicValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900035' and term = " + term+" and studentId = "+studentId;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(musicValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900035");
				score.setGrade(musicValue);
				score.setSorbName("班主任");
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		
		return "toClass";
	}
	
	public String toSubjectList() throws Exception{
		//判断是否为班主任,如果是，则去BanSubjectList
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		
		String hql2 = "from TeacherClassManage where teacherId = " + teacherId;
		List<TeacherClassManage> teacherClassManageList = teacherClassManageService.find(hql2, TeacherClassManage.class, null);
		
		List estimateList = new ArrayList();
		
		for (TeacherClassManage teacherClassManage : teacherClassManageList) {
			Map map = new HashMap();
			Classes classes = classesService.get(Classes.class, teacherClassManage.getClassId());
			map.put("className", classes.getName());
			map.put("classId", classes.getId());
			
			Subject subject = subjectService.get(Subject.class, teacherClassManage.getSubjectId());
			map.put("subjectName", subject.getSubjectName());
			map.put("subjectId", subject.getId());
			
			estimateList.add(map);
		}
		
		ActionContext.getContext().put("estimateList", estimateList);
		
		//获得teacherId后和所有班级的banId比较
		List<Classes> classesList = classesService.find("from Classes", Classes.class, null);
		for (Classes classes : classesList) {
			if(teacherId != null && teacherId.equals(classes.getBanId())){
				return "toBanSubjectList";
			}
		}
		return "toSubjectList";
	}
	
	private String classId;
	private String subjectId;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String toClass() throws Exception{
		//根据classId找到对应的班级
		Classes classes = classesService.get(Classes.class, classId);
		
		if(classes != null){
			ActionContext.getContext().getValueStack().push(classes);
			
			Set<Student> studentsSet = classes.getStudents();
			ActionContext.getContext().put("studentsSet", studentsSet);
			
			Date startTime = classes.getStartTime();
			long startTimeMillis = startTime.getTime();
			
			long currentTimeMillis = System.currentTimeMillis();
			
			long time = currentTimeMillis - startTimeMillis;
			long days = time/(1000 * 60 * 60 * 24);
			
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
			
		}
		
		Subject subject = subjectService.get(Subject.class, subjectId);
		ActionContext.getContext().put("subjectName", subject.getSubjectName());
		ActionContext.getContext().put("subjectId", subject.getId());
		
		return "toSubjectClass";
	}
	
	public String toSubjectEstimate() throws Exception{
		//通过studentId找到对应的学生
		Student student = studentService.get(Student.class, studentId);
		ActionContext.getContext().getValueStack().push(student);
		
		//获取当前User
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Subject subject = subjectService.get(Subject.class, subjectId);
		ActionContext.getContext().put("subjectName", subject.getSubjectName());
		
		//查看用户评价过的
		String hql1 = "from Score where studentId = " + studentId + " and term = " + term +" and sorbName = '" + subject.getSubjectName() +"'";
		List<Score> scoreList = scoreService.find(hql1, Score.class,null);
		for (Score score : scoreList) {
			if("3028f5816137c664016137c8f4900036".equals(score.getEstimateId())){
				attendanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					attendanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900037".equals(score.getEstimateId())){
				performanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					performanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900038".equals(score.getEstimateId())){
				taskValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					taskDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900039".equals(score.getEstimateId())){
				winningValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					winningDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900040".equals(score.getEstimateId())){
				examResultsValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					examResultsDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900043".equals(score.getEstimateId())){
				musicAttendanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					musicAttendanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900044".equals(score.getEstimateId())){
				musicPerformanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					musicPerformanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900045".equals(score.getEstimateId())){
				instrumentsValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					instrumentsDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900046".equals(score.getEstimateId())){
				musicScoreValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					musicScoreDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900047".equals(score.getEstimateId())){
				artAttendanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					artAttendanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900048".equals(score.getEstimateId())){
				artPerformanceValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					artPerformanceDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900049".equals(score.getEstimateId())){
				paintingValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					paintingDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900050".equals(score.getEstimateId())){
				manualValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					manualDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900041".equals(score.getEstimateId())){
				outsideReadingValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					outsideReadingDisabled = "yes";
				}
			}
			if("3028f5816137c664016137c8f4900042".equals(score.getEstimateId())){
				opinionValue = score.getGrade();
				Date time = score.getTime();
				long oldTime = time.getTime();
				
				long currentTimeMillis = System.currentTimeMillis();
				if((currentTimeMillis - oldTime)/(1000*60*60*24) > 1){
					opinionDisabled = "yes";
				}
			}
		}
		//判断科目是啥，然后跳转页面不同
		if("语文".equals(subject.getSubjectName())){
			return "toChineseEstimate";
		}
		if("体育".equals(subject.getSubjectName())){
			return "toSportsEstimate";
		}
		if("音乐".equals(subject.getSubjectName())){
			return "toMusicEstimate";
		}
		if("美术".equals(subject.getSubjectName())){
			return "toArtEstimate";
		}
		return "toSubjectEstimate";
	}
	
	private Double attendanceValue;		//上课考勤
	private String attendanceDisabled;
	
	private Double performanceValue;		//上课表现
	private String performanceDisabled;
	
	private Double taskValue;		//作业
	private String taskDisabled;
	
	private Double winningValue;		//学科获奖
	private String winningDisabled;
	
	private Double examResultsValue;		//考试成绩
	private String examResultsDisabled;
	
	private Double musicAttendanceValue;		//课堂出勤
	private String musicAttendanceDisabled;
	
	private Double musicPerformanceValue;		//课堂表现
	private String musicPerformanceDisabled;
	
	private Double instrumentsValue;		//歌曲乐器
	private String instrumentsDisabled;
	
	private Double musicScoreValue;		//识谱
	private String musicScoreDisabled;
	
	private Double artAttendanceValue;		//课堂出勤
	private String artAttendanceDisabled;
	
	private Double artPerformanceValue;		//课堂表现
	private String artPerformanceDisabled;
	
	private Double paintingValue;		//绘画作品
	private String paintingDisabled;
	
	private Double manualValue;		//手工作品
	private String manualDisabled;
	
	private Double outsideReadingValue;		//手工作品
	private String outsideReadingDisabled;
	
	private Double opinionValue;		//手工作品
	private String opinionDisabled;
	
	public Double getMusicAttendanceValue() {
		return musicAttendanceValue;
	}

	public void setMusicAttendanceValue(Double musicAttendanceValue) {
		this.musicAttendanceValue = musicAttendanceValue;
	}

	public String getMusicAttendanceDisabled() {
		return musicAttendanceDisabled;
	}

	public void setMusicAttendanceDisabled(String musicAttendanceDisabled) {
		this.musicAttendanceDisabled = musicAttendanceDisabled;
	}

	public Double getMusicPerformanceValue() {
		return musicPerformanceValue;
	}

	public void setMusicPerformanceValue(Double musicPerformanceValue) {
		this.musicPerformanceValue = musicPerformanceValue;
	}

	public String getMusicPerformanceDisabled() {
		return musicPerformanceDisabled;
	}

	public void setMusicPerformanceDisabled(String musicPerformanceDisabled) {
		this.musicPerformanceDisabled = musicPerformanceDisabled;
	}

	public Double getInstrumentsValue() {
		return instrumentsValue;
	}

	public void setInstrumentsValue(Double instrumentsValue) {
		this.instrumentsValue = instrumentsValue;
	}

	public String getInstrumentsDisabled() {
		return instrumentsDisabled;
	}

	public void setInstrumentsDisabled(String instrumentsDisabled) {
		this.instrumentsDisabled = instrumentsDisabled;
	}

	public Double getMusicScoreValue() {
		return musicScoreValue;
	}

	public void setMusicScoreValue(Double musicScoreValue) {
		this.musicScoreValue = musicScoreValue;
	}

	public String getMusicScoreDisabled() {
		return musicScoreDisabled;
	}

	public void setMusicScoreDisabled(String musicScoreDisabled) {
		this.musicScoreDisabled = musicScoreDisabled;
	}

	public Double getArtAttendanceValue() {
		return artAttendanceValue;
	}

	public void setArtAttendanceValue(Double artAttendanceValue) {
		this.artAttendanceValue = artAttendanceValue;
	}

	public String getArtAttendanceDisabled() {
		return artAttendanceDisabled;
	}

	public void setArtAttendanceDisabled(String artAttendanceDisabled) {
		this.artAttendanceDisabled = artAttendanceDisabled;
	}

	public Double getArtPerformanceValue() {
		return artPerformanceValue;
	}

	public void setArtPerformanceValue(Double artPerformanceValue) {
		this.artPerformanceValue = artPerformanceValue;
	}

	public String getArtPerformanceDisabled() {
		return artPerformanceDisabled;
	}

	public void setArtPerformanceDisabled(String artPerformanceDisabled) {
		this.artPerformanceDisabled = artPerformanceDisabled;
	}

	public Double getPaintingValue() {
		return paintingValue;
	}

	public void setPaintingValue(Double paintingValue) {
		this.paintingValue = paintingValue;
	}

	public String getPaintingDisabled() {
		return paintingDisabled;
	}

	public void setPaintingDisabled(String paintingDisabled) {
		this.paintingDisabled = paintingDisabled;
	}

	public Double getManualValue() {
		return manualValue;
	}

	public void setManualValue(Double manualValue) {
		this.manualValue = manualValue;
	}

	public String getManualDisabled() {
		return manualDisabled;
	}

	public void setManualDisabled(String manualDisabled) {
		this.manualDisabled = manualDisabled;
	}

	public Double getOutsideReadingValue() {
		return outsideReadingValue;
	}

	public void setOutsideReadingValue(Double outsideReadingValue) {
		this.outsideReadingValue = outsideReadingValue;
	}

	public String getOutsideReadingDisabled() {
		return outsideReadingDisabled;
	}

	public void setOutsideReadingDisabled(String outsideReadingDisabled) {
		this.outsideReadingDisabled = outsideReadingDisabled;
	}

	public Double getOpinionValue() {
		return opinionValue;
	}

	public void setOpinionValue(Double opinionValue) {
		this.opinionValue = opinionValue;
	}

	public String getOpinionDisabled() {
		return opinionDisabled;
	}

	public void setOpinionDisabled(String opinionDisabled) {
		this.opinionDisabled = opinionDisabled;
	}

	public Double getAttendanceValue() {
		return attendanceValue;
	}

	public void setAttendanceValue(Double attendanceValue) {
		this.attendanceValue = attendanceValue;
	}

	public String getAttendanceDisabled() {
		return attendanceDisabled;
	}

	public void setAttendanceDisabled(String attendanceDisabled) {
		this.attendanceDisabled = attendanceDisabled;
	}

	public Double getPerformanceValue() {
		return performanceValue;
	}

	public void setPerformanceValue(Double performanceValue) {
		this.performanceValue = performanceValue;
	}

	public String getPerformanceDisabled() {
		return performanceDisabled;
	}

	public void setPerformanceDisabled(String performanceDisabled) {
		this.performanceDisabled = performanceDisabled;
	}

	public Double getTaskValue() {
		return taskValue;
	}

	public void setTaskValue(Double taskValue) {
		this.taskValue = taskValue;
	}

	public String getTaskDisabled() {
		return taskDisabled;
	}

	public void setTaskDisabled(String taskDisabled) {
		this.taskDisabled = taskDisabled;
	}

	public Double getWinningValue() {
		return winningValue;
	}

	public void setWinningValue(Double winningValue) {
		this.winningValue = winningValue;
	}

	public String getWinningDisabled() {
		return winningDisabled;
	}

	public void setWinningDisabled(String winningDisabled) {
		this.winningDisabled = winningDisabled;
	}

	public Double getExamResultsValue() {
		return examResultsValue;
	}

	public void setExamResultsValue(Double examResultsValue) {
		this.examResultsValue = examResultsValue;
	}

	public String getExamResultsDisabled() {
		return examResultsDisabled;
	}

	public void setExamResultsDisabled(String examResultsDisabled) {
		this.examResultsDisabled = examResultsDisabled;
	}

	public String saveSubject() throws Exception{
		//代课老师操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//通过subjectId获取subjectName
		Subject subject = subjectService.get(Subject.class, subjectId);
		
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(attendanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900036' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(attendanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900036");
				score.setGrade(attendanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(performanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900037' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName= '" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(performanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900037");
				score.setGrade(performanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(taskValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900038' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(taskValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900038");
				score.setGrade(taskValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(winningValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900039' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'";
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(winningValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900039");
				score.setGrade(winningValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(examResultsValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900040' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(examResultsValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900040");
				score.setGrade(examResultsValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(musicAttendanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900043' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(musicAttendanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900043");
				score.setGrade(musicAttendanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(musicPerformanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900044' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(musicPerformanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900044");
				score.setGrade(musicPerformanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(instrumentsValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900045' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(instrumentsValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900045");
				score.setGrade(instrumentsValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(musicScoreValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900046' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(musicScoreValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900046");
				score.setGrade(musicScoreValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(artAttendanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900047' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(artAttendanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900047");
				score.setGrade(artAttendanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(artPerformanceValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900048' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(artPerformanceValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900048");
				score.setGrade(artPerformanceValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(paintingValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900049' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(paintingValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900049");
				score.setGrade(paintingValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(manualValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900050' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(manualValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900050");
				score.setGrade(manualValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		return "toClassSubject";
	}
	
	public String saveChinese() throws Exception{
		//代课老师操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = '" + number + "'";
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null &&teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		Integer teacherId = null;
		if(teacher != null){
			teacherId = teacher.getId();
		}
		//通过subjectId获取subjectName
		Subject subject = subjectService.get(Subject.class, subjectId);
		
		//获取当前时间
		long currentTimeMillis = System.currentTimeMillis();
		Date time = new Date(currentTimeMillis);
		//判断是否有评分，有则保存
		if(outsideReadingValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900041' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(outsideReadingValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900041");
				score.setGrade(outsideReadingValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		if(opinionValue != null){
			String hql1 = "from Score where estimateId = '3028f5816137c664016137c8f4900042' "
					+ "and term = " + term+" and studentId = "+studentId +" and sorbName='" + subject.getSubjectName()+"'" ;
			List<Score> scoreList = scoreService.find(hql1, Score.class, null);
			if(scoreList != null && scoreList.size() != 0){
				Score score = scoreList.get(0);
				score.setGrade(opinionValue);
				
				scoreService.saveOrUpdate(score);
			}else{
				Score score = new Score();
				score.setEstimateId("3028f5816137c664016137c8f4900042");
				score.setGrade(opinionValue);
				score.setSorbName(subject.getSubjectName());
				score.setStudentId(studentId);
				score.setTeacherId(teacherId);
				score.setTerm(term);
				score.setTime(time);
				
				scoreService.saveOrUpdate(score);
			}
		}
		return "toClassSubject";
	}
	
	
	public String toStudentView() throws Exception{
		Student student = studentService.get(Student.class, studentId);
		ActionContext.getContext().getValueStack().push(student);
		ActionContext.getContext().put("term", term);
		
		String hql = "from Score where studentId=" + studentId + " and term = " + term;
		List<Score> scoreList = scoreService.find(hql, Score.class, null);
		
		List list = new ArrayList();
		for (Score score : scoreList) {
			System.err.println("id="+score.getEstimateId());
			Map map = new HashMap();
			Estimate estimate = estimateService.get(Estimate.class, score.getEstimateId());
			if(!"班主任".equals(score.getSorbName())&&!"美术".equals(score.getSorbName())&&!"音乐".equals(score.getSorbName())){
				map.put("estimateElement", estimate.getElement() +":"+score.getSorbName());
			}else{
				map.put("estimateElement", estimate.getElement());
			}
			map.put("estimateDimension", estimate.getDimension());
			map.put("estimateName", estimate.getName());
			map.put("maxValue", estimate.getMaxValue());
			
			Teacher teacher = teacherService.get(Teacher.class, score.getTeacherId());
			map.put("teacherName", teacher.getName());
			
			map.put("score", score.getGrade());
			map.put("time", score.getTime());
			
			list.add(map);
		}
		ActionContext.getContext().put("list", list);
		
		return "toStudentView";
	}
	
	public String viewSelf() throws Exception{
		//学生操作的,获取teacherId
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Student where number = " + number;
		List<Student> studentList = studentService.find(hql, Student.class, null);
		
		Student student = null;
		if(studentList != null && studentList.size() != 0){
			student = studentList.get(0);
		}
		
		ActionContext.getContext().getValueStack().push(student);
		return "viewSelf";
	}
	
	public String getScore() throws Exception{
		//获取学生这个学期的各科分数并累加
		String hql = "from Score where studentId = " + studentId + " and term = " + term;
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
		
		Map map = new HashMap();
		map.put("sum", sum);
		
		if(sum != 0 && sum < 200){
			map.put("msg", "下学期要多多努力哦~");
		}else if(sum >=200){
			map.put("msg", "表现不错，继续加油！！");
		}else{
			map.put("msg", "未出分，请后期再来查看。。");
		}
		
		String jsonString = JSON.toJSONString(map);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");//json串的mime类型：application/json
		response.setHeader("Cache-Control", "no-cache");//设置响应结束时，不使用缓存
		response.getWriter().write(jsonString);
		
		return null;
	}
	
}
