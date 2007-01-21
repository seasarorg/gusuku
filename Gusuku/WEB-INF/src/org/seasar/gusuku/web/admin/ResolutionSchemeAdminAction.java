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

import org.seasar.gusuku.dto.ResolutionSchemeAdminDto;
import org.seasar.gusuku.helper.ResolutionHelper;
import org.seasar.gusuku.logic.ResolutionSchemeAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;


public class ResolutionSchemeAdminAction extends GusukuAction implements ModelDriven{
	
	private static final long serialVersionUID = 4528190623881231712L;
	private ResolutionSchemeAdminDto dto = new ResolutionSchemeAdminDto();
	private ResolutionSchemeAdminLogic resolutionSchemeAdminLogic;
	private ResolutionHelper resolutionHelper;
	
	private String[] delid;
	
	@XWorkAction(name = "resolution_scheme_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/resolution_scheme_list.html")))
	public String list(){
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_scheme_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_scheme_list.html?headid=${model.headid}")))
	public String done(){
		resolutionSchemeAdminLogic.registration(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_scheme_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_scheme_list.html?headid=${model.headid}")))
	public String deldone(){
		resolutionSchemeAdminLogic.delete(delid);
		return SUCCESS;
	}

	@XWorkAction(name = "resolution_scheme_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_scheme_list.html?headid=${model.headid}")))
	public String up(){
		resolutionSchemeAdminLogic.sortUp(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "resolution_scheme_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/resolution_scheme_list.html?headid=${model.headid}")))
	public String down(){
		resolutionSchemeAdminLogic.sortDown(dto);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	
	public ResolutionHelper getResolutionHelper() {
		return resolutionHelper;
	}

	
	public void setResolutionHelper(ResolutionHelper resolutionHelper) {
		this.resolutionHelper = resolutionHelper;
	}

	
	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	
	public void setResolutionSchemeAdminLogic(ResolutionSchemeAdminLogic resolutionSchemeAdminLogic) {
		this.resolutionSchemeAdminLogic = resolutionSchemeAdminLogic;
	}
}
