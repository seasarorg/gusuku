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

import org.seasar.gusuku.dao.PriorityHeadDao;
import org.seasar.gusuku.dto.PriorityHeadAdminDto;
import org.seasar.gusuku.dxo.PriorityHeadDxo;
import org.seasar.gusuku.entity.PriorityHead;
import org.seasar.gusuku.logic.PriorityHeadAdminLogic;

public class PriorityHeadAdminLogicImpl implements PriorityHeadAdminLogic {

	private PriorityHeadDao priorityHeadDao;
	private PriorityHeadDxo priorityHeadDxo;

	public void registration(PriorityHeadAdminDto priorityHeadAdminDto) {
		PriorityHead priorityHead = priorityHeadDxo.convert(priorityHeadAdminDto);
		
		if(priorityHeadAdminDto.getId() == null){
			priorityHeadDao.insert(priorityHead);
		}else{
			priorityHead.setUdate(new Date());
			priorityHeadDao.update(priorityHead);
		}
	}

	public void delete(Long[] delids) {
		if (delids != null && delids.length > 0) {
			priorityHeadDao.updateDelflag(delids);
		}

	}

	public PriorityHeadAdminDto getPriorityHeadAdminDto(
			PriorityHeadAdminDto priorityHeadAdminDto) {
		return priorityHeadDxo.convert(priorityHeadDao.findById(priorityHeadAdminDto.getId()));
	}

	public void setPriorityHeadDao(PriorityHeadDao priorityHeadDao) {
		this.priorityHeadDao = priorityHeadDao;
	}

	public void setPriorityHeadDxo(PriorityHeadDxo priorityHeadDxo) {
		this.priorityHeadDxo = priorityHeadDxo;
	}

}
