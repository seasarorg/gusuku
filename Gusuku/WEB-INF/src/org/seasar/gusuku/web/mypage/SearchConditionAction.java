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

import org.seasar.gusuku.helper.SearchConditionHelper;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

public class SearchConditionAction extends GusukuAction {

	private static final long serialVersionUID = 5227360435378086925L;
	private SearchLogic searchLogic;
	private String[] delids;
	
	private SearchConditionHelper searchConditionHelper;
	
	@XWorkAction(name = "condition", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/mypage/condition.html")))
	public String init(){
		return SUCCESS;
	}
	
	@XWorkAction(name = "condition_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/mypage/condition.html")))
	public String delete(){
		searchLogic.delete(delids);
		return SUCCESS;
	}

	public String[] getDelids() {
		return delids;
	}

	public void setDelids(String[] delids) {
		this.delids = delids;
	}

	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}
	public SearchConditionHelper getSearchConditionHelper() {
		return searchConditionHelper;
	}
	public void setSearchConditionHelper(SearchConditionHelper searchConditionHelper) {
		this.searchConditionHelper = searchConditionHelper;
	}

}
