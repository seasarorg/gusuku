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

import org.seasar.gusuku.dao.CustomFormDetailDao;
import org.seasar.gusuku.dao.CustomFormHeadDao;
import org.seasar.gusuku.dto.CustomFormHeadAdminDto;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.entity.CustomFormHead;

/**
 * カスタムフォームに関するHelperクラス
 * @author duran
 *
 */
public class CustomFormHelper {
	
	private CustomFormHeadDao customFormHeadDao;
	private CustomFormDetailDao customFormDetailDao;
	
	/**
	 * 指定したカスタムフォームのフォーム一覧を取得する
	 * @param formheadid 対象となるカスタムフォームID
	 * @return フォーム一覧
	 */
	public List<CustomFormDetail> getFormList(Long formheadid){
		return customFormDetailDao.findByFormheadid(formheadid);
	}
	
	/**
	 * 検索条件に従ってカスタムフォーム一覧を取得する
	 * @param customFormHeadAdminDto 検索条件
	 * @return カスタムフォーム一覧
	 */
	public List<CustomFormHead> getCustomFormHeadList(CustomFormHeadAdminDto customFormHeadAdminDto){
		return customFormHeadDao.findByDto(customFormHeadAdminDto);
	}
	
	/**
	 * カスタムフォーム一覧を取得する
	 * @return カスタムフォーム一覧
	 */
	public List<CustomFormHead> getCustomFormHeadList(){
		return customFormHeadDao.findAll();
	}
	
	/**
	 * カスタムフォームを取得する
	 * @param formheadid カスタムフォームID
	 * @return
	 */
	public CustomFormHead getCustomFormHead(Long formheadid){
		return customFormHeadDao.findById(formheadid);
	}

	public void setCustomFormDetailDao(CustomFormDetailDao customFormDetailDao) {
		this.customFormDetailDao = customFormDetailDao;
	}

	public void setCustomFormHeadDao(CustomFormHeadDao customFormHeadDao) {
		this.customFormHeadDao = customFormHeadDao;
	}

}
