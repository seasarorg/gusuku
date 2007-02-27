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
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dto.PagerSupport;
import org.seasar.gusuku.dto.SearchDto;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.ComponentHelper;
import org.seasar.gusuku.helper.CustomFormHelper;
import org.seasar.gusuku.helper.CustomValueHelper;
import org.seasar.gusuku.helper.PriorityHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.helper.ReportDataHelper;
import org.seasar.gusuku.helper.SearchConditionHelper;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.helper.VersionHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.gusuku.util.ParameterUtil;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.ParameterAware;
import com.opensymphony.webwork.util.TokenHelper;

public class SearchAction extends GusukuAction implements ParameterAware {
	
	private static final long serialVersionUID = -1712334803870171927L;
	private CustomFormHelper customFormHelper;
	private CustomValueHelper customValueHelper;
	private ReportDataHelper reportDataHelper;
	private PriorityHelper priorityHelper;
	private AccountHelper accountHelper;
	private TypeHelper typeHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	private ComponentHelper componentHelper;
	private VersionHelper versionHelper;
	
	private ProjectHelper projectHelper;

	private SearchConditionHelper searchConditionHelper;
	
	private SearchLogic searchLogic;
	
	private Map<String, String[]> parameters;
	
	private List searchResult = new ArrayList();
	
	private PagerSupport pagerSupport = new PagerSupport(GusukuConstant.SEARCH_LIMIT,"searchCondition");
	private SearchDto dto = new SearchDto();
	
	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	@XWorkAction(name = "search", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/search.html")))
	public String init(){
		return SUCCESS;
	}
	
	//@XWorkAction(name = "search_load", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/search.html")))
	@XWorkAction(name = "search_load", result = @Result(type = "chain", param = @Param(name = "actionName", value = "search_list")))
	public String load(){
		Long id = ParameterUtil.getParameterLongValue(parameters,"id");
		searchLogic.load(parameters,id);
		return SUCCESS;
	}
	
	@XWorkAction(name = "search_list", result = {
			@Result(type = "mayaa", param = @Param(name = "location", value = "/search.html")),
			@Result(name = "save", type = "redirect", param = @Param(name = "location", value = "/search.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/search.html")) })
	public String search(){
		//パラメーターから条件組み立て
		dto = searchLogic.makeCondition(null,parameters,getLoginid());
		Long page = ParameterUtil.getParameterLongValue(parameters,"page");
		Long limit = ParameterUtil.getParameterLongValue(parameters,"limit");
		String sort = ParameterUtil.getParameterValue(parameters,"sort");
		String order = ParameterUtil.getParameterValue(parameters,"order");
		boolean sortchange = false;
		if(!StringUtil.isEmpty(sort)){
			dto.setSort(sort);
			//ソート条件が変更された場合はページ数を初期化
			page = null;
			sortchange = true;
			System.out.println("SORT CHANGE");
		}
		if(!StringUtil.isEmpty(order)){
			dto.setOrder(order);
		}
		
		if(page != null){
			dto.setPage(page.intValue());
		}
		if(limit != null){
			pagerSupport.setLimit(limit.intValue());
		}
		
		
		dto = (SearchDto)pagerSupport.getPagerCondition(ServletActionContext.getRequest(),dto);
		//検索条件復元
		if(page != null || sortchange){
			searchLogic.load(parameters,dto);
			System.out.println("LOAD CONDITION");
		}
		
		searchResult = searchLogic.search(dto);

		return SUCCESS;
	}
	
	public String save(){
		
		String name = ParameterUtil.getParameterValue(parameters,"name");
		if(StringUtil.isEmpty(name)){
			addFieldError("name",getText("required.input",new String[]{getText("conditionname")}));
		}
		
		if(hasFieldErrors()){
			return INPUT;
		}
		if(TokenHelper.validToken()){
			searchLogic.saveCondition(parameters,getLoginid());
		}
		return "save";
	}
	public Object getModel(){
		return dto;
	}

	public CustomFormHelper getCustomFormHelper() {
		return customFormHelper;
	}

	public void setCustomFormHelper(CustomFormHelper customFormHelper) {
		this.customFormHelper = customFormHelper;
	}

	public CustomValueHelper getCustomValueHelper() {
		return customValueHelper;
	}

	public void setCustomValueHelper(CustomValueHelper customValueHelper) {
		this.customValueHelper = customValueHelper;
	}

	public ReportDataHelper getReportDataHelper() {
		return reportDataHelper;
	}

	public void setReportDataHelper(ReportDataHelper reportDataHelper) {
		this.reportDataHelper = reportDataHelper;
	}

	public ProjectHelper getProjectHelper() {
		return projectHelper;
	}

	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}

	public AccountHelper getAccountHelper() {
		return accountHelper;
	}

	public void setAccountHelper(AccountHelper accountHelper) {
		this.accountHelper = accountHelper;
	}

	public PriorityHelper getPriorityHelper() {
		return priorityHelper;
	}

	public void setPriorityHelper(PriorityHelper priorityHelper) {
		this.priorityHelper = priorityHelper;
	}

	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	public void setSearchLogic(SearchLogic searchLogic) {
		this.searchLogic = searchLogic;
	}

	public void setSearchConditionHelper(SearchConditionHelper searchConditionHelper) {
		this.searchConditionHelper = searchConditionHelper;
	}

	public SearchConditionHelper getSearchConditionHelper() {
		return searchConditionHelper;
	}

	public List getSearchResult() {
		return searchResult;
	}

	public WorkflowStatusHelper getWorkflowStatusHelper() {
		return workflowStatusHelper;
	}

	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}

	
	public ComponentHelper getComponentHelper() {
		return componentHelper;
	}

	
	public void setComponentHelper(ComponentHelper componentHelper) {
		this.componentHelper = componentHelper;
	}

	
	public VersionHelper getVersionHelper() {
		return versionHelper;
	}

	
	public void setVersionHelper(VersionHelper versionHelper) {
		this.versionHelper = versionHelper;
	}

}
