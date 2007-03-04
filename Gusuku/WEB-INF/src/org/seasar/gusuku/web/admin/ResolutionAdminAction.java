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

import org.seasar.gusuku.dto.ResolutionAdminDto;
import org.seasar.gusuku.entity.Resolution;
import org.seasar.gusuku.helper.ResolutionHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.ResolutionAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

public class ResolutionAdminAction extends GusukuAction implements ModelDriven,AdminAuthenticateAware{

	private static final long serialVersionUID = -6469970456867745572L;
	private ResolutionAdminDto dto = new ResolutionAdminDto();
	private ResolutionAdminLogic resolutionAdminLogic;
	
	private ResolutionHelper resolutionHelper;
	
	private List<Resolution> list;
	
	private Long[] delid;

	@XWorkAction(name = "resolution_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_add.html")))
	public String init(){
		if(dto.getId() != null){
			dto = resolutionAdminLogic.getResolutionAdminDto(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_list.html")))
	public String list(){
		list = resolutionHelper.getResolutionList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			resolutionAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_list.html")))
	public String deldone(){
		resolutionAdminLogic.delete(delid);
		return SUCCESS;
	}

	public void setDelid(Long[] delid) {
		this.delid = delid;
	}
	
	public Object getModel(){
		return dto;
	}

	public void setResolutionAdminLogic(ResolutionAdminLogic resolutionAdminLogic) {
		this.resolutionAdminLogic = resolutionAdminLogic;
	}

	public ResolutionHelper getResolutionHelper() {
		return resolutionHelper;
	}

	public void setResolutionHelper(ResolutionHelper resolutionHelper) {
		this.resolutionHelper = resolutionHelper;
	}

	
	public List<Resolution> getList() {
		return list;
	}
}
