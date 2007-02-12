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
import org.seasar.gusuku.dao.TypeSchemeDao;
import org.seasar.gusuku.dto.TypeSchemeAdminDto;
import org.seasar.gusuku.entity.TypeScheme;
import org.seasar.gusuku.logic.TypeSchemeAdminLogic;

public class TypeSchemeAdminLogicImpl implements TypeSchemeAdminLogic {

	private TypeSchemeDao typeSchemeDao;

	@Aspect("j2ee.requiredTx")
	public void registration(TypeSchemeAdminDto typeSchemeAdminDto) {
		Long headid = typeSchemeAdminDto.getHeadid();
		Long[] typeids = typeSchemeAdminDto.getTypeid();
		if (headid != null && typeids != null
				&& typeids.length > 0) {
			
			TypeScheme org = typeSchemeDao.findMaxSort(headid);
			
			int maxSort = 1;
			if(org != null){
				maxSort = org.getSort() + 1;
			}
			for (Long typeid : typeids) {
				TypeScheme typeScheme = new TypeScheme();
				typeScheme.setHeadid(headid);
				typeScheme.setTypeid(typeid);
				typeScheme.setSort(maxSort++);
				typeSchemeDao.insert(typeScheme);
			}
		}

	}

	@Aspect("j2ee.requiredTx")
	public void delete(Long[] delids,Long headid) {
		if (delids != null && delids.length > 0) {
			for (Long delid : delids) {
				TypeScheme typeScheme = new TypeScheme();
				typeScheme.setId(delid);
				typeSchemeDao.delete(typeScheme);
			}
			
			//ソート順序再設定
			List<TypeScheme> list = typeSchemeDao.findWithSchemeByHeadid(headid);
			int sort = 1;
			for(TypeScheme typeScheme: list){
				if(typeScheme.getSort() != sort){
					typeScheme.setSort(sort++);
					typeSchemeDao.update(typeScheme);
				}else{
					sort++;
				}
			}
		}

	}
	
	@Aspect("j2ee.requiredTx")
	public void sortDown(TypeSchemeAdminDto typeSchemeAdminDto) {
		TypeScheme target = typeSchemeDao.findById(typeSchemeAdminDto.getId());
		TypeScheme after = typeSchemeDao.findAfterById(typeSchemeAdminDto.getId(),typeSchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		typeSchemeDao.update(target);
		typeSchemeDao.update(after);
	}
	
	@Aspect("j2ee.requiredTx")
	public void sortUp(TypeSchemeAdminDto typeSchemeAdminDto) {
		TypeScheme target = typeSchemeDao.findById(typeSchemeAdminDto.getId());
		TypeScheme after = typeSchemeDao.findBeforeById(typeSchemeAdminDto.getId(),typeSchemeAdminDto.getHeadid());
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		typeSchemeDao.update(target);
		typeSchemeDao.update(after);

	}

	
	public void setTypeSchemeDao(TypeSchemeDao typeSchemeDao) {
		this.typeSchemeDao = typeSchemeDao;
	}

}
