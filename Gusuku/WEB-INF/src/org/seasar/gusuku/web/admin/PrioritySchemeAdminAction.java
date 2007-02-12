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

import org.seasar.gusuku.dto.PrioritySchemeAdminDto;
import org.seasar.gusuku.entity.Priority;
import org.seasar.gusuku.entity.PriorityHead;
import org.seasar.gusuku.entity.PriorityScheme;
import org.seasar.gusuku.helper.PriorityHelper;
import org.seasar.gusuku.logic.PrioritySchemeAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;


public class PrioritySchemeAdminAction extends GusukuAction implements ModelDriven{

	private static final long serialVersionUID = -457251731044303654L;
	private PrioritySchemeAdminDto dto = new PrioritySchemeAdminDto();
	private PrioritySchemeAdminLogic prioritySchemeAdminLogic;
	private PriorityHelper priorityHelper;
	
	private List<PriorityScheme> list;
	private List<Priority> entryList;
	
	private PriorityHead priorityHead;
	
	private Long[] delid;
	
	@XWorkAction(name = "priority_scheme_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/priority_scheme_list.html")))
	public String list(){
		list = priorityHelper.getPriorityListWithScheme(dto.getHeadid());
		entryList = priorityHelper.getPriorityListWithoutScheme(dto.getHeadid());
		priorityHead = priorityHelper.getPriorityHead(dto.getHeadid());
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_scheme_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_scheme_list.html?headid=${model.headid}")))
	public String done(){
		prioritySchemeAdminLogic.registration(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_scheme_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_scheme_list.html?headid=${model.headid}")))
	public String deldone(){
		prioritySchemeAdminLogic.delete(delid,dto.getHeadid());
		return SUCCESS;
	}

	@XWorkAction(name = "priority_scheme_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_scheme_list.html?headid=${model.headid}")))
	public String up(){
		prioritySchemeAdminLogic.sortUp(dto);
		return SUCCESS;
	}
	
	@XWorkAction(name = "priority_scheme_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/priority_scheme_list.html?headid=${model.headid}")))
	public String down(){
		prioritySchemeAdminLogic.sortDown(dto);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	
	public void setPriorityHelper(PriorityHelper priorityHelper) {
		this.priorityHelper = priorityHelper;
	}

	
	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	
	public void setPrioritySchemeAdminLogic(PrioritySchemeAdminLogic prioritySchemeAdminLogic) {
		this.prioritySchemeAdminLogic = prioritySchemeAdminLogic;
	}

	
	public List<PriorityScheme> getList() {
		return list;
	}

	
	public List<Priority> getEntryList() {
		return entryList;
	}

	
	public PriorityHead getPriorityHead() {
		return priorityHead;
	}
}
