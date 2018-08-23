package com.school.estimate.action.manage;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Module;
import com.school.estimate.domain.Role;
import com.school.estimate.service.RoleService;

public class RoleAction extends BaseAction implements ModelDriven<Role>{

	private static final long serialVersionUID = 1L;
	
	private RoleService roleService;
	
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private Role role = new Role();
	
	public Role getModel() {
		return role;
	}
	
	private List<Role> roleList = new ArrayList<>();

	

	public String toList() throws Exception{
		String hql = "from Role";
		roleList = roleService.find(hql, Role.class, null);
		
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
