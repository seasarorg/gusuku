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
import org.seasar.dao.annotation.tiger.Relation;

@Bean(table="COMMENT")
public class Comment implements Serializable {

	private static final long serialVersionUID = 8924512994954099458L;
	private Long id;
	private Long reportid;
	private Long writerid;
	private String comment;
	private String filename;
	private String messageid;
	private boolean delflag;
	private Date rdate;
	private Date udate;

	private Account writer;
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isDelflag() {
		return delflag;
	}
	
	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}
	
	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="COMMENT_ID_SEQ")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getWriterid() {
		return writerid;
	}
	
	public void setWriterid(Long writerid) {
		this.writerid = writerid;
	}

	
	public Long getReportid() {
		return reportid;
	}

	
	public void setReportid(Long reportid) {
		this.reportid = reportid;
	}

	@Relation(relationNo=0,relationKey="WRITERID:ID")
	public Account getWriter() {
		return writer;
	}

	
	public void setWriter(Account writer) {
		this.writer = writer;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String finename) {
		this.filename = finename;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	
	
}
