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
import org.seasar.gusuku.dao.CustomFormDetailDao;
import org.seasar.gusuku.dto.CustomFormDetailAdminDto;
import org.seasar.gusuku.dxo.CustomFormDetailDxo;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.logic.CustomFormDetailAdminLogic;

public class CustomFormDetailAdminLogicImpl implements CustomFormDetailAdminLogic{

	private CustomFormDetailDao customFormDetailDao;
	private CustomFormDetailDxo customFormDetailDxo;
	
	public void delete(Long formheadid,Long[] delids) {
		if(delids != null && delids.length >0){
			customFormDetailDao.updateDelflag(delids);
			
			List<CustomFormDetail> list = customFormDetailDao.findByFormheadid(formheadid);
			int sort = 1;
			for(CustomFormDetail customFormDetail : list){
				if(customFormDetail.getSort() != sort){
					customFormDetail.setSort(sort++);
					customFormDetailDao.update(customFormDetail);
				}else{
					sort++;
				}
			}
		}
	}

	public CustomFormDetailAdminDto getCustomFormDetail(CustomFormDetailAdminDto customFormDetailAdminDto) {
		return customFormDetailDxo.convert(customFormDetailDao.findById(customFormDetailAdminDto.getId()));
	}
	
	public void registration(CustomFormDetailAdminDto customFormDetailAdminDto) {
		CustomFormDetail customFormDetail = customFormDetailDxo.convert(customFormDetailAdminDto);
		
		if(customFormDetailAdminDto.getId() == null){
			CustomFormDetail org = customFormDetailDao.findMaxSort(customFormDetailAdminDto.getFormheadid());
			int sortMax = 1;
			if(org != null){
				sortMax = org.getSort() + 1;
			}
			customFormDetail.setSort(sortMax);
			customFormDetailDao.insert(customFormDetail);
		}else{
			customFormDetailDao.update(customFormDetail);
		}
		
	}

	public void setCustomFormDetailDao(CustomFormDetailDao customFormDetailDao) {
		this.customFormDetailDao = customFormDetailDao;
	}

	public void setCustomFormDetailDxo(CustomFormDetailDxo customFormDetailDxo) {
		this.customFormDetailDxo = customFormDetailDxo;
	}

	@Aspect("j2ee.requiredTx")
	public void sortDown(Long id, Long formheadid) {
		
		CustomFormDetail target = customFormDetailDao.findById(id);
		CustomFormDetail after = customFormDetailDao.findAfterById(id,formheadid);
		
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		
		customFormDetailDao.update(target);
		customFormDetailDao.update(after);
		
	}

	@Aspect("j2ee.requiredTx")
	public void sortUp(Long id, Long formheadid) {
		CustomFormDetail target = customFormDetailDao.findById(id);
		CustomFormDetail before = customFormDetailDao.findBeforeById(id,formheadid);
		
		int orgSort = target.getSort();
		target.setSort(before.getSort());
		before.setSort(orgSort);
		
		customFormDetailDao.update(target);
		customFormDetailDao.update(before);
		
	}
	
	

}
