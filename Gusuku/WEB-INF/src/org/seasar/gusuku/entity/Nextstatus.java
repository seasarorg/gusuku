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

@Bean(table="NEXTSTATUS")
public class Nextstatus implements Serializable {

	private static final long serialVersionUID = 8107437760136364303L;
	private Long id;
	private Long workflowstatusid;
	private Long nextstatusid;

	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="NEXTSTATUS_ID_SEQ")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNextstatusid() {
		return nextstatusid;
	}

	public void setNextstatusid(Long nextstatusid) {
		this.nextstatusid = nextstatusid;
	}

	public Long getWorkflowstatusid() {
		return workflowstatusid;
	}

	public void setWorkflowstatusid(Long workflowstatusid) {
		this.workflowstatusid = workflowstatusid;
	}

}
