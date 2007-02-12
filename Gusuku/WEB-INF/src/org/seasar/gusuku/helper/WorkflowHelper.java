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
package org.seasar.gusuku.helper;

import java.util.List;

import org.seasar.gusuku.dao.WorkflowDao;
import org.seasar.gusuku.dto.WorkflowAdminDto;
import org.seasar.gusuku.entity.Workflow;

/**
 * ワークフローに関するHelperクラス
 * @author duran
 *
 */
public class WorkflowHelper {

	private WorkflowDao workflowDao;

	/**
	 * ワークフロー一覧を取得する
	 * @return ワークフロー一覧
	 */
	public List<Workflow> getWorkflowList() {
		return workflowDao.findAll();
	}
	
	/**
	 * ワークフローを取得する
	 * @param id ワークフローID
	 * @return
	 */
	public Workflow getWorkflow(Long id){
		return workflowDao.findById(id);
	}

	public void setWorkflowDao(WorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

	public List<Workflow> getWorkflowList(WorkflowAdminDto dto) {
		return workflowDao.findByDto(dto);
	}

}
