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
package org.seasar.gusuku.service;

import org.seasar.gusuku.entity.Account;

/**
 * セッション管理を行うインタフェース
 * @author duran
 *
 */
public interface SessionManagerService {
	
	/**
	 * アカウント情報をセッションにセットする
	 * @param account アカウント情報
	 */
	public void setAccount(Account account);
	
	/**
	 * ログインしているかどうかを調べる
	 * @return true:ログイン中 false:非ログイン
	 */
	public boolean isLogin();
	
	/**
	 * セッションからアカウント情報を取得する
	 * @return アカウント情報
	 */
	public Account getAccount();
	
	/**
	 * セッション情報を破棄する
	 *
	 */
	public void logout();
	
}
