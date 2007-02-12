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
package org.seasar.gusuku.helper;

import java.util.List;

import org.seasar.gusuku.dao.PriorityDao;
import org.seasar.gusuku.dao.PriorityHeadDao;
import org.seasar.gusuku.dao.PrioritySchemeDao;
import org.seasar.gusuku.dto.PriorityAdminDto;
import org.seasar.gusuku.dto.PriorityHeadAdminDto;
import org.seasar.gusuku.entity.Priority;
import org.seasar.gusuku.entity.PriorityHead;
import org.seasar.gusuku.entity.PriorityScheme;

/**
 * 優先度に関するHelperクラス
 * @author duran
 *
 */
public class PriorityHelper {

	private PriorityDao priorityDao;
	private PriorityHeadDao priorityHeadDao;
	private PrioritySchemeDao prioritySchemeDao;

	/**
	 * 優先度一覧を取得する
	 * @return 優先度一覧
	 */
	public List<Priority> getPriorityList(){
		return priorityDao.findAll();
	}
	
	/**
	 * 優先度スキーマ一覧を取得する
	 * @return
	 */
	public List<PriorityHead> getPriorityHeadList(){
		return priorityHeadDao.findAll();
	}
	
	/**
	 * 指定したスキーマで使用されていない優先度一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<Priority> getPriorityListWithoutScheme(Long headid){
		return priorityDao.findWithoutSchemeByHeadid(headid);
	}
	/**
	 * 指定したスキーマで使用されている優先度一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<PriorityScheme> getPriorityListWithScheme(Long headid){
		return prioritySchemeDao.findWithSchemeByHeadid(headid);
	}
	
	public PriorityHead getPriorityHead(Long id){
		return priorityHeadDao.findById(id);
	}
	
	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	
	public void setPriorityHeadDao(PriorityHeadDao priorityHeadDao) {
		this.priorityHeadDao = priorityHeadDao;
	}

	
	public void setPrioritySchemeDao(PrioritySchemeDao prioritySchemeDao) {
		this.prioritySchemeDao = prioritySchemeDao;
	}

	public List<Priority> getPriorityList(PriorityAdminDto dto) {
		return priorityDao.findByDto(dto);
	}

	public List<PriorityHead> getPriorityHeadList(PriorityHeadAdminDto dto) {
		return priorityHeadDao.findByDto(dto);
	}

}
