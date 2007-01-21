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

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.NoPersistentProperty;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.gusuku.entity.Report;

@S2Dao(bean=Report.class)
public interface ReportDao {
	
	@NoPersistentProperty("rdate")
	public void insert(Report report);
	@NoPersistentProperty("rdate")
	public void update(Report report);
	@NoPersistentProperty("rdate")
	public void updateUnlessNull(Report report);
	public void delete(Report report);

	/**
	 * 指定したユーザーIDにアサインされたレポートリストを取得します
	 * @param assigneeid
	 * @return
	 */
	@Query("Report.ASSIGNEEID = /*assigneeid*/ AND Report.DELFLAG = FALSE ORDER BY Report.ID")
	public List<Report> findByAssigneeid(String assigneeid);
	
	@Query("Report.ASSIGNEEID = /*assigneeid*/ AND Report.STATUSID = /*statusid*/ AND Report.DELFLAG = FALSE ORDER BY Report.ID")
	@Arguments({"assigneeid","statusid"})
	public List<Report> findByAssigneeidAndStatusid(String assigneeid, String statusid);
	
	/**
	 * 指定したユーザーが報告したレポートリストを取得します
	 * @param reporterid
	 * @return
	 */
	@Query("Report.REPORTERID = /*reporterid*/ AND Report.DELFLAG = FALSE ORDER BY Report.ID")
	public List<Report> findByReporterid(String reporterid);

	@Query("Report.ID = /*id*/ AND Report.DELFLAG = FALSE ORDER BY Report.ID")
	public Report findById(String id);

	@Query("Report.PROJECTID = /*projectid*/ /*IF typeid != null */AND Report.TYPEID = /*typeid*/ /*END*/AND Report.DELFLAG = FALSE AND Report.STATUSID != (SELECT STATUSID FROM WORKFLOW_STATUS WHERE WORKFLOWID = /*workflowid*/ AND EFLAG = TRUE) ORDER BY Report.ID")
	@Arguments({"projectid","typeid","workflowid"})
	public List<Report> findByProjectidAndTypeid(String projectid, String typeid,String workflowid);
	
	@Arguments({"projectid","statusid"})
	public int countByProjectidAndStatusid(String projectid,String statusid);
	
	@Arguments({"projectid","typeid","statusid"})
	public int countByProjectidAndTypeidAndStatusid(String projectid, String typeid,String statusid);


}
