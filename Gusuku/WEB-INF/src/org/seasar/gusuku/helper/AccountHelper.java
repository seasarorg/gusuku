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

import org.seasar.gusuku.dao.AccountDao;
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.entity.Account;

/**
 * アカウントに関するHelperクラス
 * 
 * @author duran
 */
public class AccountHelper {

	private AccountDao accountDao;

	/**
	 * アカウント一覧を取得する
	 * 
	 * @return アカウント一覧
	 */
	public List<Account> getAccountList() {
		return accountDao.findAll();
	}

	/**
	 * プロジェクトに参加しているアカウント一覧を取得する
	 * 
	 * @param projectid 対象プロジェクトID
	 * @return アカウント一覧
	 */
	public List<Account> getProjectAccountList(Long projectid) {
		return accountDao.findByProjectid(projectid);
	}

	/**
	 * 所属しているグループのアカウント一覧を取得する
	 * 
	 * @param groupid 対象グループID
	 * @return アカウント一覧
	 */
	public List<Account> getGroupmemberList(Long groupid) {
		return accountDao.findByGroupid(groupid);
	}

	/**
	 * 所属しているグループに属していないアカウント一覧を取得する
	 * 
	 * @param groupid 対象グループID
	 * @return アカウント一覧
	 */
	public List<Account> getWithoutsGroupmemberList(Long groupid) {
		return accountDao.findByWithoutGroupid(groupid);
	}
	
	public boolean isExistMailaddr(Long accountid,String mailaddr){
		Account account = accountDao.findByMailaddr(accountid,mailaddr);
		if(account == null){
			return false;
		}
		return true;
	}

	/**
	 * DI AccountDao
	 * 
	 * @param accountDao
	 */
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public List<Account> getAccountList(AccountAdminDto dto) {
		return accountDao.findByDto(dto);
	}
}
