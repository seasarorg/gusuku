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
package org.seasar.gusuku.interceptor;

import org.seasar.gusuku.service.SessionManagerService;

import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

/**
 * ログイン認証インターセプター
 * @author duran
 *
 */
public class AuthenticateInterceptor implements Interceptor {

	private static final long serialVersionUID = -7367064167687432527L;
	private SessionManagerService sessionManagerService;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action)invocation.getAction();
		
		if(action instanceof NonAuthenticateAction){
			//チェックしない
			return invocation.invoke();
		}
		
		if(sessionManagerService.isLogin()){
			//管理ツールチェック
			if(action.getClass().getName().indexOf("Admin") > 0){
				if(sessionManagerService.getAccount().getKind() == 1){
					return invocation.invoke();
				}
			}else{
				return invocation.invoke();
			}
		}
		return "login";
	}

	public void setSessionManagerService(SessionManagerService sessionManagerService) {
		this.sessionManagerService = sessionManagerService;
	}
}
