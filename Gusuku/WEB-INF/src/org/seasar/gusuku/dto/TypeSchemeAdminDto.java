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


public class TypeSchemeAdminDto implements Serializable {

	private static final long serialVersionUID = 3532324322218877902L;
	private Long id;
	private Long headid;
	private Long[] typeid;
	
	public Long getHeadid() {
		return headid;
	}
	
	public void setHeadid(Long headid) {
		this.headid = headid;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Long[] getTypeid() {
		return typeid;
	}

	
	public void setTypeid(Long[] typeid) {
		this.typeid = typeid;
	}
	
}
