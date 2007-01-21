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
	private String id;
	private String name;
	private String description;
	
	private String startstatusid;
	private String endstatusid;
	
	private String addstatusid;
	private String addworkflowstatusid;
	
	private String wsid;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEndstatusid() {
		return endstatusid;
	}
	
	public void setEndstatusid(String endstatusid) {
		this.endstatusid = endstatusid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
	public String getStartstatusid() {
		return startstatusid;
	}
	
	public void setStartstatusid(String startstatusid) {
		this.startstatusid = startstatusid;
	}

	
	public String getAddstatusid() {
		return addstatusid;
	}

	
	public void setAddstatusid(String addstatusid) {
		this.addstatusid = addstatusid;
	}

	
	public String getWsid() {
		return wsid;
	}

	
	public void setWsid(String wsid) {
		this.wsid = wsid;
	}


	public String getAddworkflowstatusid() {
		return addworkflowstatusid;
	}

	
	public void setAddworkflowstatusid(String addworkflowstatusid) {
		this.addworkflowstatusid = addworkflowstatusid;
	}
	
}
