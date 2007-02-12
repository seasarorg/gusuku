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

import org.seasar.gusuku.dto.TypeHeadAdminDto;
import org.seasar.gusuku.entity.TypeHead;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.logic.TypeHeadAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

public class TypeHeadAdminAction extends GusukuAction implements ModelDriven{

	private static final long serialVersionUID = -7801957126851026502L;
	private TypeHeadAdminDto dto = new TypeHeadAdminDto();
	private TypeHeadAdminLogic typeAdminLogic;
	
	private TypeHelper typeHelper;
	
	private List<TypeHead> list;
	
	private Long[] delid;

	@XWorkAction(name = "type_head_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/type_head_add.html")))
	public String init(){
		if(dto.getId() != null){
			dto = typeAdminLogic.getTypeHeadAdminDto(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_head_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/type_head_list.html")))
	public String list(){
		list = typeHelper.getTypeHeadList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_head_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_head_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/type_head_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			typeAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "type_head_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/type_head_list.html")))
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

	public void setTypeAdminLogic(TypeHeadAdminLogic typeAdminLogic) {
		this.typeAdminLogic = typeAdminLogic;
	}

	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	
	public List<TypeHead> getList() {
		return list;
	}
}
