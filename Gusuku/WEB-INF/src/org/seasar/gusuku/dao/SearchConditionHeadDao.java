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
package org.seasar.gusuku.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.entity.SearchConditionHead;

@S2Dao(bean=SearchConditionHead.class)
public interface SearchConditionHeadDao {
	
	public void insert(SearchConditionHead searchConditionHead);
	public void update(SearchConditionHead searchConditionHead);
	public void delete(SearchConditionHead searchConditionHead);
	
	@Query("ID = /*id*/")
	public SearchConditionHead findById(Long id);
	
	@Query("ACCOUNTID = /*accountid*/ ORDER BY SORT")
	public List<SearchConditionHead> findByAccountid(Long accountid);
	
	@Query("SORT = (SELECT MAX(SORT) FROM SEARCH_CONDITION_HEAD WHERE ACCOUNTID = /*accountid*/)")
	public SearchConditionHead findMaxSort(Long accountid);
	
	public void updateBatch(SearchConditionHead[] updateList);
	
	@Query("ACCOUNTID = /*accountid*/ AND SORT < (SELECT SORT FROM SEARCH_CONDITION_HEAD WHERE ID = /*id*/) ORDER BY SORT DESC LIMIT 1")
	@Arguments({"id","accountid"})
	public SearchConditionHead findBeforeById(Long id,Long accountid);
	
	@Query("ACCOUNTID = /*accountid*/ AND SORT > (SELECT SORT FROM SEARCH_CONDITION_HEAD WHERE ID = /*id*/) ORDER BY SORT LIMIT 1")
	@Arguments({"id","accountid"})
	public SearchConditionHead findAfterById(Long id,Long accountid);
	
	@Query("ACCOUNTID = /*accountid*/ AND VISIBLE = TRUE ORDER BY SORT")
	public List<SearchConditionHead> findVisibleByAccountid(Long accountid);

}
