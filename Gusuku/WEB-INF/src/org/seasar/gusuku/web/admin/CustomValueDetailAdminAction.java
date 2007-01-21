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

import org.seasar.gusuku.dto.CustomValueDetailAdminDto;
import org.seasar.gusuku.helper.CustomValueHelper;
import org.seasar.gusuku.logic.CustomValueDetailAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * カスタムバリュー詳細
 * @author duran
 *
 */
public class CustomValueDetailAdminAction extends GusukuAction implements
		ModelDriven {

	private static final long serialVersionUID = -3748386991488460481L;
	private CustomValueDetailAdminLogic customValueDetailAdminLogic;
	private CustomValueDetailAdminDto dto = new CustomValueDetailAdminDto();
	
	private CustomValueHelper customValueHelper;
	
	private String[] delid;

	/**
	 * 詳細一覧
	 * @return
	 */
	@XWorkAction(name = "custom_value_detail_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html")))
	public String list() {
		return SUCCESS;
	}

	/**
	 * 詳細登録
	 * @return
	 */
	@XWorkAction(name = "custom_value_detail_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html?valueheadid=${model.valueheadid}")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			customValueDetailAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * 詳細削除
	 * @return
	 */
	@XWorkAction(name = "custom_value_detail_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html?valueheadid=${model.valueheadid}")))
	public String deldone() {
		customValueDetailAdminLogic.delete(delid);
		return SUCCESS;
	}
	
	@XWorkAction(name = "custom_value_detail_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html?valueheadid=${model.valueheadid}")))
	public String up(){
		customValueDetailAdminLogic.sortUp(dto.getId(),dto.getValueheadid());
		return SUCCESS;
	}
	@XWorkAction(name = "custom_value_detail_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_value_detail_list.html?valueheadid=${model.valueheadid}")))
	public String down(){
		customValueDetailAdminLogic.sortDown(dto.getId(),dto.getValueheadid());
		return SUCCESS;
	}

	public Object getModel() {
		return dto;
	}

	public CustomValueHelper getCustomValueHelper() {
		return customValueHelper;
	}

	public void setCustomValueHelper(CustomValueHelper customValueHelper) {
		this.customValueHelper = customValueHelper;
	}

	public void setCustomValueDetailAdminLogic(
			CustomValueDetailAdminLogic customValueDetailAdminLogic) {
		this.customValueDetailAdminLogic = customValueDetailAdminLogic;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}

}
