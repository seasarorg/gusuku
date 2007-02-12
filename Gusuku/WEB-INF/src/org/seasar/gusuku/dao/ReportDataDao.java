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
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.gusuku.entity.ReportData;

@S2Dao(bean=ReportData.class)
public interface ReportDataDao {

	public void insert(ReportData reportData);
	public void update(ReportData reportData);
	public void delete(ReportData reportData);
	
	@Query("REPORTID = /*reportid*/ AND FORMID = /*formid*/")
	@Arguments({"reportid","formid"})
	public ReportData findByReportidAndFormid(Long reportid,Long formid);
	
	@Query("REPORTID = /*reportid*/")
	public List<ReportData> findByReportid(Long reportid);
	
	@Query("ID = /*id*/")
	public ReportData findById(Long id);
	
	@Sql("DELETE FROM REPORT_DATA WHERE REPORTID = /*id*/")
	public void deleteByReportid(Long id);
	
}
