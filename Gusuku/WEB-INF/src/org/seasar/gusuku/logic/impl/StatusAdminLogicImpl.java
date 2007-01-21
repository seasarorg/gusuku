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
import org.seasar.gusuku.dao.StatusDao;
import org.seasar.gusuku.dto.StatusAdminDto;
import org.seasar.gusuku.dxo.StatusDxo;
import org.seasar.gusuku.entity.Status;
import org.seasar.gusuku.logic.StatusAdminLogic;
import org.seasar.gusuku.util.FileUploadUtil;

import com.opensymphony.webwork.ServletActionContext;

public class StatusAdminLogicImpl implements StatusAdminLogic {

	private StatusDao statusDao;

	private StatusDxo statusDxo;

	@Aspect("j2ee.requiredTx")
	public void registration(StatusAdminDto statusAdminDto) {
		Status status = statusDxo.convert(statusAdminDto);

		//アイコンを使用しない場合
		if(statusAdminDto.isNoiconflag()){
			status.setIcon("");
		}
		if (StringUtil.isEmpty(statusAdminDto.getId())) {
			statusDao.insert(status);
		} else {
			status.setUdate(new Date());
			if(!statusAdminDto.isNoiconflag()){
				//元ファイル名取得
				Status org = statusDao.findById(statusAdminDto.getId());
				status.setIcon(org.getIcon());
			}
			statusDao.update(status);
		}
		
		//ファイル保存
		File file = statusAdminDto.getIcon();

		if(!statusAdminDto.isNoiconflag() && file != null){
			String uploaddir = ServletActionContext.getServletContext().getRealPath(GusukuConstant.IMAGE_DIR);
			String orgFileName = statusAdminDto.getIconFileName();
			String extension = orgFileName.substring(orgFileName.indexOf("."));
			String fileName = "status_" + status.getId() + extension;
			
			FileUploadUtil.save(file,uploaddir,fileName);
			
			status.setIcon(fileName);
			statusDao.update(status);
		}

	}

	public void delete(String[] ids) {
		if(ids != null && ids.length > 0){
			statusDao.updateDelflag(ids);
		}
	}

	public StatusAdminDto getStatus(StatusAdminDto statusAdminDto) {
		Status status = statusDao.findById(statusAdminDto.getId());
		StatusAdminDto statusDto = statusDxo.convert(status); 
		if(StringUtil.isEmpty(status.getIcon())){
			statusDto.setNoiconflag(true);
		}
		return statusDto;
	}

	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	public void setStatusDxo(StatusDxo statusDxo) {
		this.statusDxo = statusDxo;
	}

}
