package org.seasar.gusuku.web.admin;

import java.util.Map;

import org.seasar.gusuku.helper.WorkflowHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.interceptor.ParameterAware;


public class WorkflowRoleAdminAction extends GusukuAction implements
		ParameterAware {
	
	private static final long serialVersionUID = 6439227479001882917L;
	
	private WorkflowHelper workflowHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	

	private Map parameters;

	@XWorkAction(name = "flow_role_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/flow_role_edit.html")))
	public String init(){
		return SUCCESS;
	}
	
	@XWorkAction(name = "flow_role_edit_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/workflow_list.html")))
	public String update(){
		return SUCCESS;
	}
	
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	
	public Map getParameters() {
		return parameters;
	}

	
	public void setWorkflowHelper(WorkflowHelper workflowHelper) {
		this.workflowHelper = workflowHelper;
	}

	
	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}

	
	public WorkflowHelper getWorkflowHelper() {
		return workflowHelper;
	}

	
	public WorkflowStatusHelper getWorkflowStatusHelper() {
		return workflowStatusHelper;
	}

}
