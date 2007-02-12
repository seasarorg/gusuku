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

import org.seasar.gusuku.dao.TypeHeadDao;
import org.seasar.gusuku.dto.TypeHeadAdminDto;
import org.seasar.gusuku.dxo.TypeHeadDxo;
import org.seasar.gusuku.entity.TypeHead;
import org.seasar.gusuku.logic.TypeHeadAdminLogic;

public class TypeHeadAdminLogicImpl implements TypeHeadAdminLogic {

	private TypeHeadDao typeHeadDao;
	private TypeHeadDxo typeHeadDxo;

	public void registration(TypeHeadAdminDto typeHeadAdminDto) {
		TypeHead typeHead = typeHeadDxo.convert(typeHeadAdminDto);
		
		if(typeHeadAdminDto.getId() == null){
			typeHeadDao.insert(typeHead);
		}else{
			typeHead.setUdate(new Date());
			typeHeadDao.update(typeHead);
		}
	}

	public void delete(Long[] delids) {
		if (delids != null && delids.length > 0) {
			typeHeadDao.updateDelflag(delids);
		}

	}

	public TypeHeadAdminDto getTypeHeadAdminDto(
			TypeHeadAdminDto typeHeadAdminDto) {
		return typeHeadDxo.convert(typeHeadDao.findById(typeHeadAdminDto.getId()));
	}

	public void setTypeHeadDao(TypeHeadDao typeHeadDao) {
		this.typeHeadDao = typeHeadDao;
	}

	public void setTypeHeadDxo(TypeHeadDxo typeHeadDxo) {
		this.typeHeadDxo = typeHeadDxo;
	}

}
