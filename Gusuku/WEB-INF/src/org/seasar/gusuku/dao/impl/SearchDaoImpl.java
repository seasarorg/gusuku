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
package org.seasar.gusuku.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.dao.DaoMetaDataFactory;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.impl.AbstractDao;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.SearchDao;
import org.seasar.gusuku.dto.SearchDto;
import org.seasar.gusuku.entity.Report;
import org.seasar.gusuku.entity.SearchConditionBasic;
import org.seasar.gusuku.entity.SearchConditionCustom;
import org.seasar.gusuku.entity.SearchConditionHead;
import org.seasar.gusuku.util.ParameterUtil;

@S2Dao(bean=Report.class)
public class SearchDaoImpl extends AbstractDao implements SearchDao {
	
	public SearchDaoImpl(DaoMetaDataFactory daoMetaDataFactory){
		super(daoMetaDataFactory);
	}

	//public List<Report> findByPamameter(SearchConditionHead searchConditionHead,List<SearchConditionBasic> searchConditionBasicList) {
	public List<Report> findByPamameter(SearchDto searchDto) {
		
		SearchConditionHead searchConditionHead = searchDto.getSearchConditionHead();
		List<SearchConditionBasic> searchConditionBasicList = searchDto.getSearchConditionBasicList();		
		//条件が存在しない場合は結果を返さない
		if(searchConditionBasicList.size() == 0){
			return new ArrayList<Report>();
		}
		StringBuffer sql = new StringBuffer();
		sql.append("REPORT.DELFLAG = FALSE ");
		
		List<Object> params = new ArrayList<Object>();
		
		int project_count = 0;
		for(Iterator ite_basic = searchConditionBasicList.iterator();ite_basic.hasNext();){
			SearchConditionBasic searchConditionBasic = (SearchConditionBasic)ite_basic.next();
			if(project_count == 0){
				sql.append("AND (");
			}else{
				sql.append("OR ");
			}
			sql.append("(REPORT.PROJECTID = ? ");
			params.add(searchConditionBasic.getProjectid());
			project_count++;
			if(!StringUtil.isEmpty(searchConditionBasic.getTypeid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getTypeid()); 
				sql.append("AND REPORT.TYPEID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getStatusid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getStatusid());
				sql.append("AND REPORT.STATUSID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getPriorityid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getPriorityid());
				sql.append("AND REPORT.PRIORITYID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getAssigneeid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getAssigneeid());
				sql.append("AND REPORT.ASSIGNEEID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getComponentid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getComponentid());
				sql.append("AND REPORT.COMPONENTID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getVersionid())){
				String[] value = ParameterUtil.splitValue(searchConditionBasic.getVersionid());
				sql.append("AND REPORT.VERSIONID IN ("+makeInValue(value.length)+") ");
				addInValue(params,value);
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getTitle())){
				sql.append("AND REPORT.TITLE LIKE ? "); 
				params.add("%"+searchConditionBasic.getTitle()+"%");
			}
			
			if(!StringUtil.isEmpty(searchConditionBasic.getEnvironment())){
				sql.append("AND REPORT.ENVIRONMENT LIKE ? ");
				params.add("%"+searchConditionBasic.getEnvironment()+"%");
			}
			if(!StringUtil.isEmpty(searchConditionBasic.getDetail())){
				sql.append("AND REPORT.DETAIL LIKE ? ");
				params.add("%"+searchConditionBasic.getDetail()+"%");
			}

			if(searchConditionBasic.getDatefrom() != null){
				sql.append("AND REPORT.RDATE >= ? ");
				params.add(searchConditionBasic.getDatefrom());
			}
			if(searchConditionBasic.getDateto() != null){
				sql.append("AND REPORT.RDATE <= ? ");
				params.add(searchConditionBasic.getDateto());
			}
			
			
			List searchConditionCustomList = searchConditionBasic.getCustom();
			if(searchConditionCustomList != null && searchConditionCustomList.size() > 0){
				sql.append("AND REPORT.ID IN (SELECT REPORTDATA.REPORTID ");
				sql.append("FROM REPORT_DATA REPORTDATA ");
				sql.append("WHERE TRUE = TRUE ");
				int custom_count = 0;
				for(Iterator ite_custom = searchConditionCustomList.iterator();ite_custom.hasNext();){
					SearchConditionCustom searchConditionCustom = (SearchConditionCustom)ite_custom.next();
					if(custom_count == 0){
						sql.append("AND (");
					}else{
						sql.append("OR ");
					}
					sql.append("(REPORTDATA.FORMID = ? ");
					params.add(searchConditionCustom.getFormid());
					custom_count++;
					switch(searchConditionCustom.getValuetype()){
					case 1:
						sql.append("AND REPORTDATA.TEXTVALUE LIKE ? ");
						params.add("%"+searchConditionCustom.getTextvalue()+"%");
						
						break;
					case 2:
						
						if(searchConditionCustom.getRangelow() != null){
							sql.append("AND REPORTDATA.RANGELOW >= ? ");
							params.add(searchConditionCustom.getRangelow());
							
						}
						if(searchConditionCustom.getRangehigh() != null){
							sql.append("AND REPORTDATA.RANGEHIGH >= ? ");
							params.add(searchConditionCustom.getRangehigh());
						}
						break;
					case 3:
						if(searchConditionCustom.getDatefrom() != null){
							sql.append("AND REPORTDATA.DATEFROM <= ? ");
							params.add(searchConditionCustom.getDatefrom());
						}
						if(searchConditionCustom.getDateto() != null){
							sql.append("AND REPORTDATA.DATETO >= ? ");
							params.add(searchConditionCustom.getDateto());
						}
						break;
					}
					sql.append(") ");
				}
				sql.append(") ");
				sql.append("GROUP BY REPORTDATA.REPORTID ");
				sql.append("HAVING COUNT(*) = ? ");
				sql.append(")");
				params.add(searchConditionCustomList.size());

			}
			sql.append(") ");
		
		}
		if(project_count > 0){
			sql.append(") ");
		}
		
		//TODO ソート順序をどうするか？
		if(!StringUtil.isEmpty(searchDto.getSort())){
			sql.append("ORDER BY ");

			if(searchDto.getSort().equals("PRIORITY")){
				//優先度ソート
				sql.append("(SELECT SORT FROM PRIORITY_SCHEME WHERE HEADID = PROJECT.PRIORITYID AND REPORT.PRIORITYID = PRIORITYID) ");
			}else if(searchDto.getSort().equals("TYPE")){
				sql.append("(SELECT SORT FROM TYPE_SCHEME WHERE HEADID = PROJECT.TYPEID AND REPORT.TYPEID = TYPEID) ");
			}else if(searchDto.getSort().equals("KEY")){
				if(searchDto.getOrder().equals("DESC")){
					sql.append("REPORT.KEY DESC,REPORT.SEQ ");
				}else{
					sql.append("REPORT.KEY,REPORT.SEQ ");
				}
			}else if(searchDto.getSort().equals("STATUS")){
				sql.append(" (SELECT CASE WHEN SFLAG = TRUE THEN 1 ELSE (CASE WHEN EFLAG = TRUE THEN 99 ELSE ID END) END  FROM WORKFLOW_STATUS WHERE WORKFLOWID = PROJECT.WORKFLOWID AND STATUSID = REPORT.STATUSID) ");
			}else if(searchDto.getSort().equals("TITLE")){
				sql.append("REPORT.TITLE ");
			}else if(searchDto.getSort().equals("ASSIGNEE")){
				sql.append("(SELECT NAME FROM ACCOUNT WHERE ID = REPORT.ASSIGNEEID) ");
			}else if(searchDto.getSort().equals("REPORTER")){
				sql.append("(SELECT NAME FROM ACCOUNT WHERE ID = REPORT.REPORTERID) ");
			}else if(searchDto.getSort().equals("RDATE")){
				sql.append("RDATE ");
			}
			
			if(searchDto.getOrder().equals("DESC")){
				sql.append("DESC");
			}
			
			if(!searchDto.getSort().equals("KEY")){
				sql.append(",REPORT.KEY,REPORT.SEQ  ");
			}
		}else{
			sql.append("ORDER BY ");
			//優先度ソート
			sql.append("(SELECT SORT FROM PRIORITY_SCHEME WHERE HEADID = PROJECT.PRIORITYID AND REPORT.PRIORITYID = PRIORITYID) ");
			
		}
		
		//ホーム表示用
		if(searchConditionHead != null){
			sql.append(" LIMIT " + searchConditionHead.getAmount());
		}else{
			//sql.append(" LIMIT " + searchDto.getLimit() + " OFFSET " + searchDto.getOffset());
		}

		return getEntityManager().find(sql.toString(),params.toArray());
	}
	
	private String makeInValue(int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append("?");
		}
		return sb.toString();
	}
	
	private void addInValue(List<Object> params, String[] value) {
		for (int i = 0; i < value.length; i++) {
			params.add(value[i]);
		}
	}

}
