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
import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.dto.StatusAdminDto;
import org.seasar.gusuku.entity.Status;

@S2Dao(bean=Status.class)
public interface StatusDao {
	
	@NoPersistentProperty("rdate")
	public void insert(Status status);
	@NoPersistentProperty("rdate")
	public void update(Status status);
	public void delete(Status status);
	
	public List<Status> findByDto(StatusAdminDto dto);
	
	@Query("DELFLAG = FALSE ORDER BY ID")
	public List<Status> findAll();

	@Query("ID = /*id*/ AND DELFLAG = FALSE")
	public Status findById(String id);

	public void updateDelflag(String[] ids);
	
	public List<Status> findByWithoutWorkflowid(String workflowid);
	
	@Arguments({"workflowid","statusid"})
	public List<Status> findNextstatusByWorkflowidAndStatusid(String workflowid,String statusid);

}
