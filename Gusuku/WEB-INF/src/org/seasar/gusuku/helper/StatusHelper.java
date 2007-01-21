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

import org.seasar.gusuku.dao.StatusDao;
import org.seasar.gusuku.dto.StatusAdminDto;
import org.seasar.gusuku.entity.Status;

/**
 * ステータスに関するHelperクラス
 * @author duran
 *
 */
public class StatusHelper {
	
	private StatusDao statusDao;
	
	/**
	 * ステータス一覧を取得する
	 * @return ステータス一覧
	 */
	public List<Status> getStatusList(){
		return statusDao.findAll();
	}
	
	/**
	 * 指定したワークフローで使用していないステータス一覧を取得する
	 * @param workflowid ワークフローID
	 * @return ステータス一覧
	 */
	public List<Status> getStatusListWithoutWorkflowid(String workflowid){
		return statusDao.findByWithoutWorkflowid(workflowid);
	}
	
	/**
	 * 指定したワークフローとステータスの次のステータス一覧を取得する
	 * @param workflowid ワークフローID
	 * @param statusid ステータスID
	 * @return ステータス一覧
	 */
	public List<Status> getNextstatus(String workflowid,String statusid){
		return statusDao.findNextstatusByWorkflowidAndStatusid(workflowid,statusid);
	}
	
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	public List<Status> getStatusList(StatusAdminDto dto) {
		return statusDao.findByDto(dto);
	}

}
