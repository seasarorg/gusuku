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
import org.seasar.gusuku.entity.TypeScheme;

@S2Dao(bean = TypeScheme.class)
public interface TypeSchemeDao {
	
	public void insert(TypeScheme typeScheme);
	public void update(TypeScheme typeScheme);
	public void delete(TypeScheme typeScheme);
	
	@Query("HEADID = /*headid*/ ORDER BY SORT")
	public List<TypeScheme> findWithSchemeByHeadid(Long headid);
	
	@Query("SORT = (SELECT MAX(SORT) FROM TYPE_SCHEME WHERE HEADID = /*headid*/) AND HEADID = /*headid*/")
	public TypeScheme findMaxSort(Long headid);
	
	@Query("TYPE_SCHEME.ID = /*id*/")
	public TypeScheme findById(Long id);
	
	@Query("HEADID = /*headid*/ AND SORT > (SELECT SORT FROM TYPE_SCHEME WHERE TYPE_SCHEME.ID = /*id*/) ORDER BY SORT LIMIT 1")
	@Arguments({"id","headid"})
	public TypeScheme findAfterById(Long id, Long headid);

	@Query("HEADID = /*headid*/ AND SORT < (SELECT SORT FROM TYPE_SCHEME WHERE TYPE_SCHEME.ID = /*id*/) ORDER BY SORT DESC LIMIT 1")
	@Arguments({"id","headid"})
	public TypeScheme findBeforeById(Long id, Long headid);
}
