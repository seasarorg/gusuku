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

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.gusuku.dao.SystemPropertyDao;
import org.seasar.gusuku.entity.SystemProperty;

public class PropertyUtil {

	private static Map<String, String> property = new HashMap<String, String>();

	public static String getProperty(String key) {

		if (property.containsKey(key)) {
			return (String) property.get(key);
		} else {
			S2Container s2 = SingletonS2ContainerFactory.getContainer();
			SystemPropertyDao systemPropertyDao = (SystemPropertyDao) s2
					.getComponent(SystemPropertyDao.class);
			SystemProperty systemProperty = systemPropertyDao.findByKey(key);
			if (systemProperty != null) {
				String value = systemProperty.getValue();
				property.put(key, value);
				return value;
			} else {
				return "";
			}
		}

	}

	public static void clear() {
		property.clear();
	}
}
