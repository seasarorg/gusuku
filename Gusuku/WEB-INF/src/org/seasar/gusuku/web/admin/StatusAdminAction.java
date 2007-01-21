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
import org.seasar.gusuku.dto.StatusAdminDto;
import org.seasar.gusuku.entity.Status;
import org.seasar.gusuku.helper.StatusHelper;
import org.seasar.gusuku.logic.StatusAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * ステータス管理
 * @author duran
 *
 */
public class StatusAdminAction extends GusukuAction implements ModelDriven {

	private static final long serialVersionUID = -2257989462791919363L;

	private StatusAdminLogic statusAdminLogic;
	private StatusAdminDto dto = new StatusAdminDto();

	private StatusHelper statusHelper;

	private List<Status> list;
	private String[] delid;

	/**
	 * ステータス一覧
	 * @return
	 */
	@XWorkAction(name = "status_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/status_list.html")))
	public String list() {
		list = statusHelper.getStatusList(dto);
		return SUCCESS;
	}

	/**
	 * ステータス入力
	 * @return
	 */
	@XWorkAction(name = "status_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/status_add.html")))
	public String init() {
		if (!StringUtil.isEmpty(dto.getId())) {
			dto = statusAdminLogic.getStatus(dto);
		}
		return SUCCESS;
	}

	/**
	 * ステータス登録
	 * @return
	 */
	@XWorkAction(name = "status_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/status_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/status_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			statusAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	/**
	 * ステータス削除
	 * @return
	 */
	@XWorkAction(name = "status_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/status_list.html")))
	public String deldone() {
		statusAdminLogic.delete(delid);
		return SUCCESS;
	}

	public Object getModel() {
		return dto;
	}

	public StatusHelper getStatusHelper() {
		return statusHelper;
	}

	public void setStatusHelper(StatusHelper statusHelper) {
		this.statusHelper = statusHelper;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	public void setStatusAdminLogic(StatusAdminLogic statusAdminLogic) {
		this.statusAdminLogic = statusAdminLogic;
	}

	
	public List<Status> getList() {
		return list;
	}

}
