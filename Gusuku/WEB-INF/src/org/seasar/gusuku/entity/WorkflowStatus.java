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

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;
import org.seasar.dao.annotation.tiger.Relation;

@Bean(table="WORKFLOW_STATUS")
public class WorkflowStatus implements Serializable {
	
	private static final long serialVersionUID = 5377622489326421232L;
	private Long id;
	private Long workflowid;
	private Long statusid;
	private boolean sflag;
	private boolean eflag;
	private boolean reporter = true;
	private boolean assignee = true;
	private boolean leader = true;
	
	private Status status;

	public boolean isEflag() {
		return eflag;
	}

	public void setEflag(boolean eflag) {
		this.eflag = eflag;
	}

	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="WORKFLOW_STATUS_ID_SEQ")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSflag() {
		return sflag;
	}

	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}

	public Long getStatusid() {
		return statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	public Long getWorkflowid() {
		return workflowid;
	}

	public void setWorkflowid(Long workflowid) {
		this.workflowid = workflowid;
	}

	@Relation(relationNo=0,relationKey="STATUSID:ID")
	public Status getStatus() {
		return status;
	}

	
	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isAssignee() {
		return assignee;
	}

	public void setAssignee(boolean assignee) {
		this.assignee = assignee;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public boolean isReporter() {
		return reporter;
	}

	public void setReporter(boolean reporter) {
		this.reporter = reporter;
	}

}
