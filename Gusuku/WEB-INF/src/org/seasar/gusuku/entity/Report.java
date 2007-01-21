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
package org.seasar.gusuku.entity;

import java.io.Serializable;
import java.util.Date;

import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;
import org.seasar.dao.annotation.tiger.Relation;

public class Report implements Serializable {

	private static final long serialVersionUID = 329744497123036297L;
	private long id;
	private long projectid;
	private long typeid;
	private String title;
	private String key;
	private String messageid;
	private long statusid;
	private long resolutionid;
	private long priorityid;
	private long reporterid;
	private long assigneeid;
	private long componentid;
	private long versionid;
	private String environment;
	private String detail;
	private boolean delflag;
	private Date rdate;
	private Date udate;
	
	private Status status;
	
	private Priority priority;
	
	private Resolution resolution;
	
	private Account assignee;
	
	private Account reporter;
	
	private Type type;
	
	private Project project;
	
	public long getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(long assigneeid) {
		this.assigneeid = assigneeid;
	}
	public boolean isDelflag() {
		return delflag;
	}
	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
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
	
	@Id(IdType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(long priorityid) {
		this.priorityid = priorityid;
	}
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public long getReporterid() {
		return reporterid;
	}
	public void setReporterid(long reporterid) {
		this.reporterid = reporterid;
	}
	public long getResolutionid() {
		return resolutionid;
	}
	public void setResolutionid(long resolutionid) {
		this.resolutionid = resolutionid;
	}
	public long getStatusid() {
		return statusid;
	}
	public void setStatusid(long statusid) {
		this.statusid = statusid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTypeid() {
		return typeid;
	}
	public void setTypeid(long typeid) {
		this.typeid = typeid;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
	@Relation(relationNo=3,relationKey="ASSIGNEEID:ID")
	public Account getAssignee() {
		return assignee;
	}
	public void setAssignee(Account assignee) {
		this.assignee = assignee;
	}
	
	@Relation(relationNo=1,relationKey="PRIORITYID:ID")
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	@Relation(relationNo=4,relationKey="REPORTERID:ID")
	public Account getReporter() {
		return reporter;
	}
	public void setReporter(Account reporter) {
		this.reporter = reporter;
	}
	
	@Relation(relationNo=2,relationKey="RESOLUTIONID:ID")
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
	@Relation(relationNo=0,relationKey="STATUSID:ID")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Relation(relationNo=5,relationKey="TYPEID:ID")
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Relation(relationNo=6,relationKey="PROJECTID:ID")
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	
	public long getComponentid() {
		return componentid;
	}
	
	public void setComponentid(long componentid) {
		this.componentid = componentid;
	}
	
	public long getVersionid() {
		return versionid;
	}
	
	public void setVersionid(long versionid) {
		this.versionid = versionid;
	}
	
	
}
