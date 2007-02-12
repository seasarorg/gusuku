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


public class WorkflowAdminDto implements Serializable {

	private static final long serialVersionUID = 4638881964193244556L;
	private Long id;
	private String name;
	private String description;
	
	private Long startstatusid;
	private Long endstatusid;
	
	private Long addstatusid;
	private Long addworkflowstatusid;
	
	private Long wsid;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getEndstatusid() {
		return endstatusid;
	}
	
	public void setEndstatusid(Long endstatusid) {
		this.endstatusid = endstatusid;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public String getLikeName() {
		return "%"+ name + "%";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getStartstatusid() {
		return startstatusid;
	}
	
	public void setStartstatusid(Long startstatusid) {
		this.startstatusid = startstatusid;
	}

	
	public Long getAddstatusid() {
		return addstatusid;
	}

	
	public void setAddstatusid(Long addstatusid) {
		this.addstatusid = addstatusid;
	}

	
	public Long getWsid() {
		return wsid;
	}

	
	public void setWsid(Long wsid) {
		this.wsid = wsid;
	}


	public Long getAddworkflowstatusid() {
		return addworkflowstatusid;
	}

	
	public void setAddworkflowstatusid(Long addworkflowstatusid) {
		this.addworkflowstatusid = addworkflowstatusid;
	}
	
}
