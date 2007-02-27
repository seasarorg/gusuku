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

import javax.servlet.http.HttpServletRequest;

import org.seasar.dao.pager.PagerCondition;
import org.seasar.framework.util.StringUtil;

public class PagerSupport {
	
	private int limit = PagerCondition.NONE_LIMIT;
	private String pageName = "page";
	private String pagerConditionName;
	
	public PagerSupport(int limit,String pagerConditionName){
		this.limit = limit;
		this.pagerConditionName = pagerConditionName;
	}
	
	/**
	 * セッションよりConditionオブジェクトを取得する。<br>
	 * pageが空白 or null の場合は新規検索とみなし、セッションの条件オブジェクトを破棄する
	 * @param request
	 * @param pagerCondition
	 * @return
	 */
	public GusukuPagerCondition getPagerCondition(HttpServletRequest request,GusukuPagerCondition pagerCondition){
		
		if(pagerCondition.getPage() == null && StringUtil.isEmpty(pagerCondition.getSort())){
			request.getSession().removeAttribute(pagerConditionName);
			if(StringUtil.isEmpty(pagerCondition.getSort())){
				pagerCondition.setSort("PRIORITY");
				pagerCondition.setOrder("ASC");
			}
		}
		
		GusukuPagerCondition dto = (GusukuPagerCondition) request.getSession().getAttribute(pagerConditionName);
		
		if(dto == null ){
			dto = pagerCondition;
			dto.setLimit(limit);
			request.getSession().setAttribute(pagerConditionName,dto);
			//System.out.println("Save Condition");
		}else{
			//System.out.println("Load Condition");
			if(!StringUtil.isEmpty(pagerCondition.getSort()) && !StringUtil.isEmpty(pagerCondition.getOrder())){
				dto.setSort(pagerCondition.getSort());
				dto.setOrder(pagerCondition.getOrder());
			}
		}
		
		updateOffset(request,dto);
		return dto;
	}
	
	/**
	 * Conditionオブジェクトのoffset値とPage値を更新する
	 * @param request
	 * @param dto
	 */
	private void updateOffset(HttpServletRequest request,GusukuPagerCondition dto){
		int page = getPage(request);
		dto.setOffset((page-1) * dto.getLimit());
		dto.setPage(page);
	}
	/**
	 * HttpServletResuestより指定されたpage数を取得する
	 * @param request
	 * @return ページ数
	 */
	private int getPage(HttpServletRequest request){
		String value = request.getParameter(pageName);
		if(StringUtil.isEmpty(value)){
			return 1;
		}else{
			try{
				int page = Integer.parseInt(value);
				if(page <= 0){
					page = 1;
				}
				return page;
			}catch(NumberFormatException e){
				return 1;
			}
		}
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
