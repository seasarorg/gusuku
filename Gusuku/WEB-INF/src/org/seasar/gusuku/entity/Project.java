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

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;
import org.seasar.dao.annotation.tiger.Relation;

@Bean(table="PROJECT")
public class Project implements Serializable {
	
	private static final long serialVersionUID = -1902238916067714889L;
	private Long id;
	private String key;
	private Long seq;
	private String name;
	private Long leaderid;
	private Long workflowid;
	private Long typeid;
	private Long priorityid;
	private Long resolutionid;
	private Long formid;
	private Date sdate;
	private Date edate;
	private String url;
	private String devurl;
	private String description;
	private String mailaddr;
	private String subject;
	private boolean closeflag;
	private boolean delflag;
	private Date rdate;
	private Date udate;
	
	private Account leader;
	
	@Relation(relationNo=0,relationKey="LEADERID:ID")
	public Account getLeader() {
		return leader;
	}
	public void setLeader(Account leader) {
		this.leader = leader;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public boolean isDelflag() {
		return delflag;
	}
	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	//Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="PROJECT_ID_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public Long getLeaderid() {
		return leaderid;
	}
	public void setLeaderid(Long leaderid) {
		this.leaderid = leaderid;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getWorkflowid() {
		return workflowid;
	}
	
	public void setWorkflowid(Long workflowid) {
		this.workflowid = workflowid;
	}
	public Long getFormid() {
		return formid;
	}
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	public String getDevurl() {
		return devurl;
	}
	public void setDevurl(String devurl) {
		this.devurl = devurl;
	}
	public boolean isCloseflag() {
		return closeflag;
	}
	public void setCloseflag(boolean closeflag) {
		this.closeflag = closeflag;
	}
	public String getMailaddr() {
		return mailaddr;
	}
	public void setMailaddr(String mailaddr) {
		this.mailaddr = mailaddr;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Long getPriorityid() {
		return priorityid;
	}
	
	public void setPriorityid(Long priorityid) {
		this.priorityid = priorityid;
	}
	
	public Long getResolutionid() {
		return resolutionid;
	}
	
	public void setResolutionid(Long resolutionid) {
		this.resolutionid = resolutionid;
	}
	
	public Long getTypeid() {
		return typeid;
	}
	
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	
	
}
