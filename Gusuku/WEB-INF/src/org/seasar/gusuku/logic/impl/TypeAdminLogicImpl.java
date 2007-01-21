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
import org.seasar.gusuku.dao.TypeDao;
import org.seasar.gusuku.dto.TypeAdminDto;
import org.seasar.gusuku.dxo.TypeDxo;
import org.seasar.gusuku.entity.Type;
import org.seasar.gusuku.logic.TypeAdminLogic;
import org.seasar.gusuku.util.FileUploadUtil;

import com.opensymphony.webwork.ServletActionContext;

public class TypeAdminLogicImpl implements TypeAdminLogic {

	private TypeDao typeDao;
	private TypeDxo typeDxo;

	@Aspect("j2ee.requiredTx")
	public void registration(TypeAdminDto typeAdminDto) {
		Type type = typeDxo.convert(typeAdminDto);
		
		if(typeAdminDto.isNoiconflag()){
			type.setIcon(""); 
		}
		
		if(StringUtil.isEmpty(typeAdminDto.getId())){
			typeDao.insert(type);
		}else{
			type.setUdate(new Date());
			if(!typeAdminDto.isNoiconflag()){
				Type org = typeDao.findById(typeAdminDto.getId());
				type.setIcon(org.getIcon());
			}
			typeDao.update(type);
		}
		
		//ファイル保存
		File file = typeAdminDto.getIcon();

		if(!typeAdminDto.isNoiconflag() && file != null){
			String uploaddir = ServletActionContext.getServletContext().getRealPath(GusukuConstant.IMAGE_DIR);
			String orgFileName = typeAdminDto.getIconFileName();
			String extension = orgFileName.substring(orgFileName.indexOf("."));
			String fileName = "type_" + type.getId() + extension;
			
			FileUploadUtil.save(file,uploaddir,fileName);
			
			type.setIcon(fileName);
			typeDao.update(type);
		}
	}

	public void delete(String[] delids) {
		if (delids != null && delids.length > 0) {
			typeDao.updateDelflag(delids);
		}

	}

	public TypeAdminDto getTypeAdminDto(TypeAdminDto typeAdminDto) {
		Type type = typeDao.findById(typeAdminDto.getId());
		TypeAdminDto typeDto = typeDxo.convert(type);
		if(StringUtil.isEmpty(type.getIcon())){
			typeDto.setNoiconflag(true);
		}
		return typeDto;
	}

	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setTypeDxo(TypeDxo typeDxo) {
		this.typeDxo = typeDxo;
	}

}
