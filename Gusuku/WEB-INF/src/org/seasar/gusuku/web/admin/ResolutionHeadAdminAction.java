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

import org.seasar.gusuku.dto.ResolutionHeadAdminDto;
import org.seasar.gusuku.entity.ResolutionHead;
import org.seasar.gusuku.helper.ResolutionHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.ResolutionHeadAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

public class ResolutionHeadAdminAction extends GusukuAction implements ModelDriven,AdminAuthenticateAware{

	private static final long serialVersionUID = -5722601212799625542L;
	private ResolutionHeadAdminDto dto = new ResolutionHeadAdminDto();
	private ResolutionHeadAdminLogic resolutionAdminLogic;
	
	private ResolutionHelper resolutionHelper;
	
	private List<ResolutionHead> list;
	
	private Long[] delid;

	@XWorkAction(name = "resolution_head_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_head_add.html")))
	public String init(){
		if(dto.getId() != null){
			dto = resolutionAdminLogic.getResolutionHeadAdminDto(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_head_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_head_list.html")))
	public String list(){
		list = resolutionHelper.getResolutionHeadList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_head_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_head_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_head_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			resolutionAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_head_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_head_list.html")))
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

	public void setResolutionAdminLogic(ResolutionHeadAdminLogic resolutionAdminLogic) {
		this.resolutionAdminLogic = resolutionAdminLogic;
	}

	public ResolutionHelper getResolutionHelper() {
		return resolutionHelper;
	}

	public void setResolutionHelper(ResolutionHelper resolutionHelper) {
		this.resolutionHelper = resolutionHelper;
	}

	
	public List<ResolutionHead> getList() {
		return list;
	}
}
