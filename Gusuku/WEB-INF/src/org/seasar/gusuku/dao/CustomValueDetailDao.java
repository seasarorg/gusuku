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
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.gusuku.entity.CustomValueDetail;

@S2Dao(bean=CustomValueDetail.class)
public interface CustomValueDetailDao {
	
	public void insert(CustomValueDetail customValueDetail);
	public void update(CustomValueDetail customValueDetail);
	public void delete(CustomValueDetail customValueDetail);
	
	/**
	 * valueheadidにひもづくCustomValueDetailリストを取得します
	 * @param valueheadid 対象となるvalueheadid
	 * @return
	 */
	@Query("VALUEHEADID = /*valueheadid*/ ORDER BY SORT")
	public List<CustomValueDetail> findByValueheadid(Long valueheadid);
	
	/**
	 * 削除フラグを更新します
	 * @param ids
	 */
	public void updateDelflag(Long[] delids);
	
	/**
	 * 指定したIDにひもづくCustomValueDetailを取得します
	 * @param id 対象となるid
	 * @return
	 */
	@Query("ID = /*id*/ ")
	public CustomValueDetail findById(Long id);
	
	/**
	 * 指定したvalueheadidの中で指定したidの一つ後ろのCustomValueDetailを取得します
	 * @param id
	 * @param valueheadid
	 * @return
	 */
	@Query("VALUEHEADID = /*valueheadid*/ AND SORT > (SELECT SORT FROM CUSTOM_VALUE_DETAIL WHERE CUSTOM_VALUE_DETAIL.ID = /*id*/) ORDER BY SORT LIMIT 1")
	@Arguments({"id","valueheadid"})
	public CustomValueDetail findAfterById(Long id, Long valueheadid);

	/**
	 * 指定したvalueheadidの中で指定したidの一つ前のCustomValueDetailを取得します
	 * @param id
	 * @param valueheadid
	 * @return
	 */
	@Query("VALUEHEADID = /*valueheadid*/ AND SORT < (SELECT SORT FROM CUSTOM_VALUE_DETAIL WHERE CUSTOM_VALUE_DETAIL.ID = /*id*/) ORDER BY SORT DESC LIMIT 1")
	@Arguments({"id","valueheadid"})
	public CustomValueDetail findBeforeById(Long id, Long valueheadid);
	
	/**
	 * 指定したvalueheadidの中でのsortの最大値を取得します
	 * @param valueheadid
	 * @return
	 */
	@Query("SORT = (SELECT MAX(SORT) FROM CUSTOM_VALUE_DETAIL WHERE CUSTOM_VALUE_DETAIL.VALUEHEADID = /*valueheadid*/)")
	public CustomValueDetail findMaxSort(Long valueheadid);
	
	@Sql("DELETE FROM CUSTOM_VALUE_DETAIL WHERE ID = /*id*/")
	public void deleteById(Long id);
	
}
