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
package org.seasar.gusuku.logic;

import java.util.Map;

import org.seasar.gusuku.dto.ReportDto;
import org.seasar.gusuku.entity.Account;

public interface ReportLogic {

	public void registration(ReportDto reportDto,Map parameters,Long reporterid);
	
	public void addComment(Map parameters,Account writer);
	
	public void deleteComment(Map parameters);
	
	public void changeStatus(ReportDto reportDto,Long changerid);
	
	public void load(Map parameters,Long reportid);
}
