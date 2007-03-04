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

import java.util.List;

import org.seasar.gusuku.dto.CustomFormHeadAdminDto;
import org.seasar.gusuku.entity.CustomFormHead;
import org.seasar.gusuku.helper.CustomFormHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.CustomFormHeadAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * カスタムフォーム
 * @author duran
 *
 */
public class CustomFormHeadAdminAction extends GusukuAction implements ModelDriven,AdminAuthenticateAware{
	
	private static final long serialVersionUID = 8997545871418338596L;
	private CustomFormHeadAdminDto dto = new CustomFormHeadAdminDto();
	private CustomFormHeadAdminLogic customFormHeadAdminLogic;

	private CustomFormHelper customFormHelper;
	
	private List<CustomFormHead> list;
	
	private Long[] delid;

	/**
	 * カスタムフォーム一覧
	 * @return
	 */
	@XWorkAction(name = "custom_form_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_list.html")))
	public String list() {
		list = customFormHelper.getCustomFormHeadList(dto);
		return SUCCESS;
	}

	/**
	 * カスタムフォーム入力
	 * @return
	 */
	@XWorkAction(name = "custom_form_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_add.html")))
	public String init() {
		if (dto.getId() != null) {
			dto = customFormHeadAdminLogic.getCustomFormHead(dto);
		}
		return SUCCESS;
	}

	/**
	 * カスタムフォーム登録
	 * @return
	 */
	@XWorkAction(name = "custom_form_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			customFormHeadAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * カスタムフォーム削除
	 * @return
	 */
	@XWorkAction(name = "custom_form_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_list.html")))
	public String deldone() {
		customFormHeadAdminLogic.delete(delid);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	public CustomFormHelper getCustomFormHelper() {
		return customFormHelper;
	}

	public void setCustomFormHelper(CustomFormHelper customFormHelper) {
		this.customFormHelper = customFormHelper;
	}

	public void setCustomFormHeadAdminLogic(
			CustomFormHeadAdminLogic customFormHeadAdminLogic) {
		this.customFormHeadAdminLogic = customFormHeadAdminLogic;
	}

	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	
	public List<CustomFormHead> getList() {
		return list;
	}

}
