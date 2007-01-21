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

public class CustomFormDetailAdminDto implements Serializable {

	private static final long serialVersionUID = 6319400848769707739L;
	private String id;
	private String formheadid;
	private String valueid;
	private String typeid;
	private String label;
	private String comment;
	private String defaultvalue;
	private String requireflag;
	private String valuetype;
	private String checktype;
	private String lengthhigh;
	private String lengthlow;
	private String datefrom;
	private String dateto;
	private String rangehigh;
	private String rangelow;
	private String sort;
	
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getFormheadid() {
		return formheadid;
	}
	public void setFormheadid(String formheadid) {
		this.formheadid = formheadid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRangelow() {
		return rangelow;
	}
	public void setRangelow(String rangelow) {
		this.rangelow = rangelow;
	}
	public String getRequireflag() {
		return requireflag;
	}
	public void setRequireflag(String requireflag) {
		this.requireflag = requireflag;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getValueid() {
		return valueid;
	}
	public void setValueid(String valueid) {
		this.valueid = valueid;
	}
	
	public String getDefaultvalue() {
		return defaultvalue;
	}
	
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	
	public String getChecktype() {
		return checktype;
	}
	
	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}
	
	public String getLengthhigh() {
		return lengthhigh;
	}
	
	public void setLengthhigh(String lengthhigh) {
		this.lengthhigh = lengthhigh;
	}
	
	public String getLengthlow() {
		return lengthlow;
	}
	
	public void setLengthlow(String lengthlow) {
		this.lengthlow = lengthlow;
	}
	
	public String getValuetype() {
		return valuetype;
	}
	
	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}
	
	public String getRangehigh() {
		return rangehigh;
	}
	
	public void setRangehigh(String rangehigh) {
		this.rangehigh = rangehigh;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
