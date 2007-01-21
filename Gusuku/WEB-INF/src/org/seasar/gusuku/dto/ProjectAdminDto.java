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

public class ProjectAdminDto implements Serializable {
	
	private static final long serialVersionUID = 5825922484284305575L;
	private String id;
	private String name;
	private String key;
	private String workflowid;
	private String typeid;
	private String priorityid;
	private String resolutionid;
	private String formid;
	private String leaderid;
	private String url;
	private String devurl;
	private String mailaddr;
	private String subject;
	private String sdate;
	private String edate;
	private String description;
	private String closeflag;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public String getLikeKey() {
		return "%" + key + "%";
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public String getLeaderid() {
		return leaderid;
	}
	public void setLeaderid(String leaderid) {
		this.leaderid = leaderid;
	}
	public String getName() {
		return name;
	}
	public String getLikeName() {
		return "%" + name + "%";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getWorkflowid() {
		return workflowid;
	}
	
	public void setWorkflowid(String workflowid) {
		this.workflowid = workflowid;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getCloseflag() {
		return closeflag;
	}
	public void setCloseflag(String closeflag) {
		this.closeflag = closeflag;
	}
	public String getDevurl() {
		return devurl;
	}
	public void setDevurl(String devurl) {
		this.devurl = devurl;
	}
	
	public String getMailaddr() {
		return mailaddr;
	}
	
	public void setMailaddr(String mailaddr) {
		this.mailaddr = mailaddr;
	}
	
	public String getPriorityid() {
		return priorityid;
	}
	
	public void setPriorityid(String priorityid) {
		this.priorityid = priorityid;
	}
	
	public String getResolutionid() {
		return resolutionid;
	}
	
	public void setResolutionid(String resolutionid) {
		this.resolutionid = resolutionid;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getTypeid() {
		return typeid;
	}
	
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	

}
