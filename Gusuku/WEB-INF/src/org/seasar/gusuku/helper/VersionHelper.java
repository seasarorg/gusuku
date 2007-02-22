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

import org.seasar.gusuku.dao.VersionDao;
import org.seasar.gusuku.dto.VersionAdminDto;
import org.seasar.gusuku.entity.Version;


public class VersionHelper {
	
	private VersionDao versionDao;
	
	public List<Version> getVersionList(VersionAdminDto versionAdminDto){
		return versionDao.findByDto(versionAdminDto);
	}

	public List<Version> getVersionList(Long projectid){
		return versionDao.findByProjectid(projectid);
	}
	
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}

}
