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

@Bean(table="REPORT_DATA")
public class ReportData implements Serializable {
	
	private static final long serialVersionUID = 1252543865335335821L;
	private Long id;
	private Long reportid;
	private Long formid;
	private String textvalue;
	private Long numericvalue;
	private Date datevalue;
	
	private CustomFormDetail customFormDetail;
	
	public Date getDatevalue() {
		return datevalue;
	}
	
	public void setDatevalue(Date datevalue) {
		this.datevalue = datevalue;
	}
	
	public Long getFormid() {
		return formid;
	}
	
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	
	@Id(IdType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumericvalue() {
		return numericvalue;
	}
	
	public void setNumericvalue(Long numericvalue) {
		this.numericvalue = numericvalue;
	}
	
	public Long getReportid() {
		return reportid;
	}
	
	public void setReportid(Long reportid) {
		this.reportid = reportid;
	}
	
	public String getTextvalue() {
		return textvalue;
	}
	
	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}

	@Relation(relationNo=0,relationKey="FORMID:ID")
	public CustomFormDetail getCustomFormDetail() {
		return customFormDetail;
	}

	
	public void setCustomFormDetail(CustomFormDetail customFormDetail) {
		this.customFormDetail = customFormDetail;
	}
	
	
}
