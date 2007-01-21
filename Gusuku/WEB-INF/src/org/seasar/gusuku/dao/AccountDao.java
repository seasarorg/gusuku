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
import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.entity.Account;

@S2Dao(bean=Account.class)
public interface AccountDao {

	@NoPersistentProperty("rdate")
	public void insert(Account account);
	@NoPersistentProperty("rdate")
	public void update(Account account);
	public void delete(Account account);
	
	/**
	 * 検索条件に従ってAccountリストを取得します
	 * @param dto 検索条件
	 * @return
	 */
	public List<Account> findByDto(AccountAdminDto dto);
	
	/**
	 * 指定したIDにひもづくAccountを取得します
	 * @param id
	 * @return
	 */
	@Query("Account.ID = /*id*/ AND Account.DELFLAG = FALSE")
	public Account findById(String id);
	
	/**
	 * 指定したID以外で、指定したMAILADDRを持つAccountを取得します
	 * @param accountid
	 * @param mailaddr
	 * @return
	 */
	@Query(" Account.MAILADDR = /*mailaddr*/ /*IF accountid != null && accountid != ''*/AND Account.ID != /*accountid*/ /*END*/" )
	@Arguments({"accountid","mailaddr"})
	public Account findByMailaddr(String accountid,String mailaddr);
	
	/**
	 * 削除フラグを更新します
	 * @param delids
	 */
	public void updateDelflag(String[] delids);
	
	/**
	 * 全てのAccountリストを取得します
	 * @return
	 */
	@Query("Account.DELFLAG = FALSE ORDER BY Account.ID")
	public List<Account> findAll();
	
	/**
	 * 指定したプロジェクトに所属するAccountリストを取得します
	 * @param projectid
	 * @return
	 */
	public List<Account> findByProjectid(String projectid);
	
	/**
	 * 指定したグループに所属するAccountリストを取得します
	 * @param groupid
	 * @return
	 */
	public List<Account> findByGroupid(String groupid);
	
	/**
	 * 指定したグループに所属しないAccountリストを取得します
	 * @param groupid
	 * @return
	 */
	public List<Account> findByWithoutGroupid(String groupid);
	
	/**
	 * 指定したメールアドレスとパスワードにひもづくAccountを取得します
	 * @param mailaddr
	 * @param password
	 * @return
	 */
	@Query("Account.MAILADDR = /*mailaddr*/ AND Account.PASSWORD = /*password*/ AND Account.DELFLAG = FALSE")
	@Arguments({"mailaddr","password"})
	public Account findByMailaddrAndPassword(String mailaddr, String password);
	
}
