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

import org.seasar.gusuku.dto.CustomFormDetailAdminDto;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.entity.CustomFormHead;
import org.seasar.gusuku.entity.CustomValueHead;
import org.seasar.gusuku.entity.FormType;
import org.seasar.gusuku.helper.CustomFormHelper;
import org.seasar.gusuku.helper.CustomValueHelper;
import org.seasar.gusuku.helper.FormTypeHelper;
import org.seasar.gusuku.interceptor.AdminAuthenticateAware;
import org.seasar.gusuku.logic.CustomFormDetailAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

/**
 * カスタムフォーム詳細
 * @author duran
 *
 */
public class CustomFormDetailAdminAction extends GusukuAction implements ModelDriven,Preparable,AdminAuthenticateAware{

	private static final long serialVersionUID = -3004552876672844928L;
	private CustomFormDetailAdminDto dto = new CustomFormDetailAdminDto();
	private CustomFormDetailAdminLogic customFormDetailAdminLogic;

	private CustomFormHelper customFormHelper;
	private CustomValueHelper customValueHelper;
	private FormTypeHelper formTypeHelper;
	
	private List<CustomFormDetail> list;
	private CustomFormHead customFormHead;
	
	private List<FormType> formTypeList;
	private List<CustomValueHead> CustomValueHeadList;
	
	private Long[] delid;
	
	public void prepare(){
		
	}
	
	public void prepareInit(){
		formTypeList = formTypeHelper.getFormTypeList();
		CustomValueHeadList = customValueHelper.getCustomValueHeadList();
	}

	public void prepareDone(){
		formTypeList = formTypeHelper.getFormTypeList();
		CustomValueHeadList = customValueHelper.getCustomValueHeadList();
	}
	/**
	 * 詳細一覧
	 * @return
	 */
	@XWorkAction(name = "custom_form_detail_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_detail_list.html")))
	public String list() {
		list = customFormHelper.getFormList(dto.getFormheadid());
		customFormHead = customFormHelper.getCustomFormHead(dto.getFormheadid());
		return SUCCESS;
	}

	/**
	 * 詳細入力
	 * @return
	 */
	@XWorkAction(name = "custom_form_detail_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_detail_add.html")))
	public String init() {
		if (dto.getId() != null) {
			dto = customFormDetailAdminLogic.getCustomFormDetail(dto);
		}
		return SUCCESS;
	}

	/**
	 * 詳細登録
	 * @return
	 */
	@XWorkAction(name = "custom_form_detail_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_detail_list.html?formheadid=${model.formheadid}")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/custom_form_detail_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			customFormDetailAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * 詳細削除
	 * @return
	 */
	@XWorkAction(name = "custom_form_detail_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_detail_list.html?formheadid=${model.formheadid}")))
	public String deldone() {
		customFormDetailAdminLogic.delete(dto.getFormheadid(),delid);
		return SUCCESS;
	}
	
	@XWorkAction(name = "custom_form_detail_up", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_detail_list.html?formheadid=${model.formheadid}")))
	public String up(){
		customFormDetailAdminLogic.sortUp(dto.getId(),dto.getFormheadid());
		return SUCCESS;
	}
	@XWorkAction(name = "custom_form_detail_down", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/custom_form_detail_list.html?formheadid=${model.formheadid}")))
	public String down(){
		customFormDetailAdminLogic.sortDown(dto.getId(),dto.getFormheadid());
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	public void setCustomFormHelper(CustomFormHelper customFormHelper) {
		this.customFormHelper = customFormHelper;
	}

	public void setCustomFormDetailAdminLogic(
			CustomFormDetailAdminLogic customFormDetailAdminLogic) {
		this.customFormDetailAdminLogic = customFormDetailAdminLogic;
	}

	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	public void setFormTypeHelper(FormTypeHelper formTypeHelper) {
		this.formTypeHelper = formTypeHelper;
	}

	public void setCustomValueHelper(CustomValueHelper customValueHelper) {
		this.customValueHelper = customValueHelper;
	}

	
	public CustomFormHead getCustomFormHead() {
		return customFormHead;
	}

	
	public List<CustomFormDetail> getList() {
		return list;
	}

	
	public List<CustomValueHead> getCustomValueHeadList() {
		return CustomValueHeadList;
	}

	
	public List<FormType> getFormTypeList() {
		return formTypeList;
	}

}
