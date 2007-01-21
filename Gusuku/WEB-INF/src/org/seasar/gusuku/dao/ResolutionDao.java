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
import org.seasar.gusuku.dto.ResolutionAdminDto;
import org.seasar.gusuku.entity.Resolution;

@S2Dao(bean=Resolution.class)
public interface ResolutionDao {

	@NoPersistentProperty("rdate")
	public void insert(Resolution resolution);
	@NoPersistentProperty("rdate")
	public void update(Resolution resolution);
	public void delete(Resolution resolution);
	
	public List<Resolution> findByDto(ResolutionAdminDto dto);
	
	@Query("DELFLAG = FALSE ORDER BY ID")
	public List<Resolution> findAll();
	

	@Query("ID = /*id*/ AND DELFLAG = FALSE")
	public Resolution findById(String id);
	
	public void updateDelflag(String[] ids);
	
	@Query("ID NOT IN (SELECT RESOLUTIONID FROM RESOLUTION_SCHEME WHERE HEADID= /*headid*/) AND DELFLAG = FALSE ORDER BY ID")
	public List<Resolution> findWithoutSchemeByHeadid(String headid);
	
}
