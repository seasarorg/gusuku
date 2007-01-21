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

import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.interceptor.NonAuthenticateAction;
import org.seasar.gusuku.logic.AccountAdminLogic;
import org.seasar.gusuku.logic.SystemPropertyLogic;
import org.seasar.gusuku.util.ParameterUtil;
import org.seasar.gusuku.util.PropertyUtil;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.interceptor.ParameterAware;


public class SetupAction extends GusukuAction implements NonAuthenticateAction,
		ParameterAware {

	private static final long serialVersionUID = -5949576909188190374L;

	private SystemPropertyLogic systemPropertyLogic;
	private AccountAdminLogic accountAdminLogic;
	
	private Map parameters;

	@XWorkAction(name = "setup", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/setup.html")))
	public String init(){
		String setup = PropertyUtil.getProperty(GusukuConstant.SETUP_DONE);
		if(StringUtil.isEmpty(setup)){
			return SUCCESS;
		}else{
			return "login";
		}
	}
	
	@XWorkAction(name = "setup_done", result = @Result(type = "redirect", param = @Param(name = "location", value = "/index.html")))
	public String done(){
		AccountAdminDto dto = new AccountAdminDto();
		dto.setMailaddr(ParameterUtil.getParameterValue(parameters,"mailaddr"));
		dto.setPassword(ParameterUtil.getParameterValue(parameters,"password"));
		dto.setName(ParameterUtil.getParameterValue(parameters,"name"));
		dto.setKind("1");
		accountAdminLogic.registration(dto);
		systemPropertyLogic.update(parameters,true);
		return SUCCESS;
	}
	
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	
	public void setAccountAdminLogic(AccountAdminLogic accountAdminLogic) {
		this.accountAdminLogic = accountAdminLogic;
	}

	
	public void setSystemPropertyLogic(SystemPropertyLogic systemPropertyLogic) {
		this.systemPropertyLogic = systemPropertyLogic;
	}

	
	public Map getParameters() {
		return parameters;
	}

}
