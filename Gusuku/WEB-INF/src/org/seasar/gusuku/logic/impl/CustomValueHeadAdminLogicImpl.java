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
import org.seasar.gusuku.dao.CustomValueHeadDao;
import org.seasar.gusuku.dto.CustomValueHeadAdminDto;
import org.seasar.gusuku.dxo.CustomValueHeadDxo;
import org.seasar.gusuku.entity.CustomValueHead;
import org.seasar.gusuku.logic.CustomValueHeadAdminLogic;

public class CustomValueHeadAdminLogicImpl implements CustomValueHeadAdminLogic{

	private CustomValueHeadDao customValueHeadDao;
	private CustomValueHeadDxo customValueHeadDxo;
	
	public void delete(String[] ids) {
		if(ids != null && ids.length >0){
			customValueHeadDao.updateDelflag(ids);
		}
	}

	public CustomValueHeadAdminDto getCustomValueHead(CustomValueHeadAdminDto customValueHeadAdminDto) {
		return customValueHeadDxo.convert(customValueHeadDao.findById(customValueHeadAdminDto.getId()));
	}
	
	public void registration(CustomValueHeadAdminDto customValueHeadAdminDto) {
		CustomValueHead customValueHead = customValueHeadDxo.convert(customValueHeadAdminDto);
		
		if(StringUtil.isEmpty(customValueHeadAdminDto.getId())){
			customValueHeadDao.insert(customValueHead);
		}else{
			customValueHeadDao.update(customValueHead);
		}
		
	}

	public void setCustomValueHeadDao(CustomValueHeadDao customValueHeadDao) {
		this.customValueHeadDao = customValueHeadDao;
	}

	public void setCustomValueHeadDxo(CustomValueHeadDxo customValueHeadDxo) {
		this.customValueHeadDxo = customValueHeadDxo;
	}
	
	

}
