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
package org.seasar.gusuku.service.impl;

import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.service.MailService;
import org.seasar.gusuku.util.PropertyUtil;

import com.ozacc.mail.Mail;
import com.ozacc.mail.impl.SendMailImpl;

public class MailServiceImpl implements MailService {

	public Mail[] send(Mail[] maillist) {
		if (maillist != null && maillist.length > 0) {
			String smtpHost = PropertyUtil.getProperty(GusukuConstant.SMTP_HOST_KEY);
			if (!StringUtil.isEmpty(smtpHost)) {
				SendMailImpl sendMail = new SendMailImpl(smtpHost);
				
				//ポート設定
				String smtpPort = PropertyUtil.getProperty(GusukuConstant.SMTP_PORT_KEY);
				if(!StringUtil.isEmpty(smtpPort)){
					sendMail.setPort(Integer.parseInt(smtpPort));
				}
				
				//ユーザーとパスワード設定
				String smtpUser = PropertyUtil.getProperty(GusukuConstant.SMTP_USER_KEY);
				String smtpPassword = PropertyUtil.getProperty(GusukuConstant.SMTP_PASSWORD_KEY);
				if (!StringUtil.isEmpty(smtpUser) && !StringUtil.isEmpty(smtpPassword)) {
					sendMail.setUsername(smtpUser);
					sendMail.setPassword(smtpPassword);
				}
				sendMail.setMessageId("gusuku.org");
				sendMail.send(maillist);
				
				return maillist;
				
			}
		}
		return null;
	}

}
