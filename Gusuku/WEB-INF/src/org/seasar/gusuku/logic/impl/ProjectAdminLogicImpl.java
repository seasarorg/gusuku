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
package org.seasar.gusuku.logic.impl;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dao.ProjectGroupbaseDao;
import org.seasar.gusuku.dto.ProjectAdminDto;
import org.seasar.gusuku.dxo.ProjectDxo;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.ProjectGroupbase;
import org.seasar.gusuku.logic.ProjectAdminLogic;

public class ProjectAdminLogicImpl implements ProjectAdminLogic {
	
	private ProjectGroupbaseDao projectGroupbaseDao;
	private ProjectDao projectDao;
	private ProjectDxo projectDxo;

	public void registration(ProjectAdminDto projectAdminDto) {
		Project project = projectDxo.convert(projectAdminDto);
		
		if(StringUtil.isEmpty(projectAdminDto.getId())){
			projectDao.insert(project);
		}else{
			projectDao.update(project);
		}

	}

	public void memberAdd(String projectid, String[] ids) {
		if(ids != null && ids.length > 0){
			for (String id : ids) {
				ProjectGroupbase projectGroupbase = new ProjectGroupbase();
				projectGroupbase.setProjectid(Long.parseLong(projectid));
				projectGroupbase.setGroupbaseid(Long.parseLong(id));
				projectGroupbaseDao.insert(projectGroupbase);
			}
		}
	}

	public void memberRemove(String projectid, String[] ids) {
		if(ids != null && ids.length > 0){
			for (String id : ids) {
				ProjectGroupbase projectGroupbase = new ProjectGroupbase();
				projectGroupbase.setProjectid(Long.parseLong(projectid));
				projectGroupbase.setGroupbaseid(Long.parseLong(id));
				projectGroupbaseDao.delete(projectGroupbase);
			}
		}
	}

	public void delete(String[] ids) {
		if(ids != null && ids.length > 0){
			projectDao.updateDelflag(ids);
		}
	}

	public ProjectAdminDto getProject(ProjectAdminDto projectAdminDto) {
		Project project = projectDao.findById(projectAdminDto.getId());
		return projectDxo.convert(project);
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setProjectDxo(ProjectDxo projectDxo) {
		this.projectDxo = projectDxo;
	}

	public void setProjectGroupbaseDao(ProjectGroupbaseDao projectGroupbaseDao) {
		this.projectGroupbaseDao = projectGroupbaseDao;
	}

	
}
