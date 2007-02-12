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
import org.seasar.gusuku.dao.CustomValueDetailDao;
import org.seasar.gusuku.dto.CustomValueDetailAdminDto;
import org.seasar.gusuku.dxo.CustomValueDetailDxo;
import org.seasar.gusuku.entity.CustomValueDetail;
import org.seasar.gusuku.logic.CustomValueDetailAdminLogic;

public class CustomValueDetailAdminLogicImpl implements CustomValueDetailAdminLogic{

	private CustomValueDetailDao customValueDetailDao;
	private CustomValueDetailDxo customValueDetailDxo;
	
	@Aspect("j2ee.requiredTx")
	public void delete(Long[] delids,Long valueheadid) {
		if(delids != null && delids.length >0){
			//TODO 物理削除へ変更
			for(Long delid : delids){
				customValueDetailDao.deleteById(delid);
			}
			
			List<CustomValueDetail> list = customValueDetailDao.findByValueheadid(valueheadid);
			int sort = 1;
			for(CustomValueDetail customValueDetail : list){
				if(customValueDetail.getSort() != sort){
					customValueDetail.setSort(sort);
					customValueDetailDao.update(customValueDetail);
				}else{
					sort++;
				}
			}
		}
	}
	
	public void registration(CustomValueDetailAdminDto customValueDetailAdminDto) {
		CustomValueDetail customValueDetail = customValueDetailDxo.convert(customValueDetailAdminDto);
		
		if(customValueDetailAdminDto.getId() == null){
			CustomValueDetail org = customValueDetailDao.findMaxSort(customValueDetail.getValueheadid());
			int maxSort = 1;
			if(org != null){
				maxSort = org.getSort() + 1;
			}
			customValueDetail.setSort(maxSort);
			customValueDetailDao.insert(customValueDetail);
		}else{
			customValueDetailDao.update(customValueDetail);
		}
		
	}

	@Aspect("j2ee.requiredTx")
	public void sortDown(Long id, Long valueheadid) {
		
		CustomValueDetail target = customValueDetailDao.findById(id);
		CustomValueDetail after = customValueDetailDao.findAfterById(id,valueheadid);
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		customValueDetailDao.update(target);
		customValueDetailDao.update(after);
		
	}

	@Aspect("j2ee.requiredTx")
	public void sortUp(Long id, Long valueheadid) {
		CustomValueDetail target = customValueDetailDao.findById(id);
		CustomValueDetail before = customValueDetailDao.findBeforeById(id,valueheadid);
		
		int orgSort = target.getSort();
		target.setSort(before.getSort());
		before.setSort(orgSort);
		
		customValueDetailDao.update(target);
		customValueDetailDao.update(before);
		
	}
	
	public void setCustomValueDetailDao(CustomValueDetailDao customValueDetailDao) {
		this.customValueDetailDao = customValueDetailDao;
	}

	public void setCustomValueDetailDxo(CustomValueDetailDxo customValueDetailDxo) {
		this.customValueDetailDxo = customValueDetailDxo;
	}
	
	

}
