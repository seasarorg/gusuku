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

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.ResolutionSchemeDao;
import org.seasar.gusuku.dto.ResolutionSchemeAdminDto;
import org.seasar.gusuku.entity.ResolutionScheme;
import org.seasar.gusuku.logic.ResolutionSchemeAdminLogic;

public class ResolutionSchemeAdminLogicImpl implements ResolutionSchemeAdminLogic {

	private ResolutionSchemeDao resolutionSchemeDao;

	@Aspect("j2ee.requiredTx")
	public void registration(ResolutionSchemeAdminDto resolutionSchemeAdminDto) {
		String headid = resolutionSchemeAdminDto.getHeadid();
		String[] resolutionids = resolutionSchemeAdminDto.getResolutionid();
		if (!StringUtil.isEmpty(headid) && resolutionids != null
				&& resolutionids.length > 0) {
			
			ResolutionScheme org = resolutionSchemeDao.findMaxSort(headid);
			
			int maxSort = 1;
			if(org != null){
				maxSort = org.getSort() + 1;
			}
			for (String resolutionid : resolutionids) {
				ResolutionScheme resolutionScheme = new ResolutionScheme();
				resolutionScheme.setHeadid(Long.parseLong(headid));
				resolutionScheme.setResolutionid(Long.parseLong(resolutionid));
				resolutionScheme.setSort(maxSort++);
				resolutionSchemeDao.insert(resolutionScheme);
			}
		}

	}

	@Aspect("j2ee.requiredTx")
	public void delete(String[] delids) {
		if (delids != null && delids.length > 0) {
			for (String delid : delids) {
				ResolutionScheme resolutionScheme = new ResolutionScheme();
				resolutionScheme.setId(Long.parseLong(delid));
				resolutionSchemeDao.delete(resolutionScheme);
			}
		}

	}
	
	@Aspect("j2ee.requiredTx")
	public void sortDown(ResolutionSchemeAdminDto resolutionSchemeAdminDto) {
		ResolutionScheme target = resolutionSchemeDao.findById(resolutionSchemeAdminDto.getId());
		ResolutionScheme after = resolutionSchemeDao.findAfterById(resolutionSchemeAdminDto.getId(),resolutionSchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		resolutionSchemeDao.update(target);
		resolutionSchemeDao.update(after);
	}
	
	@Aspect("j2ee.requiredTx")
	public void sortUp(ResolutionSchemeAdminDto resolutionSchemeAdminDto) {
		ResolutionScheme target = resolutionSchemeDao.findById(resolutionSchemeAdminDto.getId());
		ResolutionScheme after = resolutionSchemeDao.findBeforeById(resolutionSchemeAdminDto.getId(),resolutionSchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		resolutionSchemeDao.update(target);
		resolutionSchemeDao.update(after);

	}

	
	public void setResolutionSchemeDao(ResolutionSchemeDao resolutionSchemeDao) {
		this.resolutionSchemeDao = resolutionSchemeDao;
	}

}
