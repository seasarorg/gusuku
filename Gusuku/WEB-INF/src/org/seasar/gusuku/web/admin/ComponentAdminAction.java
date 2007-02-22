/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.gusuku.web.admin;

import java.util.List;

import org.seasar.gusuku.dto.ComponentAdminDto;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.helper.ComponentHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.logic.ComponentAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

/**
 * コンポーネント管理
 * @author duran
 *
 */
public class ComponentAdminAction extends GusukuAction implements ModelDriven,Preparable {
	
	private static final long serialVersionUID = 95904290698645852L;
	private ComponentAdminLogic componentAdminLogic;
	private ComponentHelper componentHelper;
	private ProjectHelper projectHelper;
	private List list;
	private Long[] delid;
	private Project project;
	
	private ComponentAdminDto dto= new ComponentAdminDto();
	
	public Object getModel(){
		return dto;
	}

	public void prepare(){
	}
	
	public void prepareInit(){
		project = projectHelper.getProject(dto.getProjectid());
	}
	public void prepareDone(){
		project = projectHelper.getProject(dto.getProjectid());
	}
	public void prepareList(){
		project = projectHelper.getProject(dto.getProjectid());
	}
	@XWorkAction(name = "component_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/component_list.html")))
	public String list(){
		list = componentHelper.getComponentList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "component_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/component_add.html")))
	public String init(){
		if(dto.getId() != null && dto.getProjectid() != null){
			dto = componentAdminLogic.getComponent(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "component_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/component_list.html?projectid=${model.projectid}")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/component_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			componentAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "component_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/component_list.html?projectid=${model.projectid}")))
	public String deldone(){
		componentAdminLogic.delete(delid);
		return SUCCESS;
	}

	
	public List getList() {
		return list;
	}

	
	public void setComponentAdminLogic(ComponentAdminLogic componentAdminLogic) {
		this.componentAdminLogic = componentAdminLogic;
	}

	
	public void setComponentHelper(ComponentHelper componentHelper) {
		this.componentHelper = componentHelper;
	}

	
	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	
	public Project getProject() {
		return project;
	}

	
	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}

}
