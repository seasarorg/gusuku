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

import org.seasar.gusuku.dao.WorkflowStatusDao;
import org.seasar.gusuku.entity.WorkflowStatus;

/**
 * ワークフローの状態に関するHelperクラス
 * @author duran
 *
 */
public class WorkflowStatusHelper {

	private WorkflowStatusDao workflowStatusDao;
	
	/**
	 * 指定したIDに従うワークフローステータスを取得する
	 * @param id ワークフローステータスID
	 * @return ワークフローステータス
	 */
	public WorkflowStatus getWorkflowStatus(Long id){
		return workflowStatusDao.findById(id);
	}

	/**
	 * 指定したワークフローIDの開始状態を取得する
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス
	 */
	public WorkflowStatus getStartStatus(Long workflowid) {
		return workflowStatusDao.findStartStatusById(workflowid);
	}

	/**
	 * 指定したワークフローIDの終了状態を取得する
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス
	 */
	public WorkflowStatus getEndStatus(Long workflowid) {
		return workflowStatusDao.findEndStatusById(workflowid);
	}
	
	/**
	 * 指定したワークフローIDの、開始・終了を除く状態一覧を取得する
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス一覧
	 */
	public List<WorkflowStatus> getStatusListWithoutStartAndEnd(Long workflowid){
		return workflowStatusDao.findByWorkflowidWithoutStartAndEnd(workflowid);
	}

	/**
	 * 指定したワークフローIDに従う状態一覧を取得する
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス一覧
	 */
	public List<WorkflowStatus> getStatusList(Long workflowid){
		return workflowStatusDao.findByWorkflowid(workflowid);
	}
	/**
	 * 指定したワークフローIDとワークフローステータスIDの次の状態一覧を取得する
	 * @param workflowstatusid　ワークフローステータスID
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス一覧
	 */
	public List<WorkflowStatus> getNextList(Long workflowstatusid,Long workflowid){
		return workflowStatusDao.findByNextstatus(workflowstatusid,workflowid);
	}
	
	/**
	 * 指定したワークフローIDとワークフローステータスIDを除く次の状態一覧を取得する
	 * @param workflowstatusid ワークフローステータスID
	 * @param workflowid ワークフローID
	 * @return ワークフローステータス一覧
	 */
	public List<WorkflowStatus> getWithoutNextList(Long workflowstatusid,Long workflowid){
		return workflowStatusDao.findByWithoutNextstatus(workflowstatusid,workflowid);
	}
	
	public void setWorkflowStatusDao(WorkflowStatusDao workflowStatusDao) {
		this.workflowStatusDao = workflowStatusDao;
	}
}
