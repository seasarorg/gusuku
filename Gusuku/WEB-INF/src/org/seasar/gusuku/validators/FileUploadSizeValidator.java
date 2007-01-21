package org.seasar.gusuku.validators;

import java.io.File;

import com.opensymphony.xwork.validator.ValidationException;
import com.opensymphony.xwork.validator.validators.FieldValidatorSupport;

public class FileUploadSizeValidator extends FieldValidatorSupport {

	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		
		Object value = getFieldValue(fieldName,object);
		
		if(value != null){
			if(value instanceof File){
				if(((File)value).length() == 0){
					addFieldError(fieldName,object);
				}
			}
		}

	}

}
