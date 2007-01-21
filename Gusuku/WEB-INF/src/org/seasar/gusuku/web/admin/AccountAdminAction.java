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
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.AccountKindHelper;
import org.seasar.gusuku.logic.AccountAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * ユーザー管理
 * @author duran
 *
 */
public class AccountAdminAction extends GusukuAction implements ModelDriven {

	private static final long serialVersionUID = -8360123046340756747L;
	private AccountAdminLogic accountAdminLogic;
	private AccountKindHelper accountKindHelper;
	private AccountHelper accountHelper;

	private AccountAdminDto dto = new AccountAdminDto();

	private List list;

	private String[] delid;

	/**
	 * ユーザー一覧
	 * @return
	 */
	@XWorkAction(name = "account_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/account_list.html")))
	public String list() {
		list = accountHelper.getAccountList(dto);
		return SUCCESS;
	}

	/**
	 * ユーザー入力
	 * @return
	 */
	@XWorkAction(name = "account_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/account_add.html")))
	public String init() {
		if (!StringUtil.isEmpty(dto.getId())) {
			dto = accountAdminLogic.getAccount(dto);
		}
		return SUCCESS;
	}

	public String confirm() {
		// セッションへ
		return SUCCESS;
	}

	/**
	 * ユーザー登録
	 * @return
	 */
	@XWorkAction(name = "account_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/account_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/account_add.html")) })
	public String done() {
		if(!StringUtil.isEmpty(dto.getMailaddr()) && accountHelper.isExistMailaddr(dto.getId(),dto.getMailaddr())){
			addFieldError("",getText("maiaddr.exist"));
		}
		if(hasFieldErrors()){
			return INPUT;
		}
		if(TokenHelper.validToken()){
			accountAdminLogic.registration(dto);
		}
		return SUCCESS;
	}

	public String delconfirm() {
		//
		return SUCCESS;
	}

	/**
	 * ユーザー削除
	 * @return
	 */
	@XWorkAction(name = "account_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/account_list.html")))
	public String deldone() {
		accountAdminLogic.delete(delid);
		return SUCCESS;
	}

	public List getList() {
		return list;
	}

	public Object getModel() {
		return dto;
	}

	public void setDelid(String[] delid) {
		this.delid = delid;
	}

	public void setAccountKindHelper(AccountKindHelper accountKindHelper) {
		this.accountKindHelper = accountKindHelper;
	}

	public void setAccountAdminLogic(AccountAdminLogic accountAdminLogic) {
		this.accountAdminLogic = accountAdminLogic;
	}

	
	public AccountKindHelper getAccountKindHelper() {
		return accountKindHelper;
	}

	public void setAccountHelper(AccountHelper accountHelper) {
		this.accountHelper = accountHelper;
	}

}