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

import org.seasar.gusuku.dto.VersionAdminDto;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.helper.VersionHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.VersionAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

/**
 * バージョン管理
 * @author duran
 *
 */
public class VersionAdminAction extends GusukuAction implements ModelDriven,Preparable,AdminAuthenticateAware {
	
	private static final long serialVersionUID = 95904290698645852L;
	private VersionAdminLogic versionAdminLogic;
	private VersionHelper versionHelper;
	private ProjectHelper projectHelper;
	private List list;
	private Long[] delid;
	private Project project;
	
	private VersionAdminDto dto= new VersionAdminDto();
	
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
	@XWorkAction(name = "version_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/version_list.html")))
	public String list(){
		list = versionHelper.getVersionList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "version_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/version_add.html")))
	public String init(){
		if(dto.getId() != null && dto.getProjectid() != null){
			dto = versionAdminLogic.getVersion(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "version_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/version_list.html?projectid=${model.projectid}")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/version_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			versionAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "version_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/version_list.html?projectid=${model.projectid}")))
	public String deldone(){
		versionAdminLogic.delete(delid);
		return SUCCESS;
	}

	
	public List getList() {
		return list;
	}

	
	public void setVersionAdminLogic(VersionAdminLogic versionAdminLogic) {
		this.versionAdminLogic = versionAdminLogic;
	}

	
	public void setVersionHelper(VersionHelper versionHelper) {
		this.versionHelper = versionHelper;
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
