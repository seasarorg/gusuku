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
import java.util.List;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

@Bean(table="SEARCH_CONDITION_BASIC")
public class SearchConditionBasic implements Serializable {

	private static final long serialVersionUID = -8923740743339100239L;
	private long id;
	private long conditionheadid;
	private long projectid;
	private String title;
	private String typeid;
	private String statusid;
	private String priorityid;
	private String assigneeid;
	private String environment;
	private String detail;
	private Date datefrom;
	private Date dateto;
	
	private List custom;
		
	public String getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(String assigneeid) {
		this.assigneeid = assigneeid;
	}
	public long getConditionheadid() {
		return conditionheadid;
	}
	public void setConditionheadid(long conditionheadid) {
		this.conditionheadid = conditionheadid;
	}
	public Date getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}
	public Date getDateto() {
		return dateto;
	}
	public void setDateto(Date dateto) {
		this.dateto = dateto;
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
	public String getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(String priorityid) {
		this.priorityid = priorityid;
	}
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public List getCustom() {
		return custom;
	}
	public void setCustom(List custom) {
		this.custom = custom;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	
}
