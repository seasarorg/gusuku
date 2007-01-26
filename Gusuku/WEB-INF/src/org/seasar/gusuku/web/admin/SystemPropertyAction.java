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
package org.seasar.gusuku.web.admin;

import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.logic.SystemPropertyLogic;
import org.seasar.gusuku.util.ParameterUtil;
import org.seasar.gusuku.util.PropertyUtil;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.interceptor.ParameterAware;


public class SystemPropertyAction extends GusukuAction implements
		ParameterAware {
	
	private static final long serialVersionUID = 2662400126409905783L;
	private Map<String, String[]> parameters;
	private SystemPropertyLogic systemPropertyLogic;
	@XWorkAction(name = "system", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/system.html")))
	public String init(){
		systemPropertyLogic.init(parameters);
		return SUCCESS;
	}
	
	@XWorkAction(name = "system_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/system.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/system.html")) })
	public String done(){
		if(StringUtil.isEmpty(ParameterUtil.getParameterValue(parameters,"dir"))){
			addFieldError("dir",getText("system.dir.required"));
		}
		if(hasFieldErrors()){
			return INPUT;
		}
		systemPropertyLogic.update(parameters,false);
		PropertyUtil.clear();
		return SUCCESS;
	}
	
	public void setParameters(Map parameters) {
		this.parameters = parameters;

	}
	
	public Map getParameters() {
		return parameters;
	}
	
	public void setSystemPropertyLogic(SystemPropertyLogic systemPropertyLogic) {
		this.systemPropertyLogic = systemPropertyLogic;
	}

}
