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
package org.seasar.gusuku.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.gusuku.exception.SaveUploadFileException;

public class FileUploadUtil {
	
	private static final Log LOG = LogFactory.getLog(FileUploadUtil.class);

	public static void save(File tmpFile, String uploadDir, String destName) {

		if (tmpFile != null) {
			if (!uploadDir.endsWith(File.separator)) {
				uploadDir = uploadDir + File.separator;
			}
			System.out.println("UPLOAD "+uploadDir);
			try {
				FileUtils.copyFile(tmpFile,new File(uploadDir + destName));
				tmpFile.delete();
				if(LOG.isDebugEnabled()){
					LOG.debug("UPLOAD FILE : " + uploadDir + destName);
				}
			} catch (Exception e) {
				throw new SaveUploadFileException(e.getMessage()+uploadDir + destName);
			}
		}
	}

}
