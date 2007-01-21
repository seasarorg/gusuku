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
package org.seasar.gusuku.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.entity.WorkflowStatus;

@S2Dao(bean=WorkflowStatus.class)
public interface WorkflowStatusDao {
	
	public void insert(WorkflowStatus workflowStatus);
	public void update(WorkflowStatus workflowStatus);
	public void delete(WorkflowStatus workflowStatus);
	
	@Query("WORKFLOW_STATUS.ID = /*id*/")
	public WorkflowStatus findById(String id);
	
	@Query("WORKFLOWID = /*workflowid*/ AND SFLAG = TRUE")
	public WorkflowStatus findStartStatusById(String workflowid);

	@Query("WORKFLOWID = /*workflowid*/ AND EFLAG = TRUE")
	public WorkflowStatus findEndStatusById(String workflowid);
	
	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND WORKFLOW_STATUS.SFLAG = FALSE AND WORKFLOW_STATUS.EFLAG = FALSE AND Status.DELFLAG = FALSE ORDER BY WORKFLOW_STATUS.STATUSID")
	public List<WorkflowStatus> findByWorkflowidWithoutStartAndEnd(String workflowid);

	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND Status.DELFLAG = FALSE ORDER BY WORKFLOW_STATUS.STATUSID")
	public List<WorkflowStatus> findByWorkflowid(String workflowid);

	@Arguments({"workflowstatusid","workflowid"})
	public List<WorkflowStatus> findByNextstatus(String workflowstatusid,String workflowid);
	
	@Arguments({"workflowstatusid","workflowid"})
	public List<WorkflowStatus> findByWithoutNextstatus(String workflowstatusid,String workflowid);

	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND WORKFLOW_STATUS.STATUSID = /*statusid*/")
	@Arguments({"workflowid","statusid"})
	public WorkflowStatus findByWorkflowidAndStatusid(String workflowid,String statusid);
	
}

