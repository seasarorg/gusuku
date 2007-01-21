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

import org.seasar.gusuku.dao.CustomValueDetailDao;
import org.seasar.gusuku.dao.CustomValueHeadDao;
import org.seasar.gusuku.dto.CustomValueHeadAdminDto;
import org.seasar.gusuku.entity.CustomValueDetail;
import org.seasar.gusuku.entity.CustomValueHead;

/**
 * カスタムバリューに関するHelperクラス
 * 
 * @author duran
 */
public class CustomValueHelper {

	private CustomValueHeadDao customValueHeadDao;

	private CustomValueDetailDao customValueDetailDao;

	/**
	 * 指定したカスタムバリューの値一覧を取得する
	 * @param valueheadid カスタムバリューID
	 * @return 値一覧
	 */
	public List<CustomValueDetail> getValueList(String valueheadid) {
		return customValueDetailDao.findByValueheadid(valueheadid);
	}

	/**
	 * 検索条件に従ってカスタムバリュー一覧を取得する
	 * @param customValueHeadAdminDto 検索条件
	 * @return カスタムバリュー一覧
	 */
	public List<CustomValueHead> getCustomValueHeadList(
			CustomValueHeadAdminDto customValueHeadAdminDto) {
		return customValueHeadDao.findByDto(customValueHeadAdminDto);
	}

	/**
	 * カスタムバリュー一覧を取得する
	 * @return カスタムバリュー一覧
	 */
	public List<CustomValueHead> getCustomValueHeadList() {
		return customValueHeadDao.findAll();
	}
	
	/**
	 * カスタムバリューを取得する
	 * @param valueheadid カスタムバリューID
	 * @return
	 */
	public CustomValueHead getCustomValueHead(String valueheadid){
		return customValueHeadDao.findById(valueheadid);
	}

	public void setCustomValueDetailDao(
			CustomValueDetailDao customValueDetailDao) {
		this.customValueDetailDao = customValueDetailDao;
	}

	public void setCustomValueHeadDao(CustomValueHeadDao customValueHeadDao) {
		this.customValueHeadDao = customValueHeadDao;
	}

}
