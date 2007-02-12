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

import java.util.List;

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.gusuku.dao.PrioritySchemeDao;
import org.seasar.gusuku.dto.PrioritySchemeAdminDto;
import org.seasar.gusuku.entity.PriorityScheme;
import org.seasar.gusuku.logic.PrioritySchemeAdminLogic;

public class PrioritySchemeAdminLogicImpl implements PrioritySchemeAdminLogic {

	private PrioritySchemeDao prioritySchemeDao;

	@Aspect("j2ee.requiredTx")
	public void registration(PrioritySchemeAdminDto prioritySchemeAdminDto) {
		Long headid = prioritySchemeAdminDto.getHeadid();
		Long[] priorityids = prioritySchemeAdminDto.getPriorityid();
		if (headid != null && priorityids != null
				&& priorityids.length > 0) {
			
			PriorityScheme org = prioritySchemeDao.findMaxSort(headid);
			
			int maxSort = 1;
			if(org != null){
				maxSort = org.getSort() + 1;
			}
			for (Long priorityid : priorityids) {
				PriorityScheme priorityScheme = new PriorityScheme();
				priorityScheme.setHeadid(headid);
				priorityScheme.setPriorityid(priorityid);
				priorityScheme.setSort(maxSort++);
				prioritySchemeDao.insert(priorityScheme);
			}
		}

	}

	@Aspect("j2ee.requiredTx")
	public void delete(Long[] delids,Long headid) {
		if (delids != null && delids.length > 0) {
			for (Long delid : delids) {
				PriorityScheme priorityScheme = new PriorityScheme();
				priorityScheme.setId(delid);
				prioritySchemeDao.delete(priorityScheme);
			}
			//ソート順序再設定
			List<PriorityScheme> list = prioritySchemeDao.findWithSchemeByHeadid(headid);
			int sort = 1;
			for(PriorityScheme priorityScheme : list){
				if(priorityScheme.getSort() != sort){
					priorityScheme.setSort(sort++);
					prioritySchemeDao.update(priorityScheme);
				}else{
					sort++;
				}
			}
		}

	}
	
	@Aspect("j2ee.requiredTx")
	public void sortDown(PrioritySchemeAdminDto prioritySchemeAdminDto) {
		PriorityScheme target = prioritySchemeDao.findById(prioritySchemeAdminDto.getId());
		PriorityScheme after = prioritySchemeDao.findAfterById(prioritySchemeAdminDto.getId(),prioritySchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		prioritySchemeDao.update(target);
		prioritySchemeDao.update(after);
	}
	
	@Aspect("j2ee.requiredTx")
	public void sortUp(PrioritySchemeAdminDto prioritySchemeAdminDto) {
		PriorityScheme target = prioritySchemeDao.findById(prioritySchemeAdminDto.getId());
		PriorityScheme after = prioritySchemeDao.findBeforeById(prioritySchemeAdminDto.getId(),prioritySchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		prioritySchemeDao.update(target);
		prioritySchemeDao.update(after);

	}

	
	public void setPrioritySchemeDao(PrioritySchemeDao prioritySchemeDao) {
		this.prioritySchemeDao = prioritySchemeDao;
	}

}
