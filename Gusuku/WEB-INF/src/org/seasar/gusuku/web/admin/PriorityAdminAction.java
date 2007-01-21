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

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dto.PriorityAdminDto;
import org.seasar.gusuku.entity.Priority;
import org.seasar.gusuku.helper.PriorityHelper;
import org.seasar.gusuku.logic.PriorityAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

public class PriorityAdminAction extends GusukuAction implements ModelDriven{
	
	private static final long serialVersionUID = -148895630206971613L;
	private PriorityAdminDto dto = new PriorityAdminDto();
	private PriorityAdminLogic priorityAdminLogic;
	
	private PriorityHelper priorityHelper;
	
	private List<Priority> list;
	
	private String[] delid;

	@XWorkAction(name = "priority_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/priority_add.html")))
	public String init(){
		if(!StringUtil.isEmpty(dto.getId())){
			dto = priorityAdminLogic.getPriorityAdminDto(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/priority_list.html")))
	public String list(){
		list = priorityHelper.getPriorityList(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/priority_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			priorityAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_list.html")))
	public String deldone(){
		priorityAdminLogic.delete(delid);
		return SUCCESS;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}
	
	public Object getModel(){
		return dto;
	}

	public void setPriorityAdminLogic(PriorityAdminLogic priorityAdminLogic) {
		this.priorityAdminLogic = priorityAdminLogic;
	}

	public PriorityHelper getPriorityHelper() {
		return priorityHelper;
	}

	public void setPriorityHelper(PriorityHelper priorityHelper) {
		this.priorityHelper = priorityHelper;
	}

	
	public List<Priority> getList() {
		return list;
	}
}
