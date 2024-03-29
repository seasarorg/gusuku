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
package org.seasar.gusuku.dao;

import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.entity.AccountGroupbase;

@S2Dao(bean=AccountGroupbase.class)
public interface AccountGroupbaseDao {

	public void insert(AccountGroupbase accountGroupbase);
	public void update(AccountGroupbase accountGroupbase);
	public void delete(AccountGroupbase accountGroupbase);
}
