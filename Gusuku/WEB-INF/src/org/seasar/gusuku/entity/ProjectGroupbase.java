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

@Bean(table="PROJECT_GROUPBASE")
public class ProjectGroupbase implements Serializable {

	private static final long serialVersionUID = 8345610059429531841L;

	private Long id;
	private Long projectid;
	private Long groupbaseid;
	
	public Long getGroupbaseid() {
		return groupbaseid;
	}
	public void setGroupbaseid(Long groupbaseid) {
		this.groupbaseid = groupbaseid;
	}
	
	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="PROJECT_GROUPBASE_ID_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	
}
