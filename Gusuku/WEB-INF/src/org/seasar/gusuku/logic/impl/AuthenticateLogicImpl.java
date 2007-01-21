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

import org.seasar.gusuku.dao.AccountDao;
import org.seasar.gusuku.dto.AuthenticateDto;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.logic.AuthenticateLogic;
import org.seasar.gusuku.service.SessionManagerService;

public class AuthenticateLogicImpl implements AuthenticateLogic {

	private AccountDao accountDao;
	private SessionManagerService sessionManagerService;
	public boolean authenticate(AuthenticateDto authenticateDto) {
		
		Account account = accountDao.findByMailaddrAndPassword(authenticateDto.getMailaddr(),authenticateDto.getPassword());
		
		if(account != null){
			sessionManagerService.setAccount(account);
			return true;
		}
		return false;
	}
	
	
	public void invalidate() {
		sessionManagerService.logout();
	}


	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	public void setSessionManagerService(SessionManagerService sessionManagerService) {
		this.sessionManagerService = sessionManagerService;
	}

}
