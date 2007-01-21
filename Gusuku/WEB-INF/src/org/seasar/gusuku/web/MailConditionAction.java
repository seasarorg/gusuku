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

import org.seasar.gusuku.dto.MailConditionDto;
import org.seasar.gusuku.logic.MailConditionLogic;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;

public class MailConditionAction extends GusukuAction implements ModelDriven {
	
	private static final long serialVersionUID = 5444806313191229100L;
	private MailConditionDto dto = new MailConditionDto();
	
	private MailConditionLogic mailConditionLogic;
	
	@XWorkAction(name = "mail_condition", result = @Result(type = "redirect", param = @Param(name = "location", value = "/project_detail.html?id=${model.projectid}")))
	public String execute(){
		mailConditionLogic.registration(dto,getLoginid());		
		return SUCCESS;
	}

	public Object getModel() {
		return dto;
	}

	public void setMailConditionLogic(MailConditionLogic mailConditionLogic) {
		this.mailConditionLogic = mailConditionLogic;
	}

}
