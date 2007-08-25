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
package org.seasar.gusuku.logic.impl;

import java.util.Date;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.AccountDao;
import org.seasar.gusuku.dto.AccountAdminDto;
import org.seasar.gusuku.dto.AccountDto;
import org.seasar.gusuku.dxo.AccountDxo;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.logic.AccountAdminLogic;
import org.seasar.gusuku.service.SessionManagerService;

public class AccountAdminLogicImpl implements AccountAdminLogic {

	private AccountDao accountDao;
	private AccountDxo accountDxo;
	
	private SessionManagerService sessionManagerService;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	public void delete(Long[] delids) {
		if(delids != null && delids.length > 0){
			accountDao.updateDelflag(delids);
		}
	}

	public void registration(AccountAdminDto accountAdminDto) {

		Account account = accountDxo.convert(accountAdminDto);
		
		if(accountAdminDto.getId()== null){
			accountDao.insert(account);
		}else{
			account.setUdate(new Date());
			accountDao.update(account);
			sessionManagerService.setAccount(account);
		}
		
	}
	
	public AccountAdminDto getAccount(AccountAdminDto accountAdminDto) {
		Account account = accountDao.findById(accountAdminDto.getId());
		
		return accountDxo.convertAdminDto(account);
	}
	
	public AccountDto getAccount(Long accountid){
		Account account = accountDao.findById(accountid);
		return accountDxo.convertDto(account);
	}
	public void setAccountDxo(AccountDxo accountDxo) {
		this.accountDxo = accountDxo;
	}
	public void update(AccountDto dto) {
		Account account = accountDao.findById(dto.getId());
		account.setMailaddr(dto.getMailaddr());
		account.setName(dto.getName());
		if(!StringUtil.isEmpty(dto.getNewpassword())){
			account.setPassword(dto.getNewpassword());
		}
		account.setRdate(new Date());
		accountDao.update(account);
		sessionManagerService.setAccount(account);
		
	}
	public void updateAssignFlag(Long accountId, boolean assignFlag) {
		accountDao.updateAssignFlag(accountId,assignFlag);
		Account account = sessionManagerService.getAccount();
		account.setAssignflag(assignFlag);
		sessionManagerService.setAccount(account);
	}
	
	public void setSessionManagerService(SessionManagerService sessionManagerService) {
		this.sessionManagerService = sessionManagerService;
	}

}
