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
import org.seasar.gusuku.dto.VersionAdminDto;
import org.seasar.gusuku.entity.Version;

@S2Dao(bean=Version.class)
public interface VersionDao {
	
	@NoPersistentProperty("rdate")
	public void insert(Version version);
	@NoPersistentProperty("rdate")
	public void update(Version version);
	public void delete(Version version);
	
	
	public List<Version> findByDto(VersionAdminDto versionAdminDto);
	
	@Query("ID = /*id*/ AND DELFLAG = FALSE ")
	public Version findById(Long id);
	
	
	public void updateDelflag(Long[] delids);
	
	@Query("PROJECTID = /*projectid*/ AND DELFLAG = FALSE ORDER BY ID")
	public List<Version> findByProjectid(Long projectid);

}