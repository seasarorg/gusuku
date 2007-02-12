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

import org.seasar.gusuku.dto.TypeAdminDto;
import org.seasar.gusuku.entity.Type;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.logic.TypeAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

public class TypeAdminAction extends GusukuAction implements ModelDriven{

	private static final long serialVersionUID = -7705493642367899982L;
	private TypeAdminDto dto = new TypeAdminDto();
	private TypeAdminLogic typeAdminLogic;
	
	private TypeHelper typeHelper;
	
	private List<Type> list;
	
	private Long[] delid;

	@XWorkAction(name = "type_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/type_add.html")))
	public String init(){
		if(dto.getId() != null){
			dto = typeAdminLogic.getTypeAdminDto(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/type_list.html")))
	public String list(){
		list = typeHelper.getTypeList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/type_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			typeAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_list.html")))
	public String deldone(){
		typeAdminLogic.delete(delid);
		return SUCCESS;
	}

	public void setDelid(Long[] delid) {
		this.delid = delid;
	}
	
	public Object getModel(){
		return dto;
	}

	public void setTypeAdminLogic(TypeAdminLogic typeAdminLogic) {
		this.typeAdminLogic = typeAdminLogic;
	}

	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	
	public List<Type> getList() {
		return list;
	}
}
