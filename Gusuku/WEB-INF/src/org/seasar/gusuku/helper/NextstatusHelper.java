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

import org.seasar.gusuku.dao.NextstatusDao;
import org.seasar.gusuku.entity.Nextstatus;

/**
 * ワークフローステータスに関するHelperクラス
 * @author duran
 *
 */
public class NextstatusHelper {

	private NextstatusDao nextstatusDao;
	
	/**
	 * 指定したワークフローの次の状態を取得する
	 * @param workflowid 対象ワークフローID
	 * @return 次の状態
	 */
	public List<Nextstatus> getNextstatusList(Long workflowid){
		return nextstatusDao.findByWorkflowstatusid(workflowid);
	}

	
	public void setNextstatusDao(NextstatusDao nextstatusDao) {
		this.nextstatusDao = nextstatusDao;
	}
	
	
}
