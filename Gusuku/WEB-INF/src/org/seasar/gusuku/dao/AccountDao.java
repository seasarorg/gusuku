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
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.entity.Account;

@S2Dao(bean=Account.class)
public interface AccountDao {

	@NoPersistentProperty({"rdate","assignflag"})
	public void insert(Account account);
	@NoPersistentProperty({"rdate","assignflag"})
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
	public Account findById(Long id);
	
	/**
	 * 指定したID以外で、指定したMAILADDRを持つAccountを取得します
	 * @param accountid
	 * @param mailaddr
	 * @return
	 */
	@Query(" /*IF accountid != null*/ Account.ID != /*accountid*/ AND /*END*/ Account.MAILADDR = /*mailaddr*/ " )
	@Arguments({"accountid","mailaddr"})
	public Account findByMailaddr(Long accountid,String mailaddr);
	
	/**
	 * 削除フラグを更新します
	 * @param delids
	 */
	public void updateDelflag(Long[] delids);
	
	/**
	 * アサイン表示フラグを変更します
	 * @param accountId
	 * @param assingflag
	 */
	@Sql("UPDATE ACCOUNT SET ASSIGNFLAG = /*assignflag*/ WHERE ID = /*accountId*/")
	@Arguments({"accountId","assignflag"})
	public void updateAssignFlag(Long accountId,boolean assignflag);
	
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
	public List<Account> findByProjectid(Long projectid);
	
	/**
	 * 指定したグループに所属するAccountリストを取得します
	 * @param groupid
	 * @return
	 */
	public List<Account> findByGroupid(Long groupid);
	
	/**
	 * 指定したグループに所属しないAccountリストを取得します
	 * @param groupid
	 * @return
	 */
	public List<Account> findByWithoutGroupid(Long groupid);
	
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
