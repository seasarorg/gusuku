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

import org.seasar.gusuku.dao.ResolutionDao;
import org.seasar.gusuku.dao.ResolutionHeadDao;
import org.seasar.gusuku.dao.ResolutionSchemeDao;
import org.seasar.gusuku.dto.ResolutionAdminDto;
import org.seasar.gusuku.dto.ResolutionHeadAdminDto;
import org.seasar.gusuku.entity.Resolution;
import org.seasar.gusuku.entity.ResolutionHead;
import org.seasar.gusuku.entity.ResolutionScheme;

/**
 * 完了理由に関するHelperクラス
 * @author duran
 *
 */
public class ResolutionHelper {

	private ResolutionDao resolutionDao;
	private ResolutionHeadDao resolutionHeadDao;
	private ResolutionSchemeDao resolutionSchemeDao;
	
	/**
	 * 完了理由一覧を取得する
	 * @return 完了理由一覧
	 */
	public List<Resolution> getResolutionList(){
		return resolutionDao.findAll();
	}
	
	/**
	 * 完了理由スキーマ一覧を取得する
	 * @return
	 */
	public List<ResolutionHead> getResolutionHeadList(){
		return resolutionHeadDao.findAll();
	}
	
	/**
	 * 指定したスキーマで使用されていない完了理由一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<Resolution> getResolutionListWithoutScheme(Long headid){
		return resolutionDao.findWithoutSchemeByHeadid(headid);
	}
	/**
	 * 指定したスキーマで使用されている完了理由一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<ResolutionScheme> getResolutionListWithScheme(Long headid){
		return resolutionSchemeDao.findWithSchemeByHeadid(headid);
	}
	
	public ResolutionHead getResolutionHead(Long id){
		return resolutionHeadDao.findById(id);
	}
	
	public void setResolutionDao(ResolutionDao resolutionDao) {
		this.resolutionDao = resolutionDao;
	}

	
	public void setResolutionHeadDao(ResolutionHeadDao resolutionHeadDao) {
		this.resolutionHeadDao = resolutionHeadDao;
	}

	
	public void setResolutionSchemeDao(ResolutionSchemeDao resolutionSchemeDao) {
		this.resolutionSchemeDao = resolutionSchemeDao;
	}

	public List<Resolution> getResolutionList(ResolutionAdminDto dto) {
		return resolutionDao.findByDto(dto);
	}

	public List<ResolutionHead> getResolutionHeadList(ResolutionHeadAdminDto dto) {
		return resolutionHeadDao.findByDto(dto);
	}
	
	
}
