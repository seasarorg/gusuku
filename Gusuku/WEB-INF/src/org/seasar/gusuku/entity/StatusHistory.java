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
	private long id;
	private long reportid;
	private long statusid;
	private long changerid;
	private Date rdate;
	
	private Report report;

	private Status status;
	
	private Account changer;
	
	public long getChangerid() {
		return changerid;
	}
	
	public void setChangerid(long changerid) {
		this.changerid = changerid;
	}
	
	@Id(IdType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getRdate() {
		return rdate;
	}
	
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	public long getReportid() {
		return reportid;
	}
	
	public void setReportid(long reportid) {
		this.reportid = reportid;
	}
	
	public long getStatusid() {
		return statusid;
	}
	
	public void setStatusid(long statusid) {
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
