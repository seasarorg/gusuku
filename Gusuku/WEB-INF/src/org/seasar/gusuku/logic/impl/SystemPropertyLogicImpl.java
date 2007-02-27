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

import java.io.File;
import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.SystemPropertyDao;
import org.seasar.gusuku.entity.SystemProperty;
import org.seasar.gusuku.logic.SystemPropertyLogic;
import org.seasar.gusuku.util.ParameterUtil;


public class SystemPropertyLogicImpl implements SystemPropertyLogic {
	
	private SystemPropertyDao systemPropertyDao;
	
	public void init(Map<String,String[]> parameters){
		SystemProperty dir = systemPropertyDao.findByKey(GusukuConstant.UPLOAD_DIR_KEY);
		if(dir != null){
			ParameterUtil.putParameterValue(parameters,"dirid",dir.getId());
			ParameterUtil.putParameterValue(parameters,"dir",dir.getValue());
		}
		
		SystemProperty smtp = systemPropertyDao.findByKey(GusukuConstant.SMTP_HOST_KEY);
		if(smtp != null){
			ParameterUtil.putParameterValue(parameters,"smtpid",smtp.getId());
			ParameterUtil.putParameterValue(parameters,"smtp",smtp.getValue());
		}

		SystemProperty smtpport = systemPropertyDao.findByKey(GusukuConstant.SMTP_PORT_KEY);
		if(smtpport != null){
			ParameterUtil.putParameterValue(parameters,"smtpportid",smtpport.getId());
			ParameterUtil.putParameterValue(parameters,"smtpport",smtpport.getValue());
		}
		
		SystemProperty smtpuser = systemPropertyDao.findByKey(GusukuConstant.SMTP_USER_KEY);
		if(smtpuser != null){
			ParameterUtil.putParameterValue(parameters,"smtpuserid",smtpuser.getId());
			ParameterUtil.putParameterValue(parameters,"smtpuser",smtpuser.getValue());
		}
		
		SystemProperty smtppassword = systemPropertyDao.findByKey(GusukuConstant.SMTP_PASSWORD_KEY);
		if(smtppassword != null){
			ParameterUtil.putParameterValue(parameters,"smtppasswordid",smtppassword.getId());
			ParameterUtil.putParameterValue(parameters,"smtppassword",smtppassword.getValue());
		}
		
		SystemProperty from = systemPropertyDao.findByKey(GusukuConstant.MAIL_FROM);
		if(from != null){
			ParameterUtil.putParameterValue(parameters,"fromid",from.getId());
			ParameterUtil.putParameterValue(parameters,"from",from.getValue());
		}
		
	}

	public void update(Map<String,String[]> parameters,boolean setup) {

		updateProperty(GusukuConstant.UPLOAD_DIR_KEY, "dirid", "dir", parameters);
		updateProperty(GusukuConstant.SMTP_HOST_KEY, "smtpid", "smtp", parameters);
		updateProperty(GusukuConstant.SMTP_PORT_KEY, "smtpportid", "smtpport", parameters);
		updateProperty(GusukuConstant.SMTP_USER_KEY, "smtpuserid", "smtpuser", parameters);
		updateProperty(GusukuConstant.SMTP_PASSWORD_KEY, "smtppasswordid", "smtppassword", parameters);
		updateProperty(GusukuConstant.MAIL_FROM, "fromid", "from", parameters);

		if (setup) {
			SystemProperty prop = new SystemProperty();
			prop.setKey(GusukuConstant.SETUP_DONE);
			prop.setValue("done");
			systemPropertyDao.insert(prop);
		}

	}
	
	private void updateProperty(String key,String idKey,String valueKey,Map parameters){
		String value = ParameterUtil.getParameterValue(parameters, valueKey);
		
		//ディレクトリの場合、最後に区切り文字を入れる
		if(valueKey.equals("dir")){
			if(!value.endsWith(File.separator)){
				value = value + File.separator;
			}
		}

		String id = ParameterUtil.getParameterValue(parameters, idKey);
		SystemProperty prop = new SystemProperty();

			prop.setKey(key);
			prop.setValue(value);
			
			if (StringUtil.isEmpty(id) && !StringUtil.isEmpty(value)) {
				systemPropertyDao.insert(prop);
			} else if(!StringUtil.isEmpty(id)){
				prop.setId(Long.parseLong(id));
				systemPropertyDao.update(prop);
			}
	}
	
	public void setSystemPropertyDao(SystemPropertyDao systemPropertyDao) {
		this.systemPropertyDao = systemPropertyDao;
	}

}
