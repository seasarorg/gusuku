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

@Bean(table="MAIL_CONDITION")
public class MailCondition implements Serializable {

	private static final long serialVersionUID = 3245283947952275147L;
	private Long id;
	private Long accountid;
	private Long projectid;
	private Long comment;
	private Long start;
	private Long process;
	private Long end;
	
	private Account account;
	
	@Id(IdType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAccountid() {
		return accountid;
	}
	
	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}
	
	public Long getComment() {
		return comment;
	}
	
	public void setComment(Long comment) {
		this.comment = comment;
	}
	
	public Long getEnd() {
		return end;
	}
	
	public void setEnd(Long end) {
		this.end = end;
	}
	
	public Long getProcess() {
		return process;
	}
	
	public void setProcess(Long process) {
		this.process = process;
	}
	
	public Long getProjectid() {
		return projectid;
	}
	
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
	public Long getStart() {
		return start;
	}
	
	public void setStart(Long start) {
		this.start = start;
	}
	
	@Relation(relationNo=0,relationKey="ACCOUNTID:ID")
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
