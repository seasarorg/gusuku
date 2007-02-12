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

@Bean(table="CUSTOM_FORM_DETAIL")
public class CustomFormDetail implements Serializable {

	private static final long serialVersionUID = -8866719784007789658L;
	private Long id;
	private Long formheadid;
	private Long valueid;
	private Long typeid;
	private String label;
	private String defaultvalue;
	private int valuetype;
	private boolean requireflag;
	private int checktype;
	private Long lengthhigh;
	private Long lengthlow;
	private Date datefrom;
	private Date dateto;
	private Long rangehigh;
	private Long rangelow;
	private int sort;
	private String comment;
	private boolean delflag;
	
	private FormType formType;
	
	private CustomValueHead customValueHead;

	public Date getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}
	public Date getDateto() {
		return dateto;
	}
	public void setDateto(Date dateto) {
		this.dateto = dateto;
	}
	public long getFormheadid() {
		return formheadid;
	}
	public void setFormheadid(long formheadid) {
		this.formheadid = formheadid;
	}
	
	@Id(IdType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getRangehigh() {
		return rangehigh;
	}
	public void setRangehigh(Long rangehigh) {
		this.rangehigh = rangehigh;
	}
	public Long getRangelow() {
		return rangelow;
	}
	public void setRangelow(Long rangelow) {
		this.rangelow = rangelow;
	}
	public boolean isRequireflag() {
		return requireflag;
	}
	public void setRequireflag(boolean requireflag) {
		this.requireflag = requireflag;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Long getTypeid() {
		return typeid;
	}
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	public Long getValueid() {
		return valueid;
	}
	public void setValueid(Long valueid) {
		this.valueid = valueid;
	}
	
	@Relation(relationNo=1,relationKey="VALUEID:ID")
	public CustomValueHead getCustomValueHead() {
		return customValueHead;
	}
	public void setCustomValueHead(CustomValueHead customValueHead) {
		this.customValueHead = customValueHead;
	}
	
	@Relation(relationNo=0,relationKey="TYPEID:ID")
	public FormType getFormType() {
		return formType;
	}
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	
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
	
	public String getDefaultvalue() {
		return defaultvalue;
	}
	
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	
	public Long getLengthhigh() {
		return lengthhigh;
	}
	
	public void setLengthhigh(Long lengthhigh) {
		this.lengthhigh = lengthhigh;
	}
	
	public Long getLengthlow() {
		return lengthlow;
	}
	
	public void setLengthlow(Long lengthlow) {
		this.lengthlow = lengthlow;
	}
	
	public int getValuetype() {
		return valuetype;
	}
	
	public void setValuetype(int valuetype) {
		this.valuetype = valuetype;
	}
	
	public int getChecktype() {
		return checktype;
	}
	
	public void setChecktype(int checktype) {
		this.checktype = checktype;
	}
	
}
