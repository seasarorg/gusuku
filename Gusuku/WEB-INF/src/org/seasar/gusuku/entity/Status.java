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

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

@Bean(table="STATUS")
public class Status implements Serializable {

	private static final long serialVersionUID = -5746411857128983686L;
	private Long id;
	private String name;
	private String icon;
	private String transition;
	private boolean assignflag;
	private boolean resolutionflag;
	private boolean mailflag;
	private boolean rssflag;
	private String subject;
	private String description;
	private boolean delflag;
	private Date rdate;
	private Date udate;

	public boolean isDelflag() {
		return delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="STATUS_ID_SEQ")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getTransition() {
		return transition;
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public boolean isResolutionflag() {
		return resolutionflag;
	}

	public void setResolutionflag(boolean resolutionflag) {
		this.resolutionflag = resolutionflag;
	}

	
	public boolean isAssignflag() {
		return assignflag;
	}

	
	public void setAssignflag(boolean assignflag) {
		this.assignflag = assignflag;
	}

	public boolean isMailflag() {
		return mailflag;
	}

	public void setMailflag(boolean mailflag) {
		this.mailflag = mailflag;
	}

	public boolean isRssflag() {
		return rssflag;
	}

	public void setRssflag(boolean rssflag) {
		this.rssflag = rssflag;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
