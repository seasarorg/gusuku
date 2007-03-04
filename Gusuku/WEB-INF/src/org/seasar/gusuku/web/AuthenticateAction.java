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

import org.seasar.gusuku.dto.AuthenticateDto;
import org.seasar.gusuku.interceptor.NonAuthenticateAware;
import org.seasar.gusuku.logic.AuthenticateLogic;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;

/**
 * ログイン・ログアウト
 * 
 * @author duran
 */
public class AuthenticateAction extends GusukuAction implements
		NonAuthenticateAware, ModelDriven {

	private static final long serialVersionUID = 6373596856464873826L;

	private AuthenticateLogic authenticateLogic;

	private AuthenticateDto dto = new AuthenticateDto();

	/**
	 * ログイン入力
	 * 
	 * @return
	 */
	@XWorkAction(name = "index", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/index.html")))
	public String index() {
		return SUCCESS;
	}

	/**
	 * ログイン
	 * 
	 * @return
	 */
	@XWorkAction(name = "login", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/home.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/index.html")) })
	public String login() {

		if (authenticateLogic.authenticate(dto)) {
			return SUCCESS;
		} else {
			addFieldError("login", getText("login.fail"));
			return "input";
		}
	}

	/**
	 * ログアウト
	 * 
	 * @return
	 */
	@XWorkAction(name = "logout", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/index.html")))
	public String logout() {
		authenticateLogic.invalidate();
		return SUCCESS;
	}

	public Object getModel() {
		return dto;
	}

	public void setAuthenticateLogic(AuthenticateLogic authenticateLogic) {
		this.authenticateLogic = authenticateLogic;
	}

}
