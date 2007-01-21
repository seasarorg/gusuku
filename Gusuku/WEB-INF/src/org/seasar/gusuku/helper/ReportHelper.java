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

import java.util.List;

import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dao.ReportDao;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.Report;
import org.seasar.gusuku.exception.EntityNotFoundException;
import org.seasar.gusuku.exception.EntryProjectException;

/**
 * 報告データ（基本）に関するHelperクラス
 * @author duran
 *
 */
public class ReportHelper {
	
	private ReportDao reportDao;
	private ProjectDao projectDao;
	
	/**
	 * 指定した報告IDの報告データを取得する
	 * @param id 報告ID
	 * @param accountid アカウントID
	 * @return 報告データ
	 */
	public Report getReport(String id,String accountid){
		//対象の報告が自分の属するプロジェクトかどうか調べる
		
		Report report =reportDao.findById(id);
		if(report == null){
			throw new EntityNotFoundException(id);
		}
		Project project = projectDao.findByIdAndAccountid(Long.toString(report.getProjectid()),accountid);
		if(project == null){
			throw new EntryProjectException(Long.toString(report.getProjectid()));
		}
		return report; 
	}
	
	/**
	 * 指定したユーザーIDに割り当てられた報告一覧を取得する
	 * @param id 対象ユーザーID
	 * @return 報告一覧
	 */
	public List<Report> getAssignList(String id){
		return reportDao.findByAssigneeid(id);
	}
	
	/**
	 * 指定したユーザーIDに割り当てられ、状態がオープンの報告一覧を取得する
	 * @param id ユーザーID
	 * @return 報告一覧
	 */
	public List<Report> getOpenList(String id){
		return reportDao.findByAssigneeidAndStatusid(id,"1");
	}

	/**
	 * 指定したユーザーID（報告者）に従う報告一覧を取得する
	 * @param id ユーザーID
	 * @return 報告一覧
	 */
	public List<Report> getReportList(String id){
		return reportDao.findByReporterid(id);
	}

	/**
	 * 指定したプロジェクトID・タイプに従う報告一覧を取得する
	 * @param projectid プロジェクトID
	 * @param typeid タイプID
	 * @return 報告一覧
	 */
	public List<Report> getProjectTypeReportList(String projectid,String typeid,String workflowid){
		return reportDao.findByProjectidAndTypeid(projectid,typeid,workflowid);
	}
	
	/**
	 * 指定したプロジェクト・ステータスの件数を取得する
	 * @param projectid プロジェクトID
	 * @param statusid ステータスID
	 * @return 件数
	 */
	public int getStatusCount(String projectid,String statusid){
		return reportDao.countByProjectidAndStatusid(projectid,statusid);
	}
	
	/**
	 * 指定したプロジェクト・タイプの件数を取得する
	 * @param projectid プロジェクトID
	 * @param typeid タイプID
	 * @param statusid 終了ステータス
	 * @return 件数
	 */
	public int getTypeCount(String projectid,String typeid,String statusid){
		return reportDao.countByProjectidAndTypeidAndStatusid(projectid,typeid,statusid);
	}
	
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

}
