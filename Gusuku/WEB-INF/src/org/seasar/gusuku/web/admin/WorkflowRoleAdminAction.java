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

import org.seasar.gusuku.dto.WorkflowRoleAdminDto;
import org.seasar.gusuku.entity.Workflow;
import org.seasar.gusuku.entity.WorkflowStatus;
import org.seasar.gusuku.helper.WorkflowHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.WorkflowAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;

public class WorkflowRoleAdminAction extends GusukuAction implements ModelDriven,AdminAuthenticateAware{
	
	private static final long serialVersionUID = 6439227479001882917L;
	
	private WorkflowAdminLogic workflowAdminLogic;
	
	private WorkflowHelper workflowHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	
	private List intervalList;
	private WorkflowStatus start;
	private WorkflowStatus end;
	
	private Workflow workflow;
	
	private WorkflowRoleAdminDto dto = new WorkflowRoleAdminDto();
	
	public Object getModel(){
		return dto;
	}
	
	@XWorkAction(name = "flow_role_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/flow_role_edit.html")))
	public String init(){
		workflow = workflowHelper.getWorkflow(dto.getId());
		intervalList = workflowStatusHelper.getStatusListWithoutStartAndEnd(dto.getId());
		start = workflowStatusHelper.getStartStatus(dto.getId());
		end = workflowStatusHelper.getEndStatus(dto.getId());
		return SUCCESS;
	}
	
	@XWorkAction(name = "flow_role_edit_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/workflow_list.html")))
	public String update(){
		workflowAdminLogic.updateRole(dto);
		return SUCCESS;
	}
	
	public void setWorkflowHelper(WorkflowHelper workflowHelper) {
		this.workflowHelper = workflowHelper;
	}

	
	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}

	
	public void setWorkflowAdminLogic(WorkflowAdminLogic workflowAdminLogic) {
		this.workflowAdminLogic = workflowAdminLogic;
	}

	
	public WorkflowStatus getEnd() {
		return end;
	}

	
	public List getIntervalList() {
		return intervalList;
	}

	
	public WorkflowStatus getStart() {
		return start;
	}

	
	public Workflow getWorkflow() {
		return workflow;
	}

}
