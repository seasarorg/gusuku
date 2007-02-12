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
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.gusuku.entity.WorkflowStatus;

@S2Dao(bean=WorkflowStatus.class)
public interface WorkflowStatusDao {
	
	public void insert(WorkflowStatus workflowStatus);
	public void update(WorkflowStatus workflowStatus);
	public void delete(WorkflowStatus workflowStatus);
	
	@Query("WORKFLOW_STATUS.ID = /*id*/")
	public WorkflowStatus findById(Long id);
	
	@Query("WORKFLOWID = /*workflowid*/ AND SFLAG = TRUE")
	public WorkflowStatus findStartStatusById(Long workflowid);

	@Query("WORKFLOWID = /*workflowid*/ AND EFLAG = TRUE")
	public WorkflowStatus findEndStatusById(Long workflowid);
	
	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND WORKFLOW_STATUS.SFLAG = FALSE AND WORKFLOW_STATUS.EFLAG = FALSE AND Status.DELFLAG = FALSE ORDER BY WORKFLOW_STATUS.STATUSID")
	public List<WorkflowStatus> findByWorkflowidWithoutStartAndEnd(Long workflowid);

	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND Status.DELFLAG = FALSE ORDER BY WORKFLOW_STATUS.STATUSID")
	public List<WorkflowStatus> findByWorkflowid(Long workflowid);

	@Arguments({"workflowstatusid","workflowid"})
	public List<WorkflowStatus> findByNextstatus(Long workflowstatusid,Long workflowid);
	
	@Arguments({"workflowstatusid","workflowid"})
	public List<WorkflowStatus> findByWithoutNextstatus(Long workflowstatusid,Long workflowid);

	@Query("WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/ AND WORKFLOW_STATUS.STATUSID = /*statusid*/")
	@Arguments({"workflowid","statusid"})
	public WorkflowStatus findByWorkflowidAndStatusid(Long workflowid,Long statusid);
	
	@Sql("UPDATE WORKFLOW_STATUS SET WORKFLOW_STATUS.REPORTER = TRUE WHERE WORKFLOW_STATUS.ID IN /*reporter*/()")
	public void updateReporterRole(Long[] reporter);
	
	@Sql("UPDATE WORKFLOW_STATUS SET WORKFLOW_STATUS.ASSIGNEE = TRUE WHERE WORKFLOW_STATUS.ID IN /*assignee*/()")
	public void updateAssigneeRole(Long[] assignee);
	
	@Sql("UPDATE WORKFLOW_STATUS SET WORKFLOW_STATUS.LEADER = TRUE WHERE WORKFLOW_STATUS.ID IN /*leader*/()")
	public void updateLeaderRole(Long[] leader);
	
	@Sql("UPDATE WORKFLOW_STATUS SET WORKFLOW_STATUS.REPORTER = FALSE,WORKFLOW_STATUS.ASSIGNEE = FALSE ,WORKFLOW_STATUS.LEADER = FALSE WHERE WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/")
	public void updateRole(Long workflowid);
	
}

