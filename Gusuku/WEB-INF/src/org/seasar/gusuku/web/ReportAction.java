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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dto.PagerSupport;
import org.seasar.gusuku.dto.ProjectReportDto;
import org.seasar.gusuku.dto.ReportDto;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.entity.Comment;
import org.seasar.gusuku.entity.Component;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.entity.PriorityScheme;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.Report;
import org.seasar.gusuku.entity.StatusHistory;
import org.seasar.gusuku.entity.TypeScheme;
import org.seasar.gusuku.entity.Version;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.CommentHelper;
import org.seasar.gusuku.helper.ComponentHelper;
import org.seasar.gusuku.helper.CustomFormHelper;
import org.seasar.gusuku.helper.CustomValueHelper;
import org.seasar.gusuku.helper.PriorityHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.helper.ReportDataHelper;
import org.seasar.gusuku.helper.ReportHelper;
import org.seasar.gusuku.helper.ResolutionHelper;
import org.seasar.gusuku.helper.StatusHelper;
import org.seasar.gusuku.helper.StatusHistoryHelper;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.helper.VersionHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.gusuku.logic.ReportLogic;
import org.seasar.gusuku.util.ParameterUtil;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.interceptor.ParameterAware;
import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.Preparable;

/**
 * 報告
 * @author duran
 *
 */
public class ReportAction extends GusukuAction implements ParameterAware,Preparable{

	private static final long serialVersionUID = 2438154368323465925L;

	private ReportLogic reportLogic;

	private AccountHelper accountHelper;
	private ProjectHelper projectHelper;
	private TypeHelper typeHelper;
	private PriorityHelper priorityHelper;
	private StatusHelper statusHelper;
	private ReportHelper reportHelper;
	private CommentHelper commentHelper;
	private ResolutionHelper resolutionHelper;
	private StatusHistoryHelper statusHistoryHelper;
	private ReportDataHelper reportDataHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	private ComponentHelper componentHelper;
	private VersionHelper versionHelper;
	private CustomFormHelper customFormHelper;
	private CustomValueHelper customValueHelper;

	private Project project;
	private Report report;
	
	private List<Report> reportList;
	private List<TypeScheme> typeList = new ArrayList<TypeScheme>();
	private List<Project> projectEntryList;
	private List<Account> assigneeList;
	private List<PriorityScheme> priorityList;
	private List<Component> componentList;
	private List<Version> versionList;
 	
	private List<Comment> commentList;
	private List<StatusHistory> statusHistoryList;

	private Map parameters;
	
	private ProjectReportDto projectReportDto = new ProjectReportDto();
	private PagerSupport pagerSupport = new PagerSupport(GusukuConstant.SEARCH_LIMIT,"projectReportDto");
	
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public void prepare(){
	}
	
	public void prepareStep2(){
		projectEntryList = projectHelper.getEntryList(getLoginid());
		project = projectHelper.getProject(ParameterUtil.getParameterLongValue(parameters,"projectid"));
		if(project != null){
			typeList = typeHelper.getTypeListWithScheme(project.getTypeid());
			priorityList = priorityHelper.getPriorityListWithScheme(project.getPriorityid());
			assigneeList = accountHelper.getProjectAccountList(project.getId());
			componentList = componentHelper.getComponentList(project.getId());
			versionList = versionHelper.getVersionList(project.getId());
		}
	}
	public void prepareRegistration(){
		project = projectHelper.getProject(ParameterUtil.getParameterLongValue(parameters,"projectid"));
		Long reportid = ParameterUtil.getParameterLongValue(parameters,"id");
		if(reportid != null){
			typeList = typeHelper.getTypeListWithScheme(project.getTypeid());
		}
		priorityList = priorityHelper.getPriorityListWithScheme(project.getPriorityid());
		assigneeList = accountHelper.getProjectAccountList(project.getId());
		componentList = componentHelper.getComponentList(project.getId());
		versionList = versionHelper.getVersionList(project.getId());
	}

