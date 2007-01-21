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
package org.seasar.gusuku.helper;

import org.seasar.gusuku.dao.ReportDataDao;
import org.seasar.gusuku.entity.ReportData;

/**
 * 報告データ（拡張）に関するHelperクラス
 * @author duran
 *
 */
public class ReportDataHelper {
	
	private ReportDataDao reportDataDao;
	
	/**
	 * 指定した報告ID・フォームIDに従う報告データを取得する
	 * @param reportid 報告ID
	 * @param formid フォームID
	 * @return 報告データ
	 */
	public ReportData getReportData(String reportid,String formid){
		return reportDataDao.findByReportidAndFormid(reportid,formid);
	}
	
	/**
	 * 指定した報告データIDに従う報告データを取得する
	 * @param id 報告データID
	 * @return 報告データ
	 */
	public ReportData getReportData(String id){
		return reportDataDao.findById(id);
	}

	public void setReportDataDao(ReportDataDao reportDataDao) {
		this.reportDataDao = reportDataDao;
	}
	
	

}
