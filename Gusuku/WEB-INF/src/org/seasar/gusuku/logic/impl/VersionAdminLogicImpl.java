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

import java.util.Date;

import org.seasar.gusuku.dao.VersionDao;
import org.seasar.gusuku.dto.VersionAdminDto;
import org.seasar.gusuku.dxo.VersionDxo;
import org.seasar.gusuku.entity.Version;
import org.seasar.gusuku.logic.VersionAdminLogic;


public class VersionAdminLogicImpl implements VersionAdminLogic {
	
	private VersionDao versionDao;
	private VersionDxo versionDxo;

	public void registration(VersionAdminDto versionAdminDto) {
		Version version = versionDxo.convert(versionAdminDto);
		
		if(version.getId() == null){
			versionDao.insert(version);
		}else{
			version.setUdate(new Date());
			versionDao.update(version);
		}

	}

	public void delete( Long[] delids) {
		if(delids != null && delids.length > 0){
			versionDao.updateDelflag(delids);
		}
	}

	public VersionAdminDto getVersion(VersionAdminDto versionAdminDto) {
		Version version = versionDao.findById(versionAdminDto.getId());
		return versionDxo.convert(version);
	}

	
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}

	
	public void setVersionDxo(VersionDxo versionDxo) {
		this.versionDxo = versionDxo;
	}

}