	/**
	 * 報告入力 step1
	 * @return
	 */
	@XWorkAction(name = "report_step1", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/report_step1.html")))
	public String step1() {
		Long projectid = ParameterUtil.getParameterLongValue(parameters,"projectid");
		if(projectid != null){
			project = projectHelper.getProject(projectid);
			typeList = typeHelper.getTypeListWithScheme(project.getTypeid());
		}
		projectEntryList = projectHelper.getEntryList(getLoginid());
		return SUCCESS;
	}

	/**
	 * 報告入力 step2
	 * @return
	 */
	@XWorkAction(name = "report_step2", result = {
			@Result(type = "mayaa", param = @Param(name = "location", value = "/report_step2.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/report_step1.html")) })
	public String step2() {
		Long reportid = ParameterUtil.getParameterLongValue(parameters,"id");
		if(reportid == null){
			//新規登録
			Long projectid = ParameterUtil.getParameterLongValue(parameters,"projectid");
			if(projectid == null){
				addFieldError("projectid",getText("required.select",new String[]{getText("project")}));
			}
			Long typeid = ParameterUtil.getParameterLongValue(parameters,"typeid");
			if(typeid == null){
				addFieldError("typeid",getText("required.select",new String[]{getText("type")}));
			}
			
			if(hasFieldErrors()){
				return INPUT;
			}

		}else{
			//変更
			reportLogic.load(parameters,reportid);
		}
		return SUCCESS;
	}
	
	/**
	 * 報告登録
	 * @return
	 */
	@XWorkAction(name = "report_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/home.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/report_step2.html")) })
	public String registration() {
		//基本入力チェック
		String title = ParameterUtil.getParameterValue(parameters,"title");
		if(StringUtil.isEmpty(title)){
			addFieldError("title",getText("required.input",new String[]{getText("title")}));
		}
		Long priorityid = ParameterUtil.getParameterLongValue(parameters,"priorityid");
		if(priorityid == null){
			addFieldError("priorityid",getText("required.select",new String[]{getText("priority")}));
		}
		
		String detail = ParameterUtil.getParameterValue(parameters,"detail");
		if(StringUtil.isEmpty(detail)){
			addFieldError("detail",getText("required.input",new String[]{getText("detail")}));
		}
		
		//基本DTO作成
		ReportDto dto = new ReportDto();
		dto.setTitle(title);
		dto.setPriorityid(priorityid);
		dto.setDetail(detail);
		dto.setAssigneeid(ParameterUtil.getParameterLongValue(parameters,"assigneeid"));
		dto.setEnvironment(ParameterUtil.getParameterValue(parameters,"environment"));
		dto.setTypeid(ParameterUtil.getParameterLongValue(parameters,"typeid"));
		dto.setComponentid(ParameterUtil.getParameterLongValue(parameters,"componentid"));
		dto.setVersionid(ParameterUtil.getParameterLongValue(parameters,"versionid"));
		Long projectid = ParameterUtil.getParameterLongValue(parameters,"projectid");
		dto.setProjectid(projectid);
		
		dto.setId(ParameterUtil.getParameterLongValue(parameters,"id"));

		//カスタムフォーム入力チェック
		//カスタムフォーム情報取得
		List<CustomFormDetail> formList = customFormHelper.getFormList(projectHelper.getProject(projectid,getLoginid()).getFormid());
		for(CustomFormDetail form : formList){
			//必須チェック
			String formName = "custom"+form.getId();
			String value = null;
			if(form.getFormType().getTagname().equals("file")){
				value = ParameterUtil.getParameterValue(parameters,formName+"FileName");
			}else{
				value = ParameterUtil.getParameterValue(parameters,formName);
			}
			
			if(form.isRequireflag()){
				if(StringUtil.isEmpty(value)){
					if(form.getCustomValueHead() == null){
						addFieldError(formName,getText("required.input",new String[]{form.getLabel()}));
					}else{
						addFieldError(formName,getText("required.select",new String[]{form.getLabel()}));
					}
				}
			}
			//各チェック
			if(!StringUtil.isEmpty(value)){
				switch(form.getChecktype()){
					case 1:
						//長さチェック
						if(form.getLengthhigh() != null && form.getLengthlow() != null){
							if(value.length() > form.getLengthhigh().longValue() || value.length() < form.getLengthlow().longValue()){
								addFieldError(formName,getText("length.value",new String[]{form.getLabel(),form.getLengthlow().toString(),form.getLengthhigh().toString()}));
							}
						}else if(form.getLengthlow() != null && form.getLengthhigh() == null){
							if(value.length() < form.getLengthlow().longValue()){
								addFieldError(formName,getText("length.low.value",new String[]{form.getLabel(),form.getLengthlow().toString()}));
							}
						}else if(form.getLengthhigh() != null && form.getLengthlow() == null ){
							if(value.length() > form.getLengthhigh().longValue()){
								addFieldError(formName,getText("length.high.value",new String[]{form.getLabel(),form.getLengthhigh().toString()}));
							}
						}
						break;
					case 2:
						//日付チェック
						try{
							SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
							Date datevalue = format.parse(value);
							if(form.getDatefrom() != null && form.getDateto() != null){
								if(datevalue.after(form.getDateto()) || datevalue.before(form.getDatefrom())){
									addFieldError(formName,getText("date.value",new String[]{form.getLabel(),format.format(form.getDatefrom()),format.format(form.getDateto())}));
								}
							}else if(form.getDatefrom() != null && form.getDateto() == null){
								if(datevalue.before(form.getDatefrom())){
									addFieldError(formName,getText("date.from.value",new String[]{form.getLabel(),format.format(form.getDatefrom())}));
								}
							}else if(form.getDatefrom() == null && form.getDateto() != null){
								if(datevalue.before(form.getDatefrom())){
									addFieldError(formName,getText("date.to.value",new String[]{form.getLabel(),format.format(form.getDateto())}));
								}
							}
						}catch(ParseException e){
							addFieldError(formName,getText("date.parse.error",new String[]{form.getLabel()}));
						}
						break;
					case 3:
						//範囲チェック
	
							if(form.getRangehigh() != null && form.getRangelow() != null){
								if(Long.parseLong(value) > form.getRangehigh().longValue() || Long.parseLong(value) < form.getRangelow().longValue()){
									addFieldError(formName,getText("range.value",new String[]{form.getLabel(),form.getRangelow().toString(),form.getRangehigh().toString()}));
								}
							}else if(form.getRangelow() != null && form.getRangehigh() == null){
								if(Long.parseLong(value) < form.getRangelow().longValue()){
									addFieldError(formName,getText("range.low.value",new String[]{form.getLabel(),form.getRangelow().toString()}));
								}
							}else if(form.getRangehigh() != null && form.getRangelow() == null ){
								if(Long.parseLong(value) > form.getRangehigh().longValue()){
									addFieldError(formName,getText("range.high.value",new String[]{form.getLabel(),form.getRangehigh().toString()}));
								}
							}
						
						break;
				}
			}
			
			//TODO アップロードの場合はアップロードサイズチェック必要？
			
		}
		
		if(hasFieldErrors()){
			return INPUT;
		}
		
		if(TokenHelper.validToken()){
			reportLogic.registration(dto,parameters,getLoginid());
		}
		return SUCCESS;
	}
	
	/**
	 * 報告詳細
	 * @return
	 */
	@XWorkAction(name = "report_detail", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/report_detail.html")))
	public String detail() {
		Long reportid = ParameterUtil.getParameterLongValue(parameters,"id");
		report = reportHelper.getReport(reportid,getLoginid());
		project = projectHelper
				.getProject(report.getProjectid(),getLoginid());
		
		//List<CustomFormDetail> formList = customFormHelper.getFormList(project.getFormid());
		commentList = commentHelper.getCommentList(reportid);
		statusHistoryList = statusHistoryHelper.getStatusHistoryList(reportid);
		return SUCCESS;
	}

	/**
	 * 報告コメント登録
	 * @return
	 */
	@XWorkAction(name = "comment", result = @Result(type = "redirect", param = @Param(name = "location", value = "/report_detail.html?id=${parameters.id[0]}")))
	public String comment() {
		if(TokenHelper.validToken()){
			reportLogic.addComment(parameters, sessionManager.getAccount());
		}
		return SUCCESS;
	}
	
	/**
	 * 報告コメント削除
	 * @return
	 */
	@XWorkAction(name = "comment_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/report_detail.html?id=${parameters.id[0]}")))
	public String comment_delete() {
		if(TokenHelper.validToken()){
			reportLogic.deleteComment(parameters);
		}
		return SUCCESS;
	}
	
	/**
	 * 報告ステータス変更
	 * @return
	 */
	@XWorkAction(name = "change_status", result = @Result(type = "redirect", param = @Param(name = "location", value = "/report_detail.html?id=${parameters.id[0]}")))
	public String changeStatus() {
		if(TokenHelper.validToken()){
			ReportDto dto = new ReportDto();
			Long id = ParameterUtil.getParameterLongValue(parameters,"id");
			Long nextstatusid = ParameterUtil.getParameterLongValue(parameters,"nextstatusid");
			Long assigneeid = ParameterUtil.getParameterLongValue(parameters,"assigneeid");
			Long resolutionid = ParameterUtil.getParameterLongValue(parameters,"resolutionid");
			dto.setId(id);
			dto.setNextstatusid(nextstatusid);
			if(assigneeid != null){
				dto.setAssigneeid(assigneeid);
			}
			if(resolutionid != null){
				dto.setResolutionid(resolutionid);
			}
			reportLogic.changeStatus(dto,getLoginid());
		}
		return SUCCESS;
	}
	
	/**
	 * 報告一覧
	 * @return
	 */
	@XWorkAction(name = "report_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/report_list.html")))
	public String list(){
		Long projectid = ParameterUtil.getParameterLongValue(parameters,"projectid");
		Long typeid = ParameterUtil.getParameterLongValue(parameters,"typeid");
		Long statusid = ParameterUtil.getParameterLongValue(parameters,"statusid");
		Long page = ParameterUtil.getParameterLongValue(parameters,"page");
		
		project = projectHelper.getProject(projectid);
		
		projectReportDto.setProjectid(projectid);
		projectReportDto.setTypeid(typeid);
		projectReportDto.setStatusid(statusid);
		projectReportDto.setWorkflowid(project.getWorkflowid());
		
		if(page != null){
			projectReportDto.setPage(page.intValue());
		}
		
		projectReportDto = (ProjectReportDto) pagerSupport.getPagerCondition(ServletActionContext.getRequest(),projectReportDto);
		
		reportList = reportHelper.getProjectReportList(projectReportDto);
		
			
		
		return SUCCESS;
	}
	
	public Object getModel(){
		return projectReportDto;
	}
	
	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}

	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	public void setPriorityHelper(PriorityHelper priorityHelper) {
		this.priorityHelper = priorityHelper;
	}

	public void setAccountHelper(AccountHelper accountHelper) {
		this.accountHelper = accountHelper;
	}

	public void setReportLogic(ReportLogic reportLogic) {
		this.reportLogic = reportLogic;
	}

	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	public ProjectHelper getProjectHelper() {
		return projectHelper;
	}

	public AccountHelper getAccountHelper() {
		return accountHelper;
	}

	public PriorityHelper getPriorityHelper() {
		return priorityHelper;
	}

	public Project getProject() {
		return project;
	}

	public Report getReport() {
		return report;
	}

	public void setReportHelper(ReportHelper reportHelper) {
		this.reportHelper = reportHelper;
	}

	
	public CommentHelper getCommentHelper() {
		return commentHelper;
	}

	
	public void setCommentHelper(CommentHelper commentHelper) {
		this.commentHelper = commentHelper;
	}

	
	public StatusHelper getStatusHelper() {
		return statusHelper;
	}

	
	public void setStatusHelper(StatusHelper statusHelper) {
		this.statusHelper = statusHelper;
	}

	
	public ResolutionHelper getResolutionHelper() {
		return resolutionHelper;
	}

	
	public void setResolutionHelper(ResolutionHelper resolutionHelper) {
		this.resolutionHelper = resolutionHelper;
	}

	
	public StatusHistoryHelper getStatusHistoryHelper() {
		return statusHistoryHelper;
	}

	
	public void setStatusHistoryHelper(StatusHistoryHelper statusHistoryHelper) {
		this.statusHistoryHelper = statusHistoryHelper;
	}

	
	public ReportHelper getReportHelper() {
		return reportHelper;
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

	public Map getParameters() {
		return parameters;
	}

	
	public ReportDataHelper getReportDataHelper() {
		return reportDataHelper;
	}

	
	public void setReportDataHelper(ReportDataHelper reportDataHelper) {
		this.reportDataHelper = reportDataHelper;
	}

	public WorkflowStatusHelper getWorkflowStatusHelper() {
		return workflowStatusHelper;
	}

	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}

	
	public List<Report> getReportList() {
		return reportList;
	}

	
	public List<TypeScheme> getTypeList() {
		return typeList;
	}

	
	public List<Project> getProjectEntryList() {
		return projectEntryList;
	}

	
	public List<Comment> getCommentList() {
		return commentList;
	}

	
	public List<StatusHistory> getStatusHistoryList() {
		return statusHistoryList;
	}

	
	public List<Account> getAssigneeList() {
		return assigneeList;
	}

	
	public List<PriorityScheme> getPriorityList() {
		return priorityList;
	}

	
	public List<Component> getComponentList() {
		return componentList;
	}

	
	public void setComponentHelper(ComponentHelper componentHelper) {
		this.componentHelper = componentHelper;
	}

	
	public List<Version> getVersionList() {
		return versionList;
	}

	
	public void setVersionHelper(VersionHelper versionHelper) {
		this.versionHelper = versionHelper;
	}

}
