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
import org.seasar.gusuku.dto.TypeAdminDto;
import org.seasar.gusuku.entity.Type;

@S2Dao(bean=Type.class)
public interface TypeDao {

	@NoPersistentProperty("rdate")
	public void insert(Type type);
	@NoPersistentProperty("rdate")
	public void update(Type type);
	public void delete(Type type);
	
	public List<Type> findByDto(TypeAdminDto dto);
	
	@Query("DELFLAG = FALSE ORDER BY ID")
	public List<Type> findAll();

	@Query("ID = /*id*/ AND DELFLAG = FALSE")
	public Type findById(String id);
	
	public void updateDelflag(String[] ids);
	
	@Query("ID NOT IN (SELECT TYPEID FROM TYPE_SCHEME WHERE HEADID= /*headid*/) AND DELFLAG = FALSE ORDER BY ID")
	public List<Type> findWithoutSchemeByHeadid(String headid);
	
}
