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

@Bean(table="RESOLUTION_SCHEME")
public class ResolutionScheme implements Serializable {

	private static final long serialVersionUID = 8860416059271714701L;
	private Long id;
	private Long headid;
	private Long resolutionid;
	private int sort;
	
	private Resolution resolution;
	
	public Long getHeadid() {
		return headid;
	}
	public void setHeadid(Long headid) {
		this.headid = headid;
	}
	//@Id(IdType.IDENTITY)
	@Id(value=IdType.SEQUENCE,sequenceName="RESOLUTION_SCHEME_ID_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Long getResolutionid() {
		return resolutionid;
	}
	public void setResolutionid(Long resolutionid) {
		this.resolutionid = resolutionid;
	}
	
	@Relation(relationNo=0,relationKey="RESOLUTIONID:ID")
	public Resolution getResolution() {
		return resolution;
	}
	
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
	
	
}
