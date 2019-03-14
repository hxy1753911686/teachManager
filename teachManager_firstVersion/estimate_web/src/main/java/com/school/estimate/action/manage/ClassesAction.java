package com.school.estimate.action.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Classes;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import com.school.estimate.domain.Subject;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.TeacherClassManage;
import com.school.estimate.domain.User;
import com.school.estimate.service.ClassesService;
import com.school.estimate.service.RoleService;
import com.school.estimate.service.SubjectService;
import com.school.estimate.service.TeacherClassManageService;
import com.school.estimate.service.TeacherService;
import com.school.estimate.service.UserService;

public class ClassesAction extends BaseAction implements ModelDriven<Classes>{

	private SubjectService subjectService;
	
	
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	private TeacherClassManageService teacherClassManageService;
	
	public void setTeacherClassManageService(TeacherClassManageService teacherClassManageService) {
		this.teacherClassManageService = teacherClassManageService;
	}

	private RoleService roleService;
	
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private ClassesService classesService;

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	private Classes model = new Classes();
	
	@Override
	public Classes getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	private List classesList = new ArrayList<>();
	
	public List getClassesList() {
		return classesList;
	}

	public void setClassesList(List classesList) {
		this.classesList = classesList;
	}

	public String toList() throws Exception{
		String hql = "from Classes where isUse = 1";
		List<Classes> allClasses = classesService.find(hql, Classes.class, null);
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
	
	private List banList = new ArrayList();
	
	
	public List getBanList() {
		return banList;
	}

	public void setBanList(List banList) {
		this.banList = banList;
		
	}

	public String toCreate() throws Exception{
		//查找是班主任的教师
		//查找所有的user,判断user中role是班主任的
		
		//获取到所有用户
		String hql = "from User";
		List<User> userList = userService.find(hql, User.class, null);
		
		
		//遍历用户,获取用户的所有角色
		for (User user : userList) {
//			System.out.println(user);
			
			Set<Role> roles = user.getRoles();
			List roleNames = new ArrayList();
			
			
			for (Role role : roles) {
				roleNames.add(role.getName());
//				System.out.println(roleNames);
			}
			if(roleNames.contains("班主任")){
				Map banMap = new HashMap<>();
				//通过user查找对应的教师
				String hql1 = "from Teacher where number = '" + user.getNumber() +"'";
				List<Teacher> teacherList = teacherService.find(hql1, Teacher.class, null);
				Teacher teacher = teacherList.get(0);
				banMap.put("id", teacher.getId());
				banMap.put("name",teacher.getName() );
				
				banList.add(banMap);
				
//				for (Object object : banList) {
//					System.err.println(object.toString());
//				}
			}
		}
		
		
		return "toCreate";
	}
	
	public String create() throws Exception{
		classesService.saveOrUpdate(model);
		
		if(model.getBanId() != null && model.getName() != null){
			Integer banId = model.getBanId();
			String name = model.getName();
			
			String hql = "from Classes where name = '" + name +"' and banId = '" + banId +"'";
			List<Classes> classesList = classesService.find(hql, Classes.class, null);
			Classes classes = null;
			if(classesList != null && classesList.size() != 0){
				classes = classesList.get(0);
			}
			
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0026");
			teacherClassManage.setTeacherId(banId);
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		return "toList";
	}
	
	/*private String classId;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}*/

	public String toUpdate() throws Exception{
		Classes classes = classesService.get(Classes.class, model.getId());
		ActionContext.getContext().getValueStack().push(classes);
		
		//获取到所有用户
		String hql = "from User";
		List<User> userList = userService.find(hql, User.class, null);
		
		
		//遍历用户,获取用户的所有角色
		for (User user : userList) {
//					System.out.println(user);
			
			Set<Role> roles = user.getRoles();
			List roleNames = new ArrayList();
			
			
			for (Role role : roles) {
				roleNames.add(role.getName());
//						System.out.println(roleNames);
			}
			if(roleNames.contains("班主任")){
				Map banMap = new HashMap<>();
				//通过user查找对应的教师
				String hql1 = "from Teacher where number = '" + user.getNumber() +"'";
				List<Teacher> teacherList = teacherService.find(hql1, Teacher.class, null);
				Teacher teacher = teacherList.get(0);
				banMap.put("id", teacher.getId());
				banMap.put("name",teacher.getName() );
				
				banList.add(banMap);
				
			}
		}
		
		return "toUpdate";
	}
	
	public String update() throws Exception{
		//通过model的id找到session中管理的entity
		Classes classes = classesService.get(Classes.class, model.getId());
		
		//修改班级时同步修改安全法制老师
		if(classes.getBanId() != null && classes.getName() != null){
			
			//从teacherClassManage中找存在的subject对应关系
			String hql2 = "from TeacherClassManage where classId = '" + classes.getId() 
			+ "' and teacherId = " + classes.getBanId() + " and subjectId = '402881ea6185659401618566GGFF0026'";
			List<TeacherClassManage> teacherClassManageList = teacherClassManageService.find(hql2, TeacherClassManage.class, null);
			TeacherClassManage teacherClassManage = null;
			if(teacherClassManageList != null && teacherClassManageList.size() != 0){
				teacherClassManage = teacherClassManageList.get(0);
			}
			if(teacherClassManage != null){
				teacherClassManage.setTeacherId(model.getBanId());
				teacherClassManageService.saveOrUpdate(teacherClassManage);
			}
		}
		
		classes.setBanId(model.getBanId());
		classes.setIsUse(model.getIsUse());
		classes.setName(model.getName());
		classes.setStartTime(model.getStartTime());

		classesService.saveOrUpdate(classes);
		
		
		return "toList";
	}
	
	public String toView() throws Exception{
		//通过id查看要查看的班级信息
		Classes classes = classesService.get(Classes.class, model.getId());
		ActionContext.getContext().getValueStack().push(classes);
		
		List teacherList = new ArrayList();
		String teacherSql = "from TeacherClassManage where classId = '" + model.getId() +"'";
		List<TeacherClassManage> teacherClassManageList = teacherClassManageService.find(teacherSql, TeacherClassManage.class, null);
		for (TeacherClassManage teacherClassManage : teacherClassManageList) {
			Map teacherSet = new HashMap();
			Teacher teacher = teacherService.get(Teacher.class, teacherClassManage.getTeacherId());
			teacherSet.put("name", teacher.getName());
			teacherSet.put("gender", teacher.getGender());
			
			Subject subject = subjectService.get(Subject.class, teacherClassManage.getSubjectId());
			teacherSet.put("subject", subject.getSubjectName());
			
			teacherList.add(teacherSet);
		}
		
		
		
		ActionContext.getContext().put("teacherSet", teacherList);
		
		Set<Student> students = classes.getStudents();
		ActionContext.getContext().put("studentSet", students);
		return "toView";
	}
	
	public String toOld() throws Exception{
		String hql = "from Classes where isUse = 0";
		List<Classes> allClasses = classesService.find(hql, Classes.class, null);
		for (Classes classes : allClasses) {
			Map classMap = new HashMap();
			classMap.put("id", classes.getId());
			classMap.put("name", classes.getName());
			if(classes.getBanId() != null && classes.getBanId() != 0){
				Teacher teacher = teacherService.get(Teacher.class, classes.getBanId());
				classMap.put("ban", teacher.getName());
			}else{
				classMap.put("ban", null);
			}
			
			
			classesList.add(classMap);
		}
		
		return "old";
	}
	
	private List teacherList = new ArrayList();
	
	public List getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List teacherList) {
		this.teacherList = teacherList;
	}

	public String toTeacher() throws Exception{
		Classes classes = classesService.get(Classes.class , model.getId());
		ActionContext.getContext().getValueStack().push(classes);
		
		//获取到所有的教师,并获取班级的每个教师
		String hql = "from Teacher";
		teacherList = teacherService.find(hql, Teacher.class, null);
		
		String hql1 = "from TeacherClassManage where classId = '" + model.getId() +"'";
		List<TeacherClassManage> teacherClassManageList = teacherClassManageService.find(hql1, TeacherClassManage.class, null);
		
		Map manageMap = new HashMap();
		
		for (TeacherClassManage teacherClassManage : teacherClassManageList) {
			System.err.println(teacherClassManage);
			
			
			String subjectId = teacherClassManage.getSubjectId();
			Integer teacherId = teacherClassManage.getTeacherId();
			Teacher teacher = teacherService.get(Teacher.class, teacherId);
			
			Subject subject = subjectService.get(Subject.class, subjectId);
			if("语文".equals(subject.getSubjectName()) ){
				manageMap.put("chineseTeacher", teacher.getId());
			}else if("数学".equals(subject.getSubjectName())){
				manageMap.put("mathTeacher", teacher.getId());
			}else if("英语".equals(subject.getSubjectName())){
				manageMap.put("englishTeacher", teacher.getId());
			}else if("政治".equals(subject.getSubjectName())){
				manageMap.put("politicsTeacher", teacher.getId());
			}else if("历史".equals(subject.getSubjectName())){
				manageMap.put("historyTeacher", teacher.getId());
			}else if("物理".equals(subject.getSubjectName())){
				manageMap.put("physicsTeacher", teacher.getId());
			}else if("化学".equals(subject.getSubjectName())){
				manageMap.put("chemistryTeacher", teacher.getId());
			}else if("地理".equals(subject.getSubjectName())){
				manageMap.put("geographyTeacher", teacher.getId());
			}else if("生物".equals(subject.getSubjectName())){
				manageMap.put("biologyTeacher", teacher.getId());
			}else if("美术".equals(subject.getSubjectName())){
				manageMap.put("artTeacher", teacher.getId());
			}else if("音乐".equals(subject.getSubjectName())){
				manageMap.put("musicTeacher", teacher.getId());
			}else if("信息技术".equals(subject.getSubjectName())){
				manageMap.put("ITTeacher", teacher.getId());
			}else if("体育".equals(subject.getSubjectName())){
				manageMap.put("sportTeacher", teacher.getId());
			}else if("心理健康".equals(subject.getSubjectName())){
				manageMap.put("healthTeacher", teacher.getId());
			}else if("书法".equals(subject.getSubjectName())){
				manageMap.put("writeTeacher", teacher.getId());
			}else if("安全法制".equals(subject.getSubjectName())){
				manageMap.put("safeTeacher", teacher.getId());
			}else{
				manageMap.put("activityTeacher", teacher.getId());
			}
			
		}
		
		ActionContext.getContext().put("manageMap", manageMap);
		return "toTeacher";
	}
	
	private Integer chineseId;
	private Integer mathId;
	private Integer englishId;
	private Integer politicsId;
	private Integer historyId;
	private Integer physicsId;
	private Integer chemistryId;
	private Integer geographyId;
	private Integer biologyTeacherId;
	private Integer artId;
	private Integer musicId;
	private Integer ITId;
	private Integer sportId;
	private Integer healthId;
	private Integer writeId;
	private Integer safeId;
	private Integer activityId;
	
	

	public Integer getChineseId() {
		return chineseId;
	}

	public void setChineseId(Integer chineseId) {
		this.chineseId = chineseId;
	}

	public Integer getMathId() {
		return mathId;
	}

	public void setMathId(Integer mathId) {
		this.mathId = mathId;
	}

	public Integer getEnglishId() {
		return englishId;
	}

	public void setEnglishId(Integer englishId) {
		this.englishId = englishId;
	}

	public Integer getPoliticsId() {
		return politicsId;
	}

	public void setPoliticsId(Integer politicsId) {
		this.politicsId = politicsId;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public Integer getPhysicsId() {
		return physicsId;
	}

	public void setPhysicsId(Integer physicsId) {
		this.physicsId = physicsId;
	}

	public Integer getChemistryId() {
		return chemistryId;
	}

	public void setChemistryId(Integer chemistryId) {
		this.chemistryId = chemistryId;
	}

	public Integer getGeographyId() {
		return geographyId;
	}

	public void setGeographyId(Integer geographyId) {
		this.geographyId = geographyId;
	}

	public Integer getBiologyTeacherId() {
		return biologyTeacherId;
	}

	public void setBiologyTeacherId(Integer biologyTeacherId) {
		this.biologyTeacherId = biologyTeacherId;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public Integer getMusicId() {
		return musicId;
	}

	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}

	public Integer getITId() {
		return ITId;
	}

	public void setITId(Integer iTId) {
		ITId = iTId;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public Integer getHealthId() {
		return healthId;
	}

	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}

	public Integer getWriteId() {
		return writeId;
	}

	public void setWriteId(Integer writeId) {
		this.writeId = writeId;
	}

	public Integer getSafeId() {
		return safeId;
	}

	public void setSafeId(Integer safeId) {
		this.safeId = safeId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String saveTeacher() throws Exception{
		
		Classes classes = classesService.get(Classes.class, model.getId());
		
		
		//判断哪些id存在,则存相应的数据
		String hql = "from TeacherClassManage where classId = '" + model.getId() +"'";
		List<TeacherClassManage> teacherClassManageList = teacherClassManageService.find(hql, TeacherClassManage.class, null);
		for (TeacherClassManage teacherClassManage : teacherClassManageList) {
			String subjectId = teacherClassManage.getSubjectId();
			Subject subject = subjectService.get(Subject.class, subjectId);
			//判断科目为哪个科目
			if("语文".equals(subject.getSubjectName())){
				//如果是语文,则teacherClassManage的teacherId为chineseId
				if(chineseId.intValue() != -1){
					teacherClassManage.setTeacherId(chineseId);
					chineseId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("数学".equals(subject.getSubjectName())){
				if(mathId.intValue() != -1){
					teacherClassManage.setTeacherId(mathId);
					mathId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("英语".equals(subject.getSubjectName())){
				if(englishId.intValue() != -1){
					teacherClassManage.setTeacherId(englishId);
					englishId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("政治".equals(subject.getSubjectName())){
				if(politicsId.intValue() != -1){
					teacherClassManage.setTeacherId(politicsId);
					politicsId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("历史".equals(subject.getSubjectName())){
				if(historyId.intValue() != -1){
					teacherClassManage.setTeacherId(historyId);
					historyId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("物理".equals(subject.getSubjectName())){
				System.err.println("物理："+( physicsId.intValue() != -1));
				if(physicsId.intValue() != -1){
					teacherClassManage.setTeacherId(physicsId);
					physicsId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("化学".equals(subject.getSubjectName())){
				if(chemistryId.intValue() != -1){
					teacherClassManage.setTeacherId(chemistryId);
					chemistryId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("地理".equals(subject.getSubjectName())){
				if(geographyId.intValue() != -1){
					teacherClassManage.setTeacherId(geographyId);
					geographyId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("生物".equals(subject.getSubjectName())){
				if(biologyTeacherId.intValue() != -1){
					teacherClassManage.setTeacherId(biologyTeacherId);
					biologyTeacherId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("美术".equals(subject.getSubjectName())){
				if(artId.intValue() != -1){
					teacherClassManage.setTeacherId(artId);
					artId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("音乐".equals(subject.getSubjectName())){
				if(musicId.intValue() != -1){
					teacherClassManage.setTeacherId(musicId);
					musicId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("信息技术".equals(subject.getSubjectName())){
				if(ITId.intValue() != -1){
					teacherClassManage.setTeacherId(ITId);
					ITId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("体育".equals(subject.getSubjectName())){
				if(sportId.intValue() != -1){
					teacherClassManage.setTeacherId(sportId);
					sportId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("心理健康".equals(subject.getSubjectName())){
				if(healthId.intValue() != -1){
					teacherClassManage.setTeacherId(healthId);
					healthId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("书法".equals(subject.getSubjectName())){
				if(writeId.intValue() != -1){
					teacherClassManage.setTeacherId(writeId);
					writeId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}else if("活动".equals(subject.getSubjectName())){
				if(activityId.intValue() != -1){
					teacherClassManage.setTeacherId(activityId);
					activityId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}
			else if("安全法制".equals(subject.getSubjectName())){
				if(safeId.intValue() != -1){
					teacherClassManage.setTeacherId(safeId);
					safeId = -1;
					teacherClassManageService.saveOrUpdate(teacherClassManage);
				}else{
					teacherClassManageService.deleteById(TeacherClassManage.class, teacherClassManage.getId());
				}
			}
			
			
			
		}
		
		//将数据库中的update后,保存不存在的
		//先判断是否为id是否为null
		if(chineseId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0011");
			teacherClassManage.setTeacherId(chineseId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		
		if(mathId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0012");
			teacherClassManage.setTeacherId(mathId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(englishId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0013");
			teacherClassManage.setTeacherId(englishId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(politicsId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0014");
			teacherClassManage.setTeacherId(politicsId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		
		if(historyId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0015");
			teacherClassManage.setTeacherId(historyId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(physicsId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0016");
			teacherClassManage.setTeacherId(physicsId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(chemistryId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0017");
			teacherClassManage.setTeacherId(chemistryId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(geographyId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0018");
			teacherClassManage.setTeacherId(geographyId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}

		
		if(biologyTeacherId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0019");
			teacherClassManage.setTeacherId(biologyTeacherId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(artId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0020");
			teacherClassManage.setTeacherId(artId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(musicId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0021");
			teacherClassManage.setTeacherId(musicId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(ITId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0022");
			teacherClassManage.setTeacherId(ITId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(sportId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0023");
			teacherClassManage.setTeacherId(sportId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(healthId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0024");
			teacherClassManage.setTeacherId(healthId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		if(writeId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0025");
			teacherClassManage.setTeacherId(writeId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		/*if(safeId != null){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0026");
			teacherClassManage.setTeacherId(safeId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}*/
		if(activityId.intValue() != -1){
			TeacherClassManage teacherClassManage = new TeacherClassManage();
			teacherClassManage.setClassId(classes.getId());
			teacherClassManage.setSubjectId("402881ea6185659401618566GGFF0027");
			teacherClassManage.setTeacherId(activityId);
			
			teacherClassManageService.saveOrUpdate(teacherClassManage);
		}
		
		
		
		return "returnView";
	}

}
