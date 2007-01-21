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

import org.seasar.gusuku.dao.TypeDao;
import org.seasar.gusuku.dao.TypeHeadDao;
import org.seasar.gusuku.dao.TypeSchemeDao;
import org.seasar.gusuku.dto.TypeAdminDto;
import org.seasar.gusuku.dto.TypeHeadAdminDto;
import org.seasar.gusuku.entity.Type;
import org.seasar.gusuku.entity.TypeHead;
import org.seasar.gusuku.entity.TypeScheme;

/**
 * タイプに関するHelperクラス
 * @author duran
 *
 */
public class TypeHelper {
	
	private TypeDao typeDao;
	private TypeHeadDao typeHeadDao;
	private TypeSchemeDao typeSchemeDao;
	
	/**
	 * タイプ一覧を取得する
	 * @return タイプ一覧
	 */
	public List<Type> getTypeList(){
		return typeDao.findAll();
	}
	
	/**
	 * タイプスキーマ一覧を取得する
	 * @return
	 */
	public List<TypeHead> getTypeHeadList(){
		return typeHeadDao.findAll();
	}
	
	/**
	 * 指定したスキーマで使用されていないタイプ一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<Type> getTypeListWithoutScheme(String headid){
		return typeDao.findWithoutSchemeByHeadid(headid);
	}
	/**
	 * 指定したスキーマで使用されているタイプ一覧を取得する
	 * @param headid
	 * @return
	 */
	public List<TypeScheme> getTypeListWithScheme(String headid){
		return typeSchemeDao.findWithSchemeByHeadid(headid);
	}

	/**
	 * 指定したタイプIDに従うタイプを取得する
	 * @param id タイプID
	 * @return タイプ
	 */
	public Type getType(String id){
		return typeDao.findById(id);
	}
	
	/**
	 * 指定したヘッダIDに従うタイプヘッダを取得する
	 * @param id
	 * @return
	 */
	public TypeHead getTypeHead(String id){
		return typeHeadDao.findById(id);
	}
	
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setTypeHeadDao(TypeHeadDao typeHeadDao) {
		this.typeHeadDao = typeHeadDao;
	}

	
	public void setTypeSchemeDao(TypeSchemeDao typeSchemeDao) {
		this.typeSchemeDao = typeSchemeDao;
	}

	public List<Type> getTypeList(TypeAdminDto dto) {
		return typeDao.findByDto(dto);
	}

	public List<TypeHead> getTypeHeadList(TypeHeadAdminDto dto) {
		return typeHeadDao.findByDto(dto);
	}
	
}
