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
package org.seasar.gusuku.service.impl;

import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.service.SessionManagerService;

import com.opensymphony.webwork.ServletActionContext;

/**
 * HttpSessionを用いてセッション管理を行う
 * @author duran
 *
 */
public class SessionManagerServiceImpl implements SessionManagerService {

	public void setAccount(Account account) {
		ServletActionContext.getRequest().getSession().setAttribute(GusukuConstant.AUTHENTICATE_KEY,account);

	}

	public boolean isLogin() {
		Account account = (Account)ServletActionContext.getRequest().getSession().getAttribute(GusukuConstant.AUTHENTICATE_KEY);
		return account != null;
	}
	
	public boolean isAdmin(){
		Account account = (Account)ServletActionContext.getRequest().getSession().getAttribute(GusukuConstant.AUTHENTICATE_KEY);
		if(account != null){
			return account.getKind() == 1;
		}
		return false;
	}

	public Account getAccount() {
		return (Account)ServletActionContext.getRequest().getSession().getAttribute(GusukuConstant.AUTHENTICATE_KEY);
	}

	public void logout() {
		ServletActionContext.getRequest().getSession().invalidate();
	}
	
	

}
