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
package org.seasar.gusuku.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.gusuku.dto.SearchDto;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.Report;
import org.seasar.gusuku.entity.SearchConditionHead;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.helper.ReportHelper;
import org.seasar.gusuku.helper.SearchConditionHelper;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

/**
 * ホーム
 * @author duran
 *
 */
public class HomeAction extends GusukuAction {
	
	private static final long serialVersionUID = -7204303464714451026L;
	private ReportHelper reportHelper;
	private ProjectHelper projectHelper;
	private SearchConditionHelper searchConditionHelper;
	private SearchLogic searchLogic;
	private List<SearchConditionInformation> homeList;
	private List<Report> openList;
	private List<Report> reportList;
	
	private List<Project> entryList;
	private Map<Long,List> assignListMap = new HashMap<Long,List>();
	
	/**
	 * ホーム
	 * @return
	 */
	@XWorkAction(name = "home", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/home.html")))
	public String home(){

		//自分へのアサインリストを取得
		entryList = projectHelper.getEntryList(getLoginid());
		
		if(getAccount().isAssignflag()){
			for(Project project:entryList){
				List<Report> assignList = reportHelper.getAssginListByProjectid(getLoginid(),project.getId());
				assignListMap.put(project.getId(),assignList);
			}
		}
		
		//ホーム設定の条件に従って取得
		List<SearchConditionHead> conditionList = searchConditionHelper.getVisibleSearchCondition(getLoginid());
		homeList = new ArrayList<SearchConditionInformation>();

		for(Iterator ite= conditionList.iterator();ite.hasNext();){
			SearchConditionHead searchConditionHead = (SearchConditionHead)ite.next();
			Map<String,String[]> parameters = new HashMap<String,String[]>();
			searchLogic.load(parameters,searchConditionHead.getId());
			
			
			SearchDto searchDto = searchLogic.makeCondition(searchConditionHead,parameters,getLoginid());
			List result = searchLogic.search(searchDto);
			
			SearchConditionInformation searchConditionInformation = new SearchConditionInformation();
			searchConditionInformation.setSearchConditionHead(searchConditionHead);
			searchConditionInformation.setSearchResult(result);
			homeList.add(searchConditionInformation);
		}
		
		return SUCCESS;
	}

	public List<Report> getOpenList() {
		return openList;
	}

	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportHelper(ReportHelper reportHelper) {
		this.reportHelper = reportHelper;
	}

	
	public void setSearchConditionHelper(SearchConditionHelper searchConditionHelper) {
		this.searchConditionHelper = searchConditionHelper;
	}

	
	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}
	
	private class SearchConditionInformation{
		private SearchConditionHead searchConditionHead;
		private List searchResult;
		
		public SearchConditionHead getSearchConditionHead() {
			return searchConditionHead;
		}
		
		public void setSearchConditionHead(SearchConditionHead searchConditionHead) {
			this.searchConditionHead = searchConditionHead;
		}
		
		public List getSearchResult() {
			return searchResult;
		}
		
		public void setSearchResult(List searchResult) {
			this.searchResult = searchResult;
		}
		
	}

	
	public List getHomeList() {
		return homeList;
	}

	
	public Map<Long, List> getAssignListMap() {
		return assignListMap;
	}

	
	public List<Project> getEntryList() {
		return entryList;
	}

	
	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}
	
}
