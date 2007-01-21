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

@Bean(noPersistentProperty="valuetype",table="SEARCH_CONDITION_CUSTOM")
public class SearchConditionCustom implements Serializable {

	private static final long serialVersionUID = 2466430570205806736L;
	private long id;
	private long conditionbasicid;
	private long formid;
	private String textvalue;
	private Long rangelow;
	private Long rangehigh;
	private Date datefrom;
	private Date dateto;
	
	private int valuetype;
	
	private CustomFormDetail customFormDetail;
	
	public long getConditionbasicid() {
		return conditionbasicid;
	}
	public void setConditionbasicid(long conditionbasicid) {
		this.conditionbasicid = conditionbasicid;
	}
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
	public long getFormid() {
		return formid;
	}
	public void setFormid(long formid) {
		this.formid = formid;
	}
	
	@Id(IdType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public int getValuetype() {
		return valuetype;
	}
	public void setValuetype(int valuetype) {
		this.valuetype = valuetype;
	}
	
}
