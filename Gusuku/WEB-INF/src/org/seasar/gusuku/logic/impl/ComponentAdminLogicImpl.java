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

import org.seasar.gusuku.dao.ComponentDao;
import org.seasar.gusuku.dto.ComponentAdminDto;
import org.seasar.gusuku.dxo.ComponentDxo;
import org.seasar.gusuku.entity.Component;
import org.seasar.gusuku.logic.ComponentAdminLogic;


public class ComponentAdminLogicImpl implements ComponentAdminLogic {
	
	private ComponentDao componentDao;
	private ComponentDxo componentDxo;

	public void registration(ComponentAdminDto componentAdminDto) {
		Component component = componentDxo.convert(componentAdminDto);
		
		if(component.getId() == null){
			componentDao.insert(component);
		}else{
			component.setUdate(new Date());
			componentDao.update(component);
		}

	}

	public void delete( Long[] delids) {
		if(delids != null && delids.length > 0){
			componentDao.updateDelflag(delids);
		}
	}

	public ComponentAdminDto getComponent(ComponentAdminDto componentAdminDto) {
		Component component = componentDao.findById(componentAdminDto.getId());
		return componentDxo.convert(component);
	}

	
	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	
	public void setComponentDxo(ComponentDxo componentDxo) {
		this.componentDxo = componentDxo;
	}

}
