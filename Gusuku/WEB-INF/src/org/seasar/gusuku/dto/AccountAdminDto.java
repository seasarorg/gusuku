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

public class AccountAdminDto extends GusukuPagerCondition implements Serializable {

	private static final long serialVersionUID = 8520746126192597905L;

	private Long id;

	private String mailaddr;

	private String password;

	private String name;

	private String kind;

	private String kindname;

	public String getName() {
		return name;
	}

	public String getLikeName() {
		return "%" + name + "%";
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMailaddr() {
		return mailaddr;
	}
	public String getLikeMailaddr() {
		return "%" + mailaddr + "%";
	}
	public void setMailaddr(String mailaddr) {
		this.mailaddr = mailaddr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKindname() {
		return kindname;
	}

	public void setKindname(String kindname) {
		this.kindname = kindname;
	}

}
