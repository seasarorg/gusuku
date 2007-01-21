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
	private long id;
	private long workflowid;
	private long statusid;
	private boolean sflag;
	private boolean eflag;
	
	private Status status;

	public boolean isEflag() {
		return eflag;
	}

	public void setEflag(boolean eflag) {
		this.eflag = eflag;
	}

	@Id(IdType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSflag() {
		return sflag;
	}

	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}

	public long getStatusid() {
		return statusid;
	}

	public void setStatusid(long statusid) {
		this.statusid = statusid;
	}

	public long getWorkflowid() {
		return workflowid;
	}

	public void setWorkflowid(long workflowid) {
		this.workflowid = workflowid;
	}

	@Relation(relationNo=0,relationKey="STATUSID:ID")
	public Status getStatus() {
		return status;
	}

	
	public void setStatus(Status status) {
		this.status = status;
	}

}
