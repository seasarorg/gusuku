package org.seasar.gusuku.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.seasar.gusuku.exception.SaveUploadFileException;

public class FileUploadUtil {

	public static void save(File tmpFile, String uploadDir, String destName) {

		if (tmpFile != null) {
			if (!uploadDir.endsWith(File.separator)) {
				uploadDir = uploadDir + File.separator;
			}
			
			try {
				FileUtils.copyFile(tmpFile,new File(uploadDir + destName));
				tmpFile.delete();
			} catch (Exception e) {
				throw new SaveUploadFileException(e.getMessage()+uploadDir + destName);
			}
		}
	}

}
