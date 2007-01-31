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
package org.seasar.gusuku.dto;

import java.io.File;
import java.io.Serializable;


public class StatusAdminDto implements Serializable {
	
	private static final long serialVersionUID = -5603211204764237319L;
	private String id;
	private String name;
	private File icon;
	private String iconFileName;
	private boolean noiconflag;
	private String transition;
	private String assignflag;
	private String resolutionflag;
	private String mailflag;
	private String rssflag;
	private String subject;
	private String description;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public String getLikeName() {
		return "%"+name+"%";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getResolutionflag() {
		return resolutionflag;
	}
	
	public void setResolutionflag(String resolutionflag) {
		this.resolutionflag = resolutionflag;
	}
	
	public String getTransition() {
		return transition;
	}
	
	public void setTransition(String transition) {
		this.transition = transition;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getAssignflag() {
		return assignflag;
	}

	
	public void setAssignflag(String assignflag) {
		this.assignflag = assignflag;
	}

	public String getMailflag() {
		return mailflag;
	}

	public void setMailflag(String mailflag) {
		this.mailflag = mailflag;
	}

	public String getRssflag() {
		return rssflag;
	}

	public void setRssflag(String rssflag) {
		this.rssflag = rssflag;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public boolean isNoiconflag() {
		return noiconflag;
	}

	public void setNoiconflag(boolean noiconflag) {
		this.noiconflag = noiconflag;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

}
