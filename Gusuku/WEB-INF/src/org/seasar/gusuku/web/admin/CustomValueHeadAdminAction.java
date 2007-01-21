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

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dto.CustomValueHeadAdminDto;
import org.seasar.gusuku.entity.CustomValueHead;
import org.seasar.gusuku.helper.CustomValueHelper;
import org.seasar.gusuku.logic.CustomValueHeadAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * カスタムバリュー
 * @author duran
 *
 */
public class CustomValueHeadAdminAction extends GusukuAction implements ModelDriven{

	private static final long serialVersionUID = 6699658705720352606L;
	private CustomValueHeadAdminDto dto = new CustomValueHeadAdminDto();
	private CustomValueHeadAdminLogic customValueHeadAdminLogic;

	private CustomValueHelper customValueHelper;
	
	private List<CustomValueHead> list;
	
	private String[] delid;

	/**
	 * カスタムバリュー一覧
	 * @return
	 */
	@XWorkAction(name = "custom_value_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_value_list.html")))
	public String list() {
		list = customValueHelper.getCustomValueHeadList(dto);
		return SUCCESS;
	}

	/**
	 * カスタムバリュー入力
	 * @return
	 */
	@XWorkAction(name = "custom_value_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_value_add.html")))
	public String init() {
		if (!StringUtil.isEmpty(dto.getId())) {
			dto = customValueHeadAdminLogic.getCustomValueHead(dto);
		}
		return SUCCESS;
	}

	/**
	 * カスタムバリュー登録
	 * @return
	 */
	@XWorkAction(name = "custom_value_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/custom_value_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			customValueHeadAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * カスタムバリュー削除
	 * @return
	 */
	@XWorkAction(name = "custom_value_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_list.html")))
	public String deldone() {
		customValueHeadAdminLogic.delete(delid);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	public CustomValueHelper getCustomValueHelper() {
		return customValueHelper;
	}

	public void setCustomValueHelper(CustomValueHelper customValueHelper) {
		this.customValueHelper = customValueHelper;
	}

	public void setCustomValueHeadAdminLogic(
			CustomValueHeadAdminLogic customValueHeadAdminLogic) {
		this.customValueHeadAdminLogic = customValueHeadAdminLogic;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	
	public List<CustomValueHead> getList() {
		return list;
	}

}
