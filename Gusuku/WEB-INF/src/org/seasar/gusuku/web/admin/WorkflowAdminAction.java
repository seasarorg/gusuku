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

import org.seasar.gusuku.dto.WorkflowAdminDto;
import org.seasar.gusuku.entity.Status;
import org.seasar.gusuku.entity.Workflow;
import org.seasar.gusuku.entity.WorkflowStatus;
import org.seasar.gusuku.helper.StatusHelper;
import org.seasar.gusuku.helper.WorkflowHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.gusuku.logic.WorkflowAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

/**
 * ワークフロー管理
 * @author duran
 *
 */
public class WorkflowAdminAction extends GusukuAction implements ModelDriven,Preparable {

	private static final long serialVersionUID = 5402723495850746817L;

	private WorkflowAdminLogic workflowAdminLogic;
	
	private WorkflowAdminDto dto = new WorkflowAdminDto();
	
	private WorkflowHelper workflowHelper;
	private StatusHelper statusHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	
	private List<Workflow> list;
	
	private List intervalList;
	private List entryList;
	private WorkflowStatus start;
	private WorkflowStatus end;
	private Status current;
	private List<WorkflowStatus> nextList;
	private List<WorkflowStatus> nextEntryList;
	
	private List<Status> statusList;
	
	private Workflow workflow;
	
	private Long[] delid;
	
	public void prepare(){
	}
	public void prepareInit(){
		statusList = statusHelper.getStatusList();
	}
	public void prepareDone(){
		statusList = statusHelper.getStatusList();
	}
	
	/**
	 * ワークフロー一覧
	 * @return
	 */
	@XWorkAction(name = "workflow_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/workflow_list.html")))
	public String list() {
		list = workflowHelper.getWorkflowList(dto);
		return SUCCESS;
	}

	/**
	 * ワークフロー入力
	 * @return
	 */
	@XWorkAction(name = "workflow_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/workflow_add.html")))
	public String init() {
		if (dto.getId() != null) {
			dto = workflowAdminLogic.getWorkflow(dto);
		}
		return SUCCESS;
	}

	/**
	 * ワークフロー登録
	 * @return
	 */
	@XWorkAction(name = "workflow_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/workflow_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/workflow_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			workflowAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * ワークフロー削除
	 * @return
	 */
	@XWorkAction(name = "workflow_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/workflow_list.html")))
	public String deldone() {
		workflowAdminLogic.delete(delid);
		return SUCCESS;
	}
	
	/**
	 * ワークフローステータス一覧
	 * @return
	 */
	@XWorkAction(name = "flow_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/flow_edit.html")))
	public String flow() {
		workflow = workflowHelper.getWorkflow(dto.getId());
		start = workflowStatusHelper.getStartStatus(dto.getId());
		end = workflowStatusHelper.getEndStatus(dto.getId());
		intervalList = workflowStatusHelper.getStatusListWithoutStartAndEnd(dto.getId());
		entryList = statusHelper.getStatusListWithoutWorkflowid(dto.getId());
		return SUCCESS;
	}
	
	/**
	 * ワークフローステータス追加
	 * @return
	 */
	@XWorkAction(name = "flow_add", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/flow_edit.html?id=${model.id}")))
	public String flowAdd() {
		return SUCCESS;
	}
	/**
	 * ワークフローステータス追加 追加
	 * @return
	 */	
	public String addStatus(){
		workflowAdminLogic.addStatus(dto);
		return SUCCESS;
	}
	/**
	 * ワークフローステータス追加 削除
	 * @return
	 */
	public String deleteStatus(){
		if(delid != null && delid.length == 1){
			workflowAdminLogic.deleteStatus(delid[0]);
		}
		return SUCCESS;
	}
	
	/**
	 * ワークフローアクション一覧
	 * @return
	 */
	@XWorkAction(name = "transition_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/transition_edit.html")))
	public String transition() {
		workflow = workflowHelper.getWorkflow(dto.getId());
		current = workflowStatusHelper.getWorkflowStatus(dto.getWsid()).getStatus();
		nextList = workflowStatusHelper.getNextList(dto.getWsid(),dto.getId());
		nextEntryList = workflowStatusHelper.getWithoutNextList(dto.getWsid(),dto.getId());
		return SUCCESS;
	}
	
	/**
	 * ワークフローアクション追加
	 * @return
	 */
	@XWorkAction(name = "transition_add", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/transition_edit.html?id=${model.id}&wsid=${model.wsid}")))
	public String transitionAdd() {
		return SUCCESS;
	}
	/**
	 * ワークフローアクション追加 追加
	 * @return
	 */	
	public String addTransition(){
		workflowAdminLogic.addTransition(dto);
		return SUCCESS;
	}
	/**
	 * ワークフローアクション追加 削除
	 * @return
	 */
	public String deleteTransition(){
		workflowAdminLogic.deleteTransition(delid,dto.getWsid());
		return SUCCESS;
	}
	
	
	public Object getModel() {
		return dto;
	}


	
	public void setWorkflowAdminLogic(WorkflowAdminLogic workflowAdminLogic) {
		this.workflowAdminLogic = workflowAdminLogic;
	}

	
	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	
	public void setWorkflowHelper(WorkflowHelper workflowHelper) {
		this.workflowHelper = workflowHelper;
	}

	
	public void setStatusHelper(StatusHelper statusHelper) {
		this.statusHelper = statusHelper;
	}

	
	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}

	public List<Workflow> getList() {
		return list;
	}
	
	public List<Status> getStatusList() {
		return statusList;
	}
	
	public Workflow getWorkflow() {
		return workflow;
	}
	
	public WorkflowStatus getEnd() {
		return end;
	}
	
	public List getEntryList() {
		return entryList;
	}
	
	public List getIntervalList() {
		return intervalList;
	}
	
	public WorkflowStatus getStart() {
		return start;
	}
	
	public Status getCurrent() {
		return current;
	}
	
	public List<WorkflowStatus> getNextEntryList() {
		return nextEntryList;
	}
	
	public List<WorkflowStatus> getNextList() {
		return nextList;
	}

}
