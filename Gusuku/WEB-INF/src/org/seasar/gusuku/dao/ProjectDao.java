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
import org.seasar.gusuku.dto.ProjectAdminDto;
import org.seasar.gusuku.entity.Project;

@S2Dao(bean=Project.class)
public interface ProjectDao {
	
	@NoPersistentProperty("rdate")
	public void insert(Project project);
	@NoPersistentProperty("rdate")
	public void update(Project project);
	public void delete(Project project);
	
	public List<Project> findByDto(ProjectAdminDto dto);
	
	@Query("Project.ID = /*id*/ AND Project.DELFLAG = FALSE")
	public Project findById(Long id);

	@Query("Project.ID = /*id*/ AND Project.DELFLAG = FALSE FOR UPDATE")
	public Project findByIdForUpdate(Long projectid);
	
	public void updateDelflag(Long[] delids);
	
	@Query("Project.DELFLAG = FALSE ORDER BY ID")
	public List<Project> findAll();

	/**
	 * 指定したユーザーが参加しているプロジェクトリストを取得します
	 * @param accountid
	 * @return
	 */
	public List<Project> findEntryList(Long accountid);
	
	/**
	 * プロジェクトIDを指定し、指定したユーザーが参加しているプロジェクトを取得<br>
	 * 参加していない場合は取得できない
	 * @param id
	 * @param accountid
	 * @return
	 */
	@Arguments({"id","accountid"})
	public Project findByIdAndAccountid(Long id, Long accountid);

}
