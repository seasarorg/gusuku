package org.seasar.gusuku.dto;

import java.io.Serializable;


public class ProjectReportDto extends GusukuPagerCondition implements
		Serializable {

	private Long projectid;
	private Long typeid;
	private Long statusid;
	private Long workflowid;
	
	public Long getProjectid() {
		return projectid;
	}
	
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	public Long getStatusid() {
		return statusid;
	}
	
	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}
	
	public Long getTypeid() {
		return typeid;
	}
	
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	
	public Long getWorkflowid() {
		return workflowid;
	}

	
	public void setWorkflowid(Long workflowid) {
		this.workflowid = workflowid;
	}
	
}
