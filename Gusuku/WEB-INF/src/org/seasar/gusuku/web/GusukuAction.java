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
package org.seasar.gusuku.web;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.service.SessionManagerService;

import com.opensymphony.xwork.ActionSupport;

/**
 * Gusuku基底アクションクラス
 * @author duran
 *
 */
public class GusukuAction extends ActionSupport {

	private static final long serialVersionUID = 709593522981703823L;
	protected SessionManagerService sessionManager;

	/**
	 * ログインしているユーザーのIDを取得する
	 * @return ログインユーザーID
	 */
	public Long getLoginid() {
		return sessionManager.getAccount().getId();
	}

	/**
	 * 管理者かどうかを調べる
	 * @return true:管理者 false:非管理者
	 */
	public boolean isAdmin() {
		if (sessionManager.isLogin()) {
			Account account = sessionManager.getAccount();
			return account.getKind() == 1;
		} else {
			return false;
		}
	}

	/**
	 * ログインしているかどうか調べる
	 * @return true:ログイン中 false:非ログイン
	 */
	public boolean isLogin() {
		return sessionManager.isLogin();
	}

	public void setSessionManager(SessionManagerService sessionManager) {
		this.sessionManager = sessionManager;
	}
	
	public String dateFormat(String str){
		if(StringUtil.isEmpty(str)){
			return "";
		}
		return str.replace("/","-");
	}

}
