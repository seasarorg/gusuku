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
package org.seasar.gusuku.dto;

import java.io.Serializable;

public class ReportDto implements Serializable {

	private static final long serialVersionUID = 6847280322093586073L;
	private Long id;
	private Long projectid;
	private String projectname;
	
	private Long formid;
	private Long typeid;
	private String typename;
	
	private String title;
	private Long priorityid;
	private Long assigneeid;
	private Long reporterid;
	private Long resolutionid;
	private Long statusid;
	private Long componentid;
	private Long versionid;
	private String environment;
	private String detail;
	
	private String comment;
	private Long nextstatusid;
	
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	public Long getTypeid() {
		return typeid;
	}
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Long getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(Long assigneeid) {
		this.assigneeid = assigneeid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Long getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(Long priorityid) {
		this.priorityid = priorityid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getReporterid() {
		return reporterid;
	}
	public void setReporterid(Long reporterid) {
		this.reporterid = reporterid;
	}
	public Long getResolutionid() {
		return resolutionid;
	}
	public void setResolutionid(Long resolutionid) {
		this.resolutionid = resolutionid;
	}
	public Long getStatusid() {
		return statusid;
	}
	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Long getNextstatusid() {
		return nextstatusid;
	}
	
	public void setNextstatusid(Long nextstatusid) {
		this.nextstatusid = nextstatusid;
	}
	public Long getFormid() {
		return formid;
	}
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	
	public Long getComponentid() {
		return componentid;
	}
	
	public void setComponentid(Long componentid) {
		this.componentid = componentid;
	}
	
	public Long getVersionid() {
		return versionid;
	}
	
	public void setVersionid(Long versionid) {
		this.versionid = versionid;
	}

}
