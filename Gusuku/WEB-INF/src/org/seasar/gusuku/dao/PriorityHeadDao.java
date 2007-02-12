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

import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.dto.PriorityHeadAdminDto;
import org.seasar.gusuku.entity.PriorityHead;

@S2Dao(bean = PriorityHead.class)
public interface PriorityHeadDao {
	
	@NoPersistentProperty("rdate")
	public void insert(PriorityHead priorityHead);
	@NoPersistentProperty("rdate")
	public void update(PriorityHead priorityHead);
	public void delete(PriorityHead priorityHead);
	
	public List<PriorityHead> findByDto(PriorityHeadAdminDto dto);

	@Query("DELFLAG = FALSE ORDER BY ID")
	public List<PriorityHead> findAll();
	
	@Query("ID = /*id*/ AND DELFLAG = FALSE")
	public PriorityHead findById(Long id);

	public void updateDelflag(Long[] delids);
}
