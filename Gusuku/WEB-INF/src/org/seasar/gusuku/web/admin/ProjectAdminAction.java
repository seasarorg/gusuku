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
package org.seasar.gusuku.web.admin;

import java.util.List;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dto.ProjectAdminDto;
import org.seasar.gusuku.entity.Groupbase;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.CustomFormHelper;
import org.seasar.gusuku.helper.GroupbaseHelper;
import org.seasar.gusuku.helper.PriorityHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.helper.ResolutionHelper;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.helper.WorkflowHelper;
import org.seasar.gusuku.logic.ProjectAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * プロジェクト管理
 * @author duran
 *
 */
public class ProjectAdminAction extends GusukuAction implements ModelDriven {

	private static final long serialVersionUID = 1532329301918514343L;
	private ProjectAdminLogic projectAdminLogic;
	private ProjectHelper projectHelper;
	private AccountHelper accountHelper;
	private GroupbaseHelper groupbaseHelper;
	private WorkflowHelper workflowHelper;
	private CustomFormHelper customFormHelper;
	private TypeHelper typeHelper;
	private ResolutionHelper resolutionHelper;
	private PriorityHelper priorityHelper;
	
	private List list;
	
	private List<Groupbase> memberList;
	private List<Groupbase> groupList;
	
	private String[] delid;
	private String[] addid;
	private String[] removeid;
	
	private ProjectAdminDto dto = new ProjectAdminDto();

	/**
	 * プロジェクト入力
	 * @return
	 */
	@XWorkAction(name = "project_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/project_add.html")))
	public String init() {
		if (!StringUtil.isEmpty(dto.getId())) {
			dto = projectAdminLogic.getProject(dto);
		}
		return SUCCESS;
	}
	
	/**
	 * プロジェクト一覧
	 * @return
	 */
	@XWorkAction(name = "project_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/project_list.html")))
	public String list() {
		list = projectHelper.getProjectList(dto);
		return SUCCESS;
	}
	
	/**
	 * プロジェクト登録
	 * @return
	 */
	@XWorkAction(name = "project_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/project_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/project_add.html")) })
	public String done(){
		if(TokenHelper.validToken()){
			projectAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	/**
	 * プロジェクト削除
	 * @return
	 */
	@XWorkAction(name = "project_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/project_list.html")))
	public String deldone() {
		projectAdminLogic.delete(delid);
		return SUCCESS;
	}
	
	/**
	 * プロジェクトグループ一覧
	 * @return
	 */
	@XWorkAction(name = "project_member", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/project_member.html")))
	public String project() {
		memberList = groupbaseHelper.getGroupList(dto.getId());
		groupList = groupbaseHelper.getWithoutsGroupList(dto.getId());
		return SUCCESS;
	}
	
	/**
	 * プロジェクトグループ移動
	 * @return
	 */
	@XWorkAction(name = "project_move", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/project_member.html?id=${model.id}")))
	public String move(){
		return SUCCESS;
	}
	
	/**
	 * プロジェクトグループ移動 追加
	 * @return
	 */
	public String doAddbutton(){
		projectAdminLogic.memberAdd(dto.getId(),addid);
		return SUCCESS;
	}
	
	/**
	 * プロジェクトグループ移動 削除
	 * @return
	 */
	public String doDeletebutton(){
		projectAdminLogic.memberRemove(dto.getId(),removeid);
		return SUCCESS;
	}

	public List getList() {
		return list;
	}

	public Object getModel() {
		return dto;
	}
	
	public void setAccountHelper(AccountHelper accountHelper){
		this.accountHelper = accountHelper;
	}

	public void setProjectAdminLogic(ProjectAdminLogic projectAdminLogic) {
		this.projectAdminLogic = projectAdminLogic;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	public List<Groupbase> getGroupList() {
		return groupList;
	}

	public List<Groupbase> getMemberList() {
		return memberList;
	}

	public void setAddid(String[] addid) {
		this.addid = addid;
	}

	public void setGroupbaseHelper(GroupbaseHelper groupbaseHelper) {
		this.groupbaseHelper = groupbaseHelper;
	}

	public void setRemoveid(String[] removeid) {
		this.removeid = removeid;
	}

	
	public WorkflowHelper getWorkflowHelper() {
		return workflowHelper;
	}

	
	public void setWorkflowHelper(WorkflowHelper workflowHelper) {
		this.workflowHelper = workflowHelper;
	}

	public CustomFormHelper getCustomFormHelper() {
		return customFormHelper;
	}

	public void setCustomFormHelper(CustomFormHelper customFormHelper) {
		this.customFormHelper = customFormHelper;
	}

	
	public AccountHelper getAccountHelper() {
		return accountHelper;
	}

	
	public ProjectHelper getProjectHelper() {
		return projectHelper;
	}

	
	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}

	
	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	
	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	
	public PriorityHelper getPriorityHelper() {
		return priorityHelper;
	}

	
	public void setPriorityHelper(PriorityHelper priorityHelper) {
		this.priorityHelper = priorityHelper;
	}

	
	public ResolutionHelper getResolutionHelper() {
		return resolutionHelper;
	}

	
	public void setResolutionHelper(ResolutionHelper resolutionHelper) {
		this.resolutionHelper = resolutionHelper;
	}

}
