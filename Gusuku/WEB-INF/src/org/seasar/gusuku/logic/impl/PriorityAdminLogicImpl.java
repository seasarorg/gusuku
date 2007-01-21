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

import java.io.File;
import java.util.Date;

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.PriorityDao;
import org.seasar.gusuku.dto.PriorityAdminDto;
import org.seasar.gusuku.dxo.PriorityDxo;
import org.seasar.gusuku.entity.Priority;
import org.seasar.gusuku.logic.PriorityAdminLogic;
import org.seasar.gusuku.util.FileUploadUtil;

import com.opensymphony.webwork.ServletActionContext;

public class PriorityAdminLogicImpl implements PriorityAdminLogic {

	private PriorityDao priorityDao;
	private PriorityDxo priorityDxo;

	@Aspect("j2ee.requiredTx")
	public void registration(PriorityAdminDto priorityAdminDto) {
		Priority priority = priorityDxo.convert(priorityAdminDto);
		
		//アイコンを使用しない
		if(priorityAdminDto.isNoiconflag()){
			priority.setIcon("");
		}
		if(StringUtil.isEmpty(priorityAdminDto.getId())){
			priorityDao.insert(priority);
		}else{
			priority.setUdate(new Date());
			if(!priorityAdminDto.isNoiconflag()){
				Priority org = priorityDao.findById(priorityAdminDto.getId());
				priority.setIcon(org.getIcon());
			}
				
			priorityDao.update(priority);
		}
		
		// ファイル保存
		File file = priorityAdminDto.getIcon();

		if(!priorityAdminDto.isNoiconflag() && file != null){
			String uploaddir = ServletActionContext.getServletContext().getRealPath(GusukuConstant.IMAGE_DIR);
			String orgFileName = priorityAdminDto.getIconFileName();
			String extension = orgFileName.substring(orgFileName.indexOf("."));
			String fileName = "priority_" + priority.getId() + extension;
			
			FileUploadUtil.save(file,uploaddir,fileName);
			
			priority.setIcon(fileName);
			priorityDao.update(priority);
		}
	}

	public void delete(String[] delids) {
		if (delids != null && delids.length > 0) {
			priorityDao.updateDelflag(delids);
		}

	}

	public PriorityAdminDto getPriorityAdminDto(PriorityAdminDto priorityAdminDto) {
		Priority priority = priorityDao.findById(priorityAdminDto.getId());
		PriorityAdminDto priorityDto = priorityDxo.convert(priority);
		if(StringUtil.isEmpty(priority.getIcon())){
			priorityDto.setNoiconflag(true);
		}
		return priorityDto;
	}

	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	public void setPriorityDxo(PriorityDxo priorityDxo) {
		this.priorityDxo = priorityDxo;
	}

}
