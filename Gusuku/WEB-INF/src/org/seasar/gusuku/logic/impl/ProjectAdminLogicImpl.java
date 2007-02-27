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

import java.io.File;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dao.ProjectGroupbaseDao;
import org.seasar.gusuku.dto.ProjectAdminDto;
import org.seasar.gusuku.dxo.ProjectDxo;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.ProjectGroupbase;
import org.seasar.gusuku.logic.ProjectAdminLogic;
import org.seasar.gusuku.util.PropertyUtil;

public class ProjectAdminLogicImpl implements ProjectAdminLogic {
	
	private static final Log LOG = LogFactory.getLog(ProjectAdminLogicImpl.class);
	
	private ProjectGroupbaseDao projectGroupbaseDao;
	private ProjectDao projectDao;
	private ProjectDxo projectDxo;

	public void registration(ProjectAdminDto projectAdminDto) {
		Project project = projectDxo.convert(projectAdminDto);
		
		if(projectAdminDto.getId() == null){
			//ディレクトリ作成
			String uploadDir = PropertyUtil.getProperty(GusukuConstant.UPLOAD_DIR_KEY);
			//プロジェクトディレクトリ作成
			//アップロードディレクトリ + プロジェクトKey + attach
			String baseDir = uploadDir+project.getKey()+ File.separator;
			File dir = new File(baseDir + GusukuConstant.UPLOAD_ATTACHMENT);
			if(dir.mkdirs()){
				LOG.debug("mkdir : Project dir " + baseDir + GusukuConstant.UPLOAD_ATTACHMENT);
			}
			//コメントアップロード用ディレクトリ作成
			dir = new File(baseDir + GusukuConstant.UPLOAD_COMMENT);
			if(dir.mkdir()){
				LOG.debug("mkdir : Project dir " + baseDir + GusukuConstant.UPLOAD_COMMENT);
			}
			
			projectDao.insert(project);
		}else{
			project.setUdate(new Date());
			projectDao.update(project);
		}

	}

	public void memberAdd(Long projectid, Long[] delids) {
		if(delids != null && delids.length > 0){
			for (Long delid : delids) {
				ProjectGroupbase projectGroupbase = new ProjectGroupbase();
				projectGroupbase.setProjectid(projectid);
				projectGroupbase.setGroupbaseid(delid);
				projectGroupbaseDao.insert(projectGroupbase);
			}
		}
	}

	public void memberRemove(Long projectid, Long[] delids) {
		if(delids != null && delids.length > 0){
			for (Long delid : delids) {
				ProjectGroupbase projectGroupbase = new ProjectGroupbase();
				projectGroupbase.setProjectid(projectid);
				projectGroupbase.setGroupbaseid(delid);
				projectGroupbaseDao.delete(projectGroupbase);
			}
		}
	}

	public void delete(Long[] ids) {
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
