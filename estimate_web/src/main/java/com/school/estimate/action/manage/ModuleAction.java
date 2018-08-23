package com.school.estimate.action.manage;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Module;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.User;
import com.school.estimate.service.ModuleService;
import com.school.estimate.service.UserService;

public class ModuleAction extends BaseAction implements ModelDriven<Module>{

	private static final long serialVersionUID = 1L;
	
	private ModuleService moduleService;
	
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	private Module model = new Module();
	
	public Module getModel() {
		return model;
	}
	
	private List<Module> moduleList = new ArrayList<>();

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public String toList() throws Exception{
		String hql = "from Module";
		moduleList = moduleService.find(hql, Module.class, null);
		
//		ActionContext.getContext().getValueStack().push(moduleList);
		return "list";
	}
	
	/*public String toCreate() throws Exception{
		return "toCreate";
	}
	
	public String create() throws Exception{
		
		
		return "tolist";
	}
	
	public String toUpdate() throws Exception{
		return "toUpdate";
	}
	
	public String update() throws Exception{
		return "tolist";
	}
	
	public String delete() throws Exception{
		moduleService.deleteById(Module.class, model.getId());
		return "tolist";
	}*/

	
}
