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
package org.seasar.gusuku.web.mypage;

import java.util.Map;

import org.seasar.gusuku.helper.SearchConditionHelper;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

public class HomeAction extends GusukuAction {
	
	private static final long serialVersionUID = 7632043202798048261L;
	private SearchConditionHelper searchConditionHelper;
	private SearchLogic searchLogic;
	
	private String[] visible;
	private Map amount;
	private String id;
	
	@XWorkAction(name = "portlet", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/mypage/home.html")))
	public String init(){
		return SUCCESS;
	}
	
	@XWorkAction(name = "portlet_update", result = @Result(type = "redirect", param = @Param(name = "location", value = "portlet.html")))
	public String update(){
		searchLogic.update(visible,amount);
		return SUCCESS;
	}
	
	@XWorkAction(name = "portlet_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "portlet.html")))
	public String up(){
		searchLogic.sortUp(id,getLoginid());
		return SUCCESS;
	}
	
	@XWorkAction(name = "portlet_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "portlet.html")))
	public String down(){
		searchLogic.sortDown(id,getLoginid());
		return SUCCESS;
	}

	public SearchConditionHelper getSearchConditionHelper() {
		return searchConditionHelper;
	}

	public void setSearchConditionHelper(SearchConditionHelper searchConditionHelper) {
		this.searchConditionHelper = searchConditionHelper;
	}

	public void setAmount(Map amount) {
		this.amount = amount;
	}

	public String[] getVisible() {
		return visible;
	}

	public void setVisible(String[] visible) {
		this.visible = visible;
	}

	public Map getAmount() {
		return amount;
	}

	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
