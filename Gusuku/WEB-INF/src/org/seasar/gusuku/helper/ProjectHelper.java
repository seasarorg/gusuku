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

import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dto.ProjectAdminDto;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.exception.EntityNotFoundException;
import org.seasar.gusuku.exception.EntryProjectException;

/**
 * プロジェクトに関するHelperクラス
 * @author duran
 *
 */
public class ProjectHelper {
	
	private ProjectDao projectDao;
	
	/**
	 * プロジェクト一覧を取得する
	 * @return プロジェクト一覧
	 */
	public List<Project> getProjectList(){
		return projectDao.findAll();
	}
	
	/**
	 * 指定したプロジェクトIDのプロジェクトを取得する
	 * @param id 対象プロジェクトID
	 * @param accountid アカウントID
	 * @return プロジェクト
	 */
	public Project getProject(Long id,Long accountid){
		//参加プロジェクトかどうかのチェック
		Project project = projectDao.findByIdAndAccountid(id,accountid);
		if(project == null){
			throw new EntryProjectException(id + "");
		}
		return project;
	}
	
	/**
	 * 指定したプロジェクトIDのプロジェクトを取得する
	 * @param id 対象プロジェクトID
	 * @return プロジェクト
	 */
	public Project getProject(Long id){
		if(id != null){
			Project project = projectDao.findById(id);
			if(project == null){
				throw new EntityNotFoundException(id + "");
			}
			return project;
		}
		return null;
	}
	
	/**
	 * 指定したユーザーIDに関連づいたプロジェクト一覧を取得する
	 * @param userid 対象ユーザーID
	 * @return プロジェクト一覧
	 */
	public List<Project> getEntryList(Long userid) {
		return projectDao.findEntryList(userid);
	}
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public List<Project> getProjectList(ProjectAdminDto dto) {
		return projectDao.findByDto(dto);
	}	
}
