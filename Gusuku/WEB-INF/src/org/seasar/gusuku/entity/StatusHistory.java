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

@Bean(table="STATUS_HISTORY")
public class StatusHistory implements Serializable {

	private static final long serialVersionUID = 2791111042703441943L;
	private Long id;
	private Long reportid;
	private Long statusid;
	private Long changerid;
	private Date rdate;
	
	private Report report;

	private Status status;
	
	private Account changer;
	
	public Long getChangerid() {
		return changerid;
	}
	
	public void setChangerid(Long changerid) {
		this.changerid = changerid;
	}
	
	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="STATUS_HISTORY_ID_SEQ")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getRdate() {
		return rdate;
	}
	
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	public Long getReportid() {
		return reportid;
	}
	
	public void setReportid(Long reportid) {
		this.reportid = reportid;
	}
	
	public Long getStatusid() {
		return statusid;
	}
	
	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	@Relation(relationNo=2,relationKey="CHANGERID:ID")
	public Account getChanger() {
		return changer;
	}

	
	public void setChanger(Account changer) {
		this.changer = changer;
	}

	@Relation(relationNo=0,relationKey="REPORTID:ID")
	public Report getReport() {
		return report;
	}

	
	public void setReport(Report report) {
		this.report = report;
	}

	@Relation(relationNo=1,relationKey="STATUSID:ID")
	public Status getStatus() {
		return status;
	}

	
	public void setStatus(Status status) {
		this.status = status;
	}

}
