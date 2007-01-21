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
	private String id;
	private String projectid;
	private String projectname;
	
	private String formid;
	private String typeid;
	private String typename;
	
	private String title;
	private String priorityid;
	private String assigneeid;
	private String reporterid;
	private String resolutionid;
	private String statusid;
	private String componentid;
	private String versionid;
	private String environment;
	private String detail;
	
	private String comment;
	private String nextstatusid;
	
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
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
	public String getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(String assigneeid) {
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
	public String getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(String priorityid) {
		this.priorityid = priorityid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReporterid() {
		return reporterid;
	}
	public void setReporterid(String reporterid) {
		this.reporterid = reporterid;
	}
	public String getResolutionid() {
		return resolutionid;
	}
	public void setResolutionid(String resolutionid) {
		this.resolutionid = resolutionid;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getNextstatusid() {
		return nextstatusid;
	}
	
	public void setNextstatusid(String nextstatusid) {
		this.nextstatusid = nextstatusid;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	
	public String getComponentid() {
		return componentid;
	}
	
	public void setComponentid(String componentid) {
		this.componentid = componentid;
	}
	
	public String getVersionid() {
		return versionid;
	}
	
	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}

}
