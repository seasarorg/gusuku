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

import org.seasar.gusuku.dao.MailConditionDao;
import org.seasar.gusuku.dto.MailConditionDto;
import org.seasar.gusuku.dxo.MailConditionDxo;
import org.seasar.gusuku.entity.MailCondition;
import org.seasar.gusuku.logic.MailConditionLogic;

public class MailConditionLogicImpl implements MailConditionLogic {

	private MailConditionDao mailConditionDao;
	private MailConditionDxo mailConditionDxo;
	
	public void registration(MailConditionDto mailConditionDto, Long accountid) {
		MailCondition mailCondition = mailConditionDxo.convert(mailConditionDto);
		
		//TODO
		if(mailConditionDto.getId() == null){
			mailCondition.setAccountid(accountid);
			mailConditionDao.insert(mailCondition);
		}else{
			mailCondition.setAccountid(accountid);
			mailConditionDao.update(mailCondition);
		}

	}

	public void setMailConditionDao(MailConditionDao mailConditionDao) {
		this.mailConditionDao = mailConditionDao;
	}

	public void setMailConditionDxo(MailConditionDxo mailConditionDxo) {
		this.mailConditionDxo = mailConditionDxo;
	}

}
