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

@Bean(table="PRIORITY_SCHEME")
public class PriorityScheme implements Serializable {

	private static final long serialVersionUID = 5444931372313679177L;
	private Long id;
	private Long headid;
	private Long priorityid;
	private int sort;
	
	private Priority priority;
	
	public Long getHeadid() {
		return headid;
	}
	public void setHeadid(Long headid) {
		this.headid = headid;
	}
	@Id(IdType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Long getPriorityid() {
		return priorityid;
	}
	public void setPriorityid(Long priorityid) {
		this.priorityid = priorityid;
	}
	
	@Relation(relationNo=0,relationKey="PRIORITYID:ID")
	public Priority getPriority() {
		return priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	
	
}
