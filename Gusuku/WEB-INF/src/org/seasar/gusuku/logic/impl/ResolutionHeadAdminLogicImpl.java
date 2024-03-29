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

import org.seasar.gusuku.dao.ResolutionHeadDao;
import org.seasar.gusuku.dto.ResolutionHeadAdminDto;
import org.seasar.gusuku.dxo.ResolutionHeadDxo;
import org.seasar.gusuku.entity.ResolutionHead;
import org.seasar.gusuku.logic.ResolutionHeadAdminLogic;

public class ResolutionHeadAdminLogicImpl implements ResolutionHeadAdminLogic {

	private ResolutionHeadDao resolutionHeadDao;
	private ResolutionHeadDxo resolutionHeadDxo;

	public void registration(ResolutionHeadAdminDto resolutionHeadAdminDto) {
		ResolutionHead resolutionHead = resolutionHeadDxo.convert(resolutionHeadAdminDto);
		
		if(resolutionHeadAdminDto.getId() == null){
			resolutionHeadDao.insert(resolutionHead);
		}else{
			resolutionHead.setUdate(new Date());
			resolutionHeadDao.update(resolutionHead);
		}
	}

	public void delete(Long[] delids) {
		if (delids != null && delids.length > 0) {
			resolutionHeadDao.updateDelflag(delids);
		}

	}

	public ResolutionHeadAdminDto getResolutionHeadAdminDto(
			ResolutionHeadAdminDto resolutionHeadAdminDto) {
		return resolutionHeadDxo.convert(resolutionHeadDao.findById(resolutionHeadAdminDto.getId()));
	}

	public void setResolutionHeadDao(ResolutionHeadDao resolutionHeadDao) {
		this.resolutionHeadDao = resolutionHeadDao;
	}

	public void setResolutionHeadDxo(ResolutionHeadDxo resolutionHeadDxo) {
		this.resolutionHeadDxo = resolutionHeadDxo;
	}

}
