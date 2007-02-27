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
package org.seasar.gusuku.logic;

import java.util.List;
import java.util.Map;

import org.seasar.gusuku.dto.SearchDto;
import org.seasar.gusuku.entity.SearchConditionHead;

public interface SearchLogic {
	
	/**
	 * 検索条件を保存します
	 * @param parameters
	 * @param accountid
	 */
	public void saveCondition(Map<String,String[]> parameters,Long accountid);
	
	/**
	 * 条件に従って検索を行います
	 * @param parameters
	 * @param accountid
	 * @return
	 */
	public List search(SearchDto searchDto);
	public SearchDto makeCondition(SearchConditionHead searchConditionHead,Map<String,String[]> parameters,Long accountid);
	/**
	 * 
	 *
	 */
	public void load(Map<String,String[]> parameters,Long conditionid);
	public void load(Map<String,String[]> parameters,SearchDto searchDto);
	
	/**
	 * 検索条件を削除します
	 * @param ids 削除対象ID
	 */
	public void delete(Long accountid,Long[] delids);

	/**
	 * 検索条件を更新します<br>
	 * 更新内容　表示フラグ　表示件数
	 * @param visible 表示設定を行うIDの配列
	 * @param amount 表示件数のマップ ID:件数
	 */
	public void update(Long[] visible, Map amount);
	
	
	public void sortUp(Long id,Long accountid);
	public void sortDown(Long id,Long accountid);

}
