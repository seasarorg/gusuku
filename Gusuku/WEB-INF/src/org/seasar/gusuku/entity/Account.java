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

import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;
import org.seasar.dao.annotation.tiger.Relation;


public class Account implements Serializable {

	private static final long serialVersionUID = 7358624794127027130L;
	private Long id;
	private String mailaddr;
	private String password;
	private String name;
	private int kind;
	private boolean delflag;
	private Date rdate;
	private Date udate;

	private AccountKind accountKind;
	
	@Relation(relationNo=0,relationKey="KIND:ID")
	public AccountKind getAccountKind() {
		return accountKind;
	}

	public void setAccountKind(AccountKind accountKind) {
		this.accountKind = accountKind;
	}

	public boolean isDelflag() {
		return delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	@Id(IdType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailaddr() {
		return mailaddr;
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

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

}
