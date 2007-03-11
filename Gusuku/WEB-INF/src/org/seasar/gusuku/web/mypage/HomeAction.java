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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dto.SelectValue;
import org.seasar.gusuku.entity.SearchConditionHead;
import org.seasar.gusuku.helper.SearchConditionHelper;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.gusuku.util.SelectValueUtil;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.Preparable;

public class HomeAction extends GusukuAction implements Preparable{
	
	private static final long serialVersionUID = 7632043202798048261L;
	private SearchConditionHelper searchConditionHelper;
	private SearchLogic searchLogic;
	
	private List<SearchConditionHead> list;
	private List<SelectValue> sortkeyList;
	private List<SelectValue> orderList;
	private Long[] visible;
	private Map amount;
	private Map sortkey;
	private Map ordr;
	private Long id;
	
	public void prepare(){
	}
	
	public void prepareInit(){
		list = searchConditionHelper.getSearchConditionHead(getLoginid());
		sortkeyList = SelectValueUtil.getSortKeyList();
		orderList = SelectValueUtil.getOrderList();
	}
	public void prepareUpdate(){
		list = searchConditionHelper.getSearchConditionHead(getLoginid());
		sortkeyList = SelectValueUtil.getSortKeyList();
		orderList = SelectValueUtil.getOrderList();
	}
	
	@XWorkAction(name = "portlet", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/mypage/home.html")))
	public String init(){
	
		return SUCCESS;
	}
	
	@XWorkAction(name = "portlet_update", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "portlet.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/mypage/home.html")) })
	public String update(){
		if(amount != null && amount.size() > 0){
			for(Iterator ite = amount.keySet().iterator();ite.hasNext();){
				String id = (String)ite.next();
				String tmp = ((String[])amount.get(id))[0];
				if(StringUtil.isEmpty(tmp)){
					addFieldError("amount",getText("home.amount.required"));
					break;
				}
				try{
					Long.parseLong(tmp);
				}catch(NumberFormatException e){
					addFieldError("amount",getText("home.amount.number"));
					break;
				}
			}
		}
		if(hasFieldErrors()){
			return INPUT;
		}
		searchLogic.update(visible,amount,sortkey,ordr);
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

	public void setSearchConditionHelper(SearchConditionHelper searchConditionHelper) {
		this.searchConditionHelper = searchConditionHelper;
	}

	public void setAmount(Map amount) {
		this.amount = amount;
	}

	public Long[] getVisible() {
		return visible;
	}

	public void setVisible(Long[] visible) {
		this.visible = visible;
	}

	public Map getAmount() {
		return amount;
	}

	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public List<SearchConditionHead> getList() {
		return list;
	}

	
	public List<SelectValue> getOrderList() {
		return orderList;
	}

	
	public List<SelectValue> getSortkeyList() {
		return sortkeyList;
	}

	
	public void setOrdr(Map ordr) {
		this.ordr = ordr;
	}

	
	public void setSortkey(Map sortkey) {
		this.sortkey = sortkey;
	}

}
