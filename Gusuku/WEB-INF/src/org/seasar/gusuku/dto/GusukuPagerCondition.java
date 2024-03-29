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

import org.seasar.dao.pager.DefaultPagerCondition;

public class GusukuPagerCondition extends DefaultPagerCondition {
	
	private static final long serialVersionUID = 2936257425109490902L;
	private Integer page;
	private String sort = "";
	private String order = "";

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	
	public String getSort() {
		return sort;
	}

	
	public void setSort(String sort) {
		this.sort = sort;
	}

	
	public String getOrder() {
		return order;
	}

	
	public void setOrder(String order) {
		this.order = order;
	}
	


}
