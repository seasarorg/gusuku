package org.seasar.gusuku.web;

import org.seasar.gusuku.exception.EntryProjectException;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;


public class ExceptionAction extends GusukuAction {

	@XWorkAction(name = "entryProjectException", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/index.html")))
	public String entryProjectException(){
		throw new EntryProjectException("");
	}
}
