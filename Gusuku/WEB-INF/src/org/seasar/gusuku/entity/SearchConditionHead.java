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
import java.sql.Date;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Id;
import org.seasar.dao.annotation.tiger.IdType;

@Bean(table="SEARCH_CONDITION_HEAD")
public class SearchConditionHead implements Serializable {

	private static final long serialVersionUID = -6313929764721870176L;
	
	private Long id;
	private Long accountid;
	private String name;
	private boolean visible;
	private int sort;
	private int amount;
	private String sortkey;
	private String ordr;
	private Date rdate;
	
	public Long getAccountid() {
		return accountid;
	}
	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}
	
	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="SEARCH_CONDITION_HEAD_ID_SEQ")
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public String getOrdr() {
		return ordr;
	}
	
	public void setOrdr(String ordr) {
		this.ordr = ordr;
	}
	
	public String getSortkey() {
		return sortkey;
	}
	
	public void setSortkey(String sortkey) {
		this.sortkey = sortkey;
	}
	

}
