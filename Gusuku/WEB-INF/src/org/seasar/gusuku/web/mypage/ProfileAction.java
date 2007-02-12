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
package org.seasar.gusuku.web.mypage;

import java.util.List;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dto.AccountDto;
import org.seasar.gusuku.entity.Groupbase;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.GroupbaseHelper;
import org.seasar.gusuku.logic.AccountAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

public class ProfileAction extends GusukuAction implements ModelDriven,Preparable {

	private static final long serialVersionUID = -6722614301913866754L;
	private GroupbaseHelper groupbaseHelper;
	private AccountHelper accountHelper;
	private AccountAdminLogic accountAdminLogic;
	
	private AccountDto dto = new AccountDto();
	
	private List<Groupbase> groupList;
	
	public void prepare(){
	}
	
	public void prepareInit(){
		groupList = groupbaseHelper.getJoinGroupList(getLoginid());
	}
	public void prepareUpdate(){
		groupList = groupbaseHelper.getJoinGroupList(getLoginid());
	}
	
	@XWorkAction(name = "profile", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/mypage/profile.html")))
	public String init(){
		dto =accountAdminLogic.getAccount(getLoginid()); 
		return SUCCESS;
	}
	
	@XWorkAction(name = "profile_update", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/mypage/profile.html")),
			@Result(name="input",type = "mayaa", param = @Param(name = "location", value = "/mypage/profile.html")) })
	public String update(){
		
		if(!StringUtil.isEmpty(dto.getMailaddr()) && accountHelper.isExistMailaddr(getLoginid(),dto.getMailaddr())){
			addFieldError("mailaddr.exist",getText("maiaddr.exist"));
		}
		
		//新しいパスワードが入っている場合はは、パスワード変更と認識
		if(!StringUtil.isEmpty(dto.getNewpassword())){
			//元パスワードチェック
			AccountDto org = accountAdminLogic.getAccount(getLoginid());
			if(!org.getPassword().equals(dto.getPassword())){
				addFieldError("password",getText("profile.password.invalid"));
			}
			//確認と一致しているかチェック
			if(!dto.getNewpassword().equals(dto.getRepassword())){
				addFieldError("newpassowrd",getText("profile.password.mismatch"));
			}
		}
		if(hasFieldErrors()){
			return INPUT;
		}
		dto.setId(getLoginid());
		accountAdminLogic.update(dto);
		
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	public void setGroupbaseHelper(GroupbaseHelper groupbaseHelper) {
		this.groupbaseHelper = groupbaseHelper;
	}

	public void setAccountAdminLogic(AccountAdminLogic accountAdminLogic) {
		this.accountAdminLogic = accountAdminLogic;
	}

	public void setAccountHelper(AccountHelper accountHelper) {
		this.accountHelper = accountHelper;
	}

	
	public List getGroupList() {
		return groupList;
	}

}
