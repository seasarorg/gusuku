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

import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dto.GroupbaseAdminDto;
import org.seasar.gusuku.dto.PagerSupport;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.entity.Groupbase;
import org.seasar.gusuku.helper.AccountHelper;
import org.seasar.gusuku.helper.GroupbaseHelper;
import org.seasar.gusuku.logic.GroupbaseAdminLogic;
import org.seasar.gusuku.web.GusukuAction;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.util.TokenHelper;
import com.opensymphony.xwork.ModelDriven;

/**
 * グループ管理
 * @author duran
 *
 */
public class GroupAdminAction extends GusukuAction implements ModelDriven{
	
	private static final long serialVersionUID = -142765400130553629L;
	private GroupbaseAdminLogic groupbaseAdminLogic;
	private GroupbaseHelper groupbaseHelper;
	private AccountHelper accountHelper;
	
	private List<Groupbase> groupList;
	private List<Account> accountList;
	private List<Account> memberList;
	
	private Long[] delid;
	private Long[] addid;
	private Long[] removeid;
	
	private Groupbase groupbase;
	
	private GroupbaseAdminDto dto = new GroupbaseAdminDto();
	private PagerSupport pagerSupport = new PagerSupport(GusukuConstant.SEARCH_LIMIT,"groupbaseAdminDto");
	
	/**
	 * グループ一覧
	 * @return
	 */
	@XWorkAction(name = "group_list", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/group_list.html")))
	public String list(){
		dto = (GroupbaseAdminDto)pagerSupport.getPagerCondition(ServletActionContext.getRequest(),dto);
		groupList = groupbaseHelper.getGroupbaseList(dto);
		return SUCCESS;
	}
	
	/**
	 * グループ入力
	 * @return
	 */
	@XWorkAction(name = "group_edit", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/group_add.html")))
	public String init(){
		if (dto.getId() != null) {
			dto = groupbaseAdminLogic.getGroupbase(dto);
		}
		return SUCCESS;
	}
	
	/**
	 * グループ登録
	 * @return
	 */
	@XWorkAction(name = "group_done", result = {
			@Result(type = "redirect", param = @Param(name = "location", value = "/admin/group_list.html")),
			@Result(name = "input", type = "mayaa", param = @Param(name = "location", value = "/admin/group_add.html")) })
	public String done() {
		if(TokenHelper.validToken()){
			groupbaseAdminLogic.registration(dto);
		}
		return SUCCESS;
	}
	
	/**
	 * グループ削除
	 * @return
	 */
	@XWorkAction(name = "group_delete", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/group_list.html")))
	public String deldone() {
		if (delid != null && delid.length > 0) {
			groupbaseAdminLogic.delete(delid);
		}
		return SUCCESS;
	}
	
	/**
	 * グループメンバー一覧
	 * @return
	 */
	@XWorkAction(name = "group_member", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/admin/group_member.html")))
	public String group() {
		memberList = accountHelper.getGroupmemberList(dto.getId());
		accountList = accountHelper.getWithoutsGroupmemberList(dto.getId());
		groupbase = groupbaseHelper.getGroupbase(dto.getId());
		return SUCCESS;
	}
	
	/**
	 * グループメンバー移動
	 * @return
	 */
	@XWorkAction(name = "group_move", result = @Result(type = "redirect", param = @Param(name = "location", value = "/admin/group_member.html?id=${model.id}")))
	public String move(){
		return SUCCESS;
	}
	
	/**
	 * グループメンバー移動　追加
	 * @return
	 */
	public String doAddbutton(){
		groupbaseAdminLogic.memberAdd(dto.getId(),addid);
		return SUCCESS;
	}
	/**
	 * グループメンバー移動　削除
	 * @return
	 */
	public String doDeletebutton(){
		groupbaseAdminLogic.memberRemove(dto.getId(),removeid);
		return SUCCESS;
	}
	
	public Object getModel(){
		return dto;
	}

	public List<Groupbase> getGroupList() {
		return groupList;
	}

	public void setGroupbaseHelper(GroupbaseHelper groupbaseHelper) {
		this.groupbaseHelper = groupbaseHelper;
	}

	public void setGroupbaseAdminLogic(GroupbaseAdminLogic groupbaseAdminLogic) {
		this.groupbaseAdminLogic = groupbaseAdminLogic;
	}

	public void setDelid(Long[] delid) {
		this.delid = delid;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public List<Account> getMemberList() {
		return memberList;
	}

	public void setAccountHelper(AccountHelper accountHelper) {
		this.accountHelper = accountHelper;
	}

	public void setAddid(Long[] addid) {
		this.addid = addid;
	}

	public void setRemoveid(Long[] removeid) {
		this.removeid = removeid;
	}

	
	public Groupbase getGroupbase() {
		return groupbase;
	}

}
