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

import org.seasar.gusuku.dto.TypeSchemeAdminDto;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.logic.TypeSchemeAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;


public class TypeSchemeAdminAction extends GusukuAction implements ModelDriven{
	
	private static final long serialVersionUID = 7421966414760767061L;
	private TypeSchemeAdminDto dto = new TypeSchemeAdminDto();
	private TypeSchemeAdminLogic typeSchemeAdminLogic;
	private TypeHelper typeHelper;
	
	private String[] delid;
	
	@XWorkAction(name = "type_scheme_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/type_scheme_list.html")))
	public String list(){
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_scheme_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_scheme_list.html?headid=${model.headid}")))
	public String done(){
		typeSchemeAdminLogic.registration(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_scheme_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_scheme_list.html?headid=${model.headid}")))
	public String deldone(){
		typeSchemeAdminLogic.delete(delid);
		return SUCCESS;
	}

	@XWorkAction(name = "type_scheme_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_scheme_list.html?headid=${model.headid}")))
	public String up(){
		typeSchemeAdminLogic.sortUp(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_scheme_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_scheme_list.html?headid=${model.headid}")))
	public String down(){
		typeSchemeAdminLogic.sortDown(dto);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	
	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	
	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	
	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	
	public void setTypeSchemeAdminLogic(TypeSchemeAdminLogic typeSchemeAdminLogic) {
		this.typeSchemeAdminLogic = typeSchemeAdminLogic;
	}
}
