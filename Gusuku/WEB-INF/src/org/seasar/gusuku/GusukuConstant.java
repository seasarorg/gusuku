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
package org.seasar.gusuku;

import java.io.File;

public class GusukuConstant {

	private GusukuConstant(){}
	
	public static final String AUTHENTICATE_KEY = "gusuku.authenticate";
	public static final String UPLOAD_DIR_KEY = "gusuku.upload.dir";
	public static final String SMTP_HOST_KEY = "gusuku.smtp.host";
	public static final String SMTP_PORT_KEY = "gusuku.smtp.port";
	public static final String SMTP_USER_KEY = "gusuku.smtp.user";
	public static final String SMTP_PASSWORD_KEY = "gusuku.smtp.passoword";
	public static final String MAIL_FROM = "gusuku.mail.from";
	public static final String UNDEFINED = "undefined";
	
	public static final String SETUP_DONE = "gusuku.setup.done";
	
	public static final String IMAGE_DIR = File.separator + "image" + File.separator;
	
}
