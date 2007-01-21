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

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.ResolutionDao;
import org.seasar.gusuku.dto.ResolutionAdminDto;
import org.seasar.gusuku.dxo.ResolutionDxo;
import org.seasar.gusuku.entity.Resolution;
import org.seasar.gusuku.logic.ResolutionAdminLogic;

public class ResolutionAdminLogicImpl implements ResolutionAdminLogic {

	private ResolutionDao resolutionDao;
	private ResolutionDxo resolutionDxo;

	public void registration(ResolutionAdminDto resolutionAdminDto) {
		Resolution resolution = resolutionDxo.convert(resolutionAdminDto);
		
		if(StringUtil.isEmpty(resolutionAdminDto.getId())){
			resolutionDao.insert(resolution);
		}else{
			resolution.setUdate(new Date());
			resolutionDao.update(resolution);
		}
	}

	public void delete(String[] delids) {
		if (delids != null && delids.length > 0) {
			resolutionDao.updateDelflag(delids);
		}

	}

	public ResolutionAdminDto getResolutionAdminDto(ResolutionAdminDto resolutionAdminDto) {
		return resolutionDxo.convert(resolutionDao.findById(resolutionAdminDto.getId()));
	}

	public void setResolutionDao(ResolutionDao resolutionDao) {
		this.resolutionDao = resolutionDao;
	}

	public void setResolutionDxo(ResolutionDxo resolutionDxo) {
		this.resolutionDxo = resolutionDxo;
	}

}
