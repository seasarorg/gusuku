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

import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.dto.TypeHeadAdminDto;
import org.seasar.gusuku.entity.TypeHead;

@S2Dao(bean = TypeHead.class)
public interface TypeHeadDao {
	
	@NoPersistentProperty("rdate")
	public void insert(TypeHead typeHead);
	@NoPersistentProperty("rdate")
	public void update(TypeHead typeHead);
	public void delete(TypeHead typeHead);
	
	public List<TypeHead> findByDto(TypeHeadAdminDto dto);

	@Query("DELFLAG = FALSE ORDER BY ID")
	public List<TypeHead> findAll();
	
	@Query("ID = /*id*/ AND DELFLAG = FALSE")
	public TypeHead findById(String id);

	public void updateDelflag(String[] ids);
}
