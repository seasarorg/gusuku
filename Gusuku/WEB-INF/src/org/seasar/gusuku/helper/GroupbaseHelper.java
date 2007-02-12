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

import org.seasar.gusuku.dao.GroupbaseDao;
import org.seasar.gusuku.dto.GroupbaseAdminDto;
import org.seasar.gusuku.entity.Groupbase;

/**
 * グループに関するHelperクラス
 * @author duran
 *
 */
public class GroupbaseHelper {
	
	private GroupbaseDao groupbaseDao;
	
	/**
	 * グループを取得する
	 * @param id グループID
	 * @return
	 */
	public Groupbase getGroupbase(Long id){
		return groupbaseDao.findById(id);
	}
	
	/**
	 * グループ一覧を取得する
	 * @return グループ一覧
	 */
	public List<Groupbase> getGroupbaseList(){
		return groupbaseDao.findAll();
	}
	
	/**
	 * 検索条件に従ってグループ一覧を取得する
	 * @param groupbaseAdminDto 検索条件
	 * @return グループ一覧
	 */
	public List<Groupbase> getGroupbaseList(GroupbaseAdminDto groupbaseAdminDto){
		return groupbaseDao.findByDto(groupbaseAdminDto);
	}

	/**
	 * 指定したプロジェクトに関連づいたグループ一覧を取得する
	 * @param projectid 対象プロジェクトID
	 * @return グループ一覧
	 */
	public List<Groupbase> getGroupList(Long projectid) {
		return groupbaseDao.findByProjectid(projectid);
	}

	/**
	 * 指定したグループと関連のないグループ一覧を取得する
	 * @param projectid 対象プロジェクトID
	 * @return グループ一覧
	 */
	public List<Groupbase> getWithoutsGroupList(Long projectid) {
		return groupbaseDao.findByWithoutProjectid(projectid);
	}
	
	/**
	 * 指定したユーザーが所属するグループ一覧を取得する
	 * @param accountid 対象アカウントID
	 * @return グループ一覧
	 */
	public List<Groupbase> getJoinGroupList(Long accountid){
		return groupbaseDao.findByAccountid(accountid);
	}

	public void setGroupbaseDao(GroupbaseDao groupbaseDao) {
		this.groupbaseDao = groupbaseDao;
	}
}
