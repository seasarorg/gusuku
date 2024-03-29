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

import org.seasar.gusuku.dao.AccountGroupbaseDao;
import org.seasar.gusuku.dao.GroupbaseDao;
import org.seasar.gusuku.dto.GroupbaseAdminDto;
import org.seasar.gusuku.dxo.GroupbaseDxo;
import org.seasar.gusuku.entity.AccountGroupbase;
import org.seasar.gusuku.entity.Groupbase;
import org.seasar.gusuku.logic.GroupbaseAdminLogic;

public class GroupbaseAdminLogicImpl implements GroupbaseAdminLogic {
	
	private GroupbaseDao groupbaseDao;
	private AccountGroupbaseDao accountGroupbaseDao;
	private GroupbaseDxo groupbaseDxo;

	public void registration(GroupbaseAdminDto groupbaseAdminDto) {
		Groupbase groupbase = groupbaseDxo.convert(groupbaseAdminDto);
		
		if(groupbaseAdminDto.getId() == null){
			groupbaseDao.insert(groupbase);
		}else{
			groupbase.setUdate(new Date());
			groupbaseDao.update(groupbase);
		}

	}

	public void delete(Long[] delids) {
		groupbaseDao.updateDelflag(delids);
	}

	public GroupbaseAdminDto getGroupbase(GroupbaseAdminDto groupbaseAdminDto) {
		Groupbase groupbase = groupbaseDao.findById(groupbaseAdminDto.getId());
		return groupbaseDxo.convert(groupbase);
	}

	public void setGroupbaseDao(GroupbaseDao groupbaseDao) {
		this.groupbaseDao = groupbaseDao;
	}

	public void setGroupbaseDxo(GroupbaseDxo groupbaseDxo) {
		this.groupbaseDxo = groupbaseDxo;
	}

	public void memberAdd(Long groupid,Long[] delids) {
		if(delids != null && delids.length > 0){
			for (Long delid : delids) {
				AccountGroupbase accountGroupbase = new AccountGroupbase();
				accountGroupbase.setAccountid(delid);
				accountGroupbase.setGroupbaseid(groupid);
				accountGroupbaseDao.insert(accountGroupbase);
			}
		}
	}

	public void memberRemove(Long groupid,Long[] delids) {
		if(delids != null && delids.length > 0){
			for (Long delid : delids) {
				AccountGroupbase accountGroupbase = new AccountGroupbase();
				accountGroupbase.setAccountid(delid);
				accountGroupbase.setGroupbaseid(groupid);
				accountGroupbaseDao.delete(accountGroupbase);
			}
		}
		
	}

	public void setAccountGroupbaseDao(AccountGroupbaseDao accountGroupbaseDao) {
		this.accountGroupbaseDao = accountGroupbaseDao;
	}
	
	

}
