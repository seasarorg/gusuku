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
package org.seasar.gusuku.logic.impl;

import java.util.Date;

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.NextstatusDao;
import org.seasar.gusuku.dao.WorkflowDao;
import org.seasar.gusuku.dao.WorkflowStatusDao;
import org.seasar.gusuku.dto.WorkflowAdminDto;
import org.seasar.gusuku.dto.WorkflowRoleAdminDto;
import org.seasar.gusuku.dxo.WorkflowDxo;
import org.seasar.gusuku.entity.Nextstatus;
import org.seasar.gusuku.entity.Workflow;
import org.seasar.gusuku.entity.WorkflowStatus;
import org.seasar.gusuku.logic.WorkflowAdminLogic;

public class WorkflowAdminLogicImpl implements WorkflowAdminLogic {

	private WorkflowDao workflowDao;
	private WorkflowDxo workflowDxo;

	private WorkflowStatusDao workflowStatusDao;
	private NextstatusDao nextstatusDao;

	@Aspect("j2ee.requiredTx")
	public void registration(WorkflowAdminDto workflowAdminDto) {
		Workflow workflow = workflowDxo.convert(workflowAdminDto);

		if (StringUtil.isEmpty(workflowAdminDto.getId())) {
			workflowDao.insert(workflow);
			WorkflowStatus start = new WorkflowStatus();
			start.setWorkflowid(workflow.getId());
			start.setSflag(true);
			start.setStatusid(Long.parseLong(workflowAdminDto
					.getStartstatusid()));
			workflowStatusDao.insert(start);
			WorkflowStatus end = new WorkflowStatus();
			end.setWorkflowid(workflow.getId());
			end.setEflag(true);
			end.setStatusid(Long.parseLong(workflowAdminDto.getEndstatusid()));
			workflowStatusDao.insert(end);
		} else {
			workflow.setUdate(new Date());
			workflowDao.update(workflow);

			WorkflowStatus start = workflowStatusDao
					.findStartStatusById(workflowAdminDto.getId());
			start.setStatusid(Long.parseLong(workflowAdminDto
					.getStartstatusid()));
			workflowStatusDao.update(start);
			WorkflowStatus end = workflowStatusDao
					.findEndStatusById(workflowAdminDto.getId());
			end.setStatusid(Long.parseLong(workflowAdminDto.getEndstatusid()));
			workflowStatusDao.update(end);

		}
	}

	public void delete(String[] deleteids) {
		if (deleteids != null && deleteids.length > 0) {
			workflowDao.updateDelflag(deleteids);
		}
	}

	public WorkflowAdminDto getWorkflow(WorkflowAdminDto workflowAdminDto) {
		Workflow workflow = workflowDao.findById(workflowAdminDto.getId());
		WorkflowAdminDto dto = workflowDxo.convert(workflow);

		//開始 終了を取得
		WorkflowStatus start = workflowStatusDao.findStartStatusById(dto
				.getId());
		WorkflowStatus end = workflowStatusDao.findEndStatusById(dto.getId());

		dto.setStartstatusid(Long.toString(start.getStatusid()));
		dto.setEndstatusid(Long.toString(end.getStatusid()));
		return dto;
	}

	public void addStatus(WorkflowAdminDto workflowAdminDto) {
		if (!StringUtil.isEmpty(workflowAdminDto.getAddstatusid())) {
			WorkflowStatus workflowStatus = new WorkflowStatus();
			workflowStatus.setStatusid(Long.parseLong(workflowAdminDto
					.getAddstatusid()));
			workflowStatus.setWorkflowid(Long.parseLong(workflowAdminDto
					.getId()));
			workflowStatusDao.insert(workflowStatus);
		}
	}
	

	public void deleteStatus(String delid) {
		if(!StringUtil.isEmpty(delid)){
			WorkflowStatus workflowStatus = new WorkflowStatus();
			workflowStatus.setId(Long.parseLong(delid));
			workflowStatusDao.delete(workflowStatus);
		}
		
	}

	public void addTransition(WorkflowAdminDto workflowAdminDto) {
		if(!StringUtil.isEmpty(workflowAdminDto.getAddworkflowstatusid())){
		Nextstatus nextstatus = new Nextstatus();
			nextstatus.setWorkflowstatusid(Long.parseLong(workflowAdminDto.getWsid()));
			nextstatus.setNextstatusid(Long.parseLong(workflowAdminDto
					.getAddworkflowstatusid()));
			nextstatusDao.insert(nextstatus);
		}
	}
	
	public void deleteTransition(String[] delids,String workflowstatusid) {
		if (delids != null && delids.length > 0) {
			for (String delid : delids) {
				nextstatusDao.deleteByWorkflowstatusidAndNextstatusid(workflowstatusid,delid);
			}
		}
	}
	
	@Aspect("j2ee.requiredTx")
	public void updateRole(WorkflowRoleAdminDto workflowRoleAdminDto) {
		
		workflowStatusDao.updateRole(workflowRoleAdminDto.getId());
		
		if(workflowRoleAdminDto.getReporter() != null && workflowRoleAdminDto.getReporter().length > 0){
			workflowStatusDao.updateReporterRole(workflowRoleAdminDto.getReporter());
		}
		if(workflowRoleAdminDto.getAssignee() != null && workflowRoleAdminDto.getAssignee().length > 0){
			workflowStatusDao.updateAssigneeRole(workflowRoleAdminDto.getAssignee());
		}
		if(workflowRoleAdminDto.getLeader() != null && workflowRoleAdminDto.getLeader().length > 0){
			workflowStatusDao.updateLeaderRole(workflowRoleAdminDto.getLeader());
		}
		
	}

	public void setWorkflowDao(WorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

	public void setWorkflowDxo(WorkflowDxo workflowDxo) {
		this.workflowDxo = workflowDxo;
	}

	public void setWorkflowStatusDao(WorkflowStatusDao workflowStatusDao) {
		this.workflowStatusDao = workflowStatusDao;
	}

	
	public void setNextstatusDao(NextstatusDao nextstatusDao) {
		this.nextstatusDao = nextstatusDao;
	}

}
