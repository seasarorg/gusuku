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

import org.seasar.gusuku.dao.SearchConditionBasicDao;
import org.seasar.gusuku.dao.SearchConditionCustomDao;
import org.seasar.gusuku.dao.SearchConditionHeadDao;
import org.seasar.gusuku.entity.SearchConditionBasic;
import org.seasar.gusuku.entity.SearchConditionCustom;
import org.seasar.gusuku.entity.SearchConditionHead;

/**
 * 検索条件に関するHelperクラス
 * @author duran
 *
 */
public class SearchConditionHelper {
	
	private SearchConditionHeadDao searchConditionHeadDao;
	private SearchConditionBasicDao searchConditionBasicDao;
	private SearchConditionCustomDao searchConditionCustomDao;
	
	/**
	 * IDにひもづく検索条件を取得します
	 * @param id
	 * @return
	 */
	public SearchConditionHead getSearchCondition(String id){
		return searchConditionHeadDao.findById(id);
	}
	
	/**
	 * ACCOUNTIDにひもづく検索条件を取得します
	 * @param accountid
	 * @return
	 */
	public List<SearchConditionHead> getSearchConditionHead(String accountid){
		return searchConditionHeadDao.findByAccountid(accountid);
	}
	
	/**
	 * CONDITIONHEADIDにひもづく基本検索条件を取得します
	 * @param conditionheadid
	 * @return
	 */
	public List<SearchConditionBasic> getSearchConditionBasic(String conditionheadid){
		return searchConditionBasicDao.findByConditionheadid(conditionheadid);
	}
	
	/**
	 * CONDITIONBASICIDにひもづく拡張検索条件を取得します
	 * @param conditionbasicid
	 * @return
	 */
	public List<SearchConditionCustom> getSearchConditionCustom(String conditionbasicid){
		return searchConditionCustomDao.findByConditionbasicid(conditionbasicid);
	}
	
	public List<SearchConditionHead> getVisibleSearchCondition(String accountid){
		return searchConditionHeadDao.findVisibleByAccountid(accountid);
	}
	
	public void setSearchConditionBasicDao(
			SearchConditionBasicDao searchConditionBasicDao) {
		this.searchConditionBasicDao = searchConditionBasicDao;
	}
	public void setSearchConditionCustomDao(
			SearchConditionCustomDao searchConditionCustomDao) {
		this.searchConditionCustomDao = searchConditionCustomDao;
	}
	public void setSearchConditionHeadDao(
			SearchConditionHeadDao searchConditionHeadDao) {
		this.searchConditionHeadDao = searchConditionHeadDao;
	}
	
	

}
