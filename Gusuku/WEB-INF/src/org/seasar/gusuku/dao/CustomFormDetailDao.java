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
import org.seasar.gusuku.entity.CustomFormDetail;

@S2Dao(bean=CustomFormDetail.class)
public interface CustomFormDetailDao {

	public void insert(CustomFormDetail customFormDetail);
	public void update(CustomFormDetail customFormDetail);
	public void delete(CustomFormDetail customFormDetail);
	
	@Query("CUSTOM_FORM_DETAIL.FORMHEADID = /*formheadid*/ AND CUSTOM_FORM_DETAIL.DELFLAG = FALSE ORDER BY CUSTOM_FORM_DETAIL.SORT")
	public List<CustomFormDetail> findByFormheadid(String formheadid);
	
	public void updateDelflag(String[] ids);
	
	@Query("CUSTOM_FORM_DETAIL.ID = /*id*/ AND CUSTOM_FORM_DETAIL.DELFLAG = FALSE")
	public CustomFormDetail findById(String id);
	
	@Query("FORMHEADID = /*formheadid*/ AND SORT > (SELECT SORT FROM CUSTOM_FORM_DETAIL WHERE CUSTOM_FORM_DETAIL.ID = /*id*/) ORDER BY SORT LIMIT 1")
	@Arguments({"id","formheadid"})
	public CustomFormDetail findAfterById(String id,String formheadid);
	
	@Query("FORMHEADID = /*formheadid*/ AND SORT < (SELECT SORT FROM CUSTOM_FORM_DETAIL WHERE CUSTOM_FORM_DETAIL.ID = /*id*/) ORDER BY SORT DESC LIMIT 1")
	@Arguments({"id","formheadid"})
	public CustomFormDetail findBeforeById(String id,String formheadid);
	
	@Query("SORT = (SELECT MAX(SORT) FROM CUSTOM_FORM_DETAIL WHERE CUSTOM_FORM_DETAIL.FORMHEADID = /*formheadid*/)")
	public CustomFormDetail findMaxSort(String formheadid);
}
