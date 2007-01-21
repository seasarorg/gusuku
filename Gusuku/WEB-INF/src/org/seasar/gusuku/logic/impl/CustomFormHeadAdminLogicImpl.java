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
import org.seasar.gusuku.dao.CustomFormHeadDao;
import org.seasar.gusuku.dto.CustomFormHeadAdminDto;
import org.seasar.gusuku.dxo.CustomFormHeadDxo;
import org.seasar.gusuku.entity.CustomFormHead;
import org.seasar.gusuku.logic.CustomFormHeadAdminLogic;

public class CustomFormHeadAdminLogicImpl implements CustomFormHeadAdminLogic{

	private CustomFormHeadDao customFormHeadDao;
	private CustomFormHeadDxo customFormHeadDxo;
	
	public void delete(String[] ids) {
		if(ids != null && ids.length >0){
			customFormHeadDao.updateDelflag(ids);
		}
	}

	public CustomFormHeadAdminDto getCustomFormHead(CustomFormHeadAdminDto customFormHeadAdminDto) {
		return customFormHeadDxo.convert(customFormHeadDao.findById(customFormHeadAdminDto.getId()));
	}
	
	public void registration(CustomFormHeadAdminDto customFormHeadAdminDto) {
		CustomFormHead customFormHead = customFormHeadDxo.convert(customFormHeadAdminDto);
		
		if(StringUtil.isEmpty(customFormHeadAdminDto.getId())){
			customFormHeadDao.insert(customFormHead);
		}else{
			customFormHead.setUdate(new Date());
			customFormHeadDao.update(customFormHead);
		}
		
	}

	public void setCustomFormHeadDao(CustomFormHeadDao customFormHeadDao) {
		this.customFormHeadDao = customFormHeadDao;
	}

	public void setCustomFormHeadDxo(CustomFormHeadDxo customFormHeadDxo) {
		this.customFormHeadDxo = customFormHeadDxo;
	}
	
	

}
