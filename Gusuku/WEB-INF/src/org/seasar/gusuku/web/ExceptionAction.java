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
package org.seasar.gusuku.web;

import org.seasar.gusuku.exception.EntryProjectException;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;


public class ExceptionAction extends GusukuAction {

	private static final long serialVersionUID = -3457548869726811776L;

	@XWorkAction(name = "entryProjectException", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/index.html")))
	public String entryProjectException(){
		throw new EntryProjectException("");
	}
}
