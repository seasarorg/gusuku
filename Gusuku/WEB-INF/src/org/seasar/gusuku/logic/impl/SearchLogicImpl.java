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
package org.seasar.gusuku.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.dao.CustomFormDetailDao;
import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dao.SearchConditionBasicDao;
import org.seasar.gusuku.dao.SearchConditionCustomDao;
import org.seasar.gusuku.dao.SearchConditionHeadDao;
import org.seasar.gusuku.dao.SearchDao;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.SearchConditionBasic;
import org.seasar.gusuku.entity.SearchConditionCustom;
import org.seasar.gusuku.entity.SearchConditionHead;
import org.seasar.gusuku.logic.SearchLogic;
import org.seasar.gusuku.util.ParameterUtil;

public class SearchLogicImpl implements SearchLogic {
	
	private SearchConditionHeadDao searchConditionHeadDao;
	private SearchConditionBasicDao searchConditionBasicDao;
	private SearchConditionCustomDao searchConditionCustomDao;

	private SearchDao searchDao;
	private ProjectDao projectDao;
	private CustomFormDetailDao customFormDetailDao;
	
	@Aspect("j2ee.requiredTx")
	public void saveCondition(Map<String,String[]> parameters, Long accountid) {
		
		String id = ParameterUtil.getParameterValue(parameters,"id");
		String name = ParameterUtil.getParameterValue(parameters,"name");
		
		Long[] projectids = ParameterUtil.getParameterLongArrayValue(parameters,"projectid");
		
		SearchConditionHead maxSortHead= searchConditionHeadDao.findMaxSort(accountid);
		int sortMax = 1;
		if(maxSortHead != null){
			sortMax = maxSortHead.getSort() + 1;
		}
		//ヘッダ保存
		SearchConditionHead searchConditionHead = new SearchConditionHead();
		searchConditionHead.setAccountid(accountid);
		searchConditionHead.setName(name);
		searchConditionHead.setSort(sortMax);
		//TODO デフォルト値をどうする？
		searchConditionHead.setAmount(10);
		searchConditionHead.setVisible(true);
		searchConditionHeadDao.insert(searchConditionHead);
		
		for(Long projectid:projectids){
			SearchConditionBasic searchConditionBasic = getBasicCondition(parameters,projectid);
			Project project = projectDao.findById(projectid);
			List<CustomFormDetail> customFormList = customFormDetailDao.findByFormheadid(project.getFormid());
			List<SearchConditionCustom> conditionCustomList = new ArrayList<SearchConditionCustom>();
			for(CustomFormDetail customFormDetail:customFormList){
				SearchConditionCustom searchConditionCustom =getCustomCondition(parameters,customFormDetail,projectid);
				if(searchConditionCustom.getFormid() != null){
					conditionCustomList.add(searchConditionCustom);
				}
			}
			if(StringUtil.isEmpty(id)){
				//新規
				//Basic
				searchConditionBasic.setConditionheadid(searchConditionHead.getId());
				searchConditionBasicDao.insert(searchConditionBasic);
				//Custom
				long basicid = searchConditionBasic.getId();
				SearchConditionCustom[] searchConditionCustoms = (SearchConditionCustom[])conditionCustomList.toArray(new SearchConditionCustom[0]);
				for(SearchConditionCustom searchConditionCustom:searchConditionCustoms){
					searchConditionCustom.setConditionbasicid(basicid);
				}
				searchConditionCustomDao.insertBatch(searchConditionCustoms);
			}else{
				//TODO ペンディング
				/*
				//更新

				//Basic
				//IDが特定できてない
				searchConditionBasic.setConditionheadid(searchConditionHead.getId());
				searchConditionBasicDao.update(searchConditionBasic);
				//Custom
				long basicid = searchConditionBasic.getId();
				SearchConditionCustom[] searchConditionCustoms = (SearchConditionCustom[])conditionCustomList.toArray(new SearchConditionCustom[0]);
				for(int j=0;j<searchConditionCustoms.length;j++){
					//IDが特定できてない
					searchConditionCustoms[j].setConditionbasicid(basicid);
				}
				searchConditionCustomDao.updateBatch(searchConditionCustoms);
				*/
			}
		}
		

	}
	

	public List search(SearchConditionHead searchConditionHead,Map<String,String[]> parameters,Long accountid) {

		Long[] projectids = ParameterUtil.getParameterLongArrayValue(parameters,"projectid");
		List<SearchConditionBasic> searchConditionBasicList = new ArrayList<SearchConditionBasic>();
		if(projectids != null){
			for(Long projectid : projectids){
				Project project = projectDao.findByIdAndAccountid(projectid,accountid);
				//過去に所属していたプロジェクトｊに関わるものを除く
				if(project != null){
					SearchConditionBasic searchConditionBasic = getBasicCondition(parameters,projectid);
					List<CustomFormDetail> customFormList = customFormDetailDao.findByFormheadid(project.getFormid());
					List<SearchConditionCustom> conditionCustomList = new ArrayList<SearchConditionCustom>();
					for(Iterator ite = customFormList.iterator();ite.hasNext();){
						CustomFormDetail customFormDetail = (CustomFormDetail)ite.next();
						SearchConditionCustom searchConditionCustom =getCustomCondition(parameters,customFormDetail,projectid);
						if(searchConditionCustom.getFormid() != null){
							conditionCustomList.add(searchConditionCustom);
						}
					}
					searchConditionBasic.setCustom(conditionCustomList);
					searchConditionBasicList.add(searchConditionBasic);
				}
			}
		}
		
		return searchDao.findByPamameter(searchConditionHead,searchConditionBasicList);
	}
	
	
	private SearchConditionBasic getBasicCondition(Map<String,String[]> parameters,Long projectid){
		SearchConditionBasic searchConditionBasic = new SearchConditionBasic();
		//String id =  ParameterUtil.getParameterValue(parameters,"basic_id_"+projectid);
		String title = ParameterUtil.getParameterValue(parameters,"basic_title_"+projectid);
		String typeid = ParameterUtil.getParameterValue(parameters,"basic_typeid_"+projectid);
		String statusid = ParameterUtil.getParameterValue(parameters,"basic_statusid_"+projectid);
		String priorityid = ParameterUtil.getParameterValue(parameters,"basic_priorityid_"+projectid);
		String assigneeid = ParameterUtil.getParameterValue(parameters,"basic_assigneeid_"+projectid);
		String environment = ParameterUtil.getParameterValue(parameters,"basic_environment_"+projectid);
		String detail = ParameterUtil.getParameterValue(parameters,"basic_detail_"+projectid);
		Date datefrom = ParameterUtil.getParameterDateValue(parameters,"basic_datefrom_"+projectid);
		Date dateto = ParameterUtil.getParameterDateValue(parameters,"basic_dateto_"+projectid);
		//searchConditionBasic.setId(Long.parseLong(id));
		searchConditionBasic.setProjectid(projectid);
		searchConditionBasic.setTitle(title);
		searchConditionBasic.setTypeid(typeid);
		searchConditionBasic.setStatusid(statusid);
		searchConditionBasic.setPriorityid(priorityid);
		searchConditionBasic.setAssigneeid(assigneeid);
		searchConditionBasic.setEnvironment(environment);
		searchConditionBasic.setDetail(detail);
		searchConditionBasic.setDatefrom(datefrom);
		searchConditionBasic.setDateto(dateto);
		
		return searchConditionBasic;
	}
	
	private SearchConditionCustom getCustomCondition(Map<String,String[]> parameters,CustomFormDetail customFormDetail,Long projectid){
		SearchConditionCustom searchConditionCustom = new SearchConditionCustom();
		
		//String id = ParameterUtil.getParameterValue(parameters,"custom_id_"+customFormDetail.getId()+"_"+projectid);
		//searchConditionCustom.setId(Long.parseLong(id));
		
		searchConditionCustom.setValuetype(customFormDetail.getValuetype());
		switch(customFormDetail.getValuetype() ){
		case 1:
			
			String textvalue = ParameterUtil.getParameterValue(parameters,"custom_"+customFormDetail.getId()+"_"+projectid);
			if(!StringUtil.isEmpty(textvalue)){
				searchConditionCustom.setFormid(customFormDetail.getId());
				searchConditionCustom.setTextvalue(textvalue);
			}
			break;
		case 2:
			String low = ParameterUtil.getParameterValue(parameters,"custom_low_"+customFormDetail.getId()+"_"+projectid);
			String high = ParameterUtil.getParameterValue(parameters,"custom_high_"+customFormDetail.getId()+"_"+projectid);
			boolean range = false;
			if(!StringUtil.isEmpty(low)){
				searchConditionCustom.setRangelow(Long.valueOf(low));
				range = true;
			}
			if(!StringUtil.isEmpty(high)){
				searchConditionCustom.setRangehigh(Long.valueOf(high));
				range = true;
			}
			if(range){
				searchConditionCustom.setFormid(customFormDetail.getId());
			}
			break;
		case 3:
			Date from = ParameterUtil.getParameterDateValue(parameters,"custom_from_"+customFormDetail.getId()+"_"+projectid);
			Date to = ParameterUtil.getParameterDateValue(parameters,"custom_to_"+customFormDetail.getId()+"_"+projectid);
			boolean date = false;
			if(from != null){
				searchConditionCustom.setDatefrom(from);
				date = true;
			}
			if(to != null){
				searchConditionCustom.setDateto(to);
				date = true;
			}
			if(date){
				searchConditionCustom.setFormid(customFormDetail.getId());
			}
			break;
		}
		return searchConditionCustom;
	}
	
	public void setSearchConditionBasicDao(
			SearchConditionBasicDao searchConditionBasicDao) {
		this.searchConditionBasicDao = searchConditionBasicDao;
	}

	public void setSearchConditionCustomDao(
			SearchConditionCustomDao searchConditionCustomDao) {
		this.searchConditionCustomDao = searchConditionCustomDao;
	}


	public void setSearchConditionHeadDao(
			SearchConditionHeadDao searchConditionHeadDao) {
		this.searchConditionHeadDao = searchConditionHeadDao;
	}


	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}


	public void delete(Long accountid,Long[] delids) {
		if(delids != null && delids.length > 0){
			for(Long delid : delids){
				SearchConditionHead searchConditionHead = new SearchConditionHead();
				searchConditionHead.setId(delid);
				searchConditionHeadDao.delete(searchConditionHead);
			}
			List<SearchConditionHead> list = searchConditionHeadDao.findByAccountid(accountid);
			int sort = 1;
			for(SearchConditionHead searchConditionHead : list){
				if(searchConditionHead.getSort() != sort){
					searchConditionHead.setSort(sort++);
					searchConditionHeadDao.update(searchConditionHead);
				}else{
					sort++;
				}
			}
		}
		
	}


	public void update(Long[] visible, Map amount) {
		
		Map<Long,SearchConditionHead> cache = new HashMap<Long,SearchConditionHead>();

		//表示件数を取得
		if(amount != null && amount.size() > 0){
			for(Iterator ite = amount.keySet().iterator();ite.hasNext();){
				String id = (String)ite.next();
				SearchConditionHead searchConditionHead = null;
				if(cache.containsKey(id)){
					searchConditionHead = (SearchConditionHead)cache.get(id);
					String tmp = ((String[])amount.get(id))[0];
					searchConditionHead.setAmount(Integer.parseInt(tmp));
					searchConditionHead.setVisible(false);
				}else{
					searchConditionHead = searchConditionHeadDao.findById(Long.parseLong(id));
					String tmp = ((String[])amount.get(id))[0];
					searchConditionHead.setAmount(Integer.parseInt(tmp));
					searchConditionHead.setVisible(false);
					cache.put(Long.parseLong(id),searchConditionHead);
				}
			}
		}
		
		//キーを取得
		if(visible != null && visible.length > 0){
			for(int i=0;i<visible.length;i++){
				Long id = visible[i];
				SearchConditionHead searchConditionHead = null;
				if(cache.containsKey(id)){
					searchConditionHead = (SearchConditionHead)cache.get(id);
					searchConditionHead.setVisible(true);
				}else{
					searchConditionHead = searchConditionHeadDao.findById(id);
					searchConditionHead.setVisible(true);
					cache.put(id,searchConditionHead);
				}
			}
		}

		//ロードして設定してアップデート
		if(cache.size() > 0){
			SearchConditionHead[] updateList = (SearchConditionHead[])cache.values().toArray(new SearchConditionHead[0]);
			searchConditionHeadDao.updateBatch(updateList);
		}
		
	}
	
	@Aspect("j2ee.requiredTx")
	public void sortUp(Long id,Long accountid){
		//↑
		//対象となる行を取得
		SearchConditionHead target = searchConditionHeadDao.findById(id);
		//対象となる行の一つ手前を取得
		SearchConditionHead before = searchConditionHeadDao.findBeforeById(id,accountid);
		//ソート値入替え
		int orgSort = target.getSort();
		target.setSort(before.getSort());
		before.setSort(orgSort);
		//保存
		searchConditionHeadDao.update(target);
		searchConditionHeadDao.update(before);
	}
	
	@Aspect("j2ee.requiredTx")
	public void sortDown(Long id,Long accountid){
		//↑
		//対象となる行を取得
		SearchConditionHead target = searchConditionHeadDao.findById(id);
		//対象となる行の一つ後を取得
		SearchConditionHead after = searchConditionHeadDao.findAfterById(id,accountid);
		//ソート値入替え
		int orgSort = target.getSort();
		target.setSort(after.getSort());
		after.setSort(orgSort);
		//保存
		searchConditionHeadDao.update(target);
		searchConditionHeadDao.update(after);
	}
	
	public void load(Map<String,String[]> parameters,Long conditionid){
		
		if(conditionid != null){
			SearchConditionHead searchConditionHead = searchConditionHeadDao.findById(conditionid);
			Long accountid = searchConditionHead.getAccountid();
			ParameterUtil.putParameterValue(parameters,"id",searchConditionHead.getId());
			ParameterUtil.putParameterValue(parameters,"name",searchConditionHead.getName());

			List searchConditionBasicList = searchConditionBasicDao.findByConditionheadid(searchConditionHead.getId());
			
			List<Long> projectidList = new ArrayList<Long>();
			for(Iterator ite_basic = searchConditionBasicList.iterator() ;ite_basic.hasNext();){
				SearchConditionBasic searchConditionBasic = (SearchConditionBasic)ite_basic.next();
				Long projectid = searchConditionBasic.getProjectid();
				Project project = projectDao.findByIdAndAccountid(projectid,accountid);
				//過去に所属していたプロジェクトｊに関わるものを除く
				if(project != null){
					projectidList.add(projectid);
					ParameterUtil.putParameterValue(parameters,"basic_id_"+projectid,searchConditionBasic.getId());
					ParameterUtil.putParameterValue(parameters,"basic_typeid_"+projectid,searchConditionBasic.getTypeid());
					ParameterUtil.putParameterValue(parameters,"basic_statusid_"+projectid,searchConditionBasic.getStatusid());
					ParameterUtil.putParameterValue(parameters,"basic_priorityid_"+projectid,searchConditionBasic.getPriorityid());
					ParameterUtil.putParameterValue(parameters,"basic_assigneeid_"+projectid,searchConditionBasic.getAssigneeid());
					ParameterUtil.putParameterValue(parameters,"basic_title_"+projectid,searchConditionBasic.getTitle());
					ParameterUtil.putParameterValue(parameters,"basic_environment_"+projectid,searchConditionBasic.getEnvironment());
					ParameterUtil.putParameterValue(parameters,"basic_datefrom_"+projectid,searchConditionBasic.getDatefrom());
					ParameterUtil.putParameterValue(parameters,"basic_dateto_"+projectid,searchConditionBasic.getDateto());
					ParameterUtil.putParameterValue(parameters,"basic_detail_"+projectid,searchConditionBasic.getDetail());
					List searchConditionCustomList = searchConditionCustomDao.findByConditionbasicid(searchConditionBasic.getId());
	
					for(Iterator ite_custom = searchConditionCustomList.iterator();ite_custom.hasNext();){
						SearchConditionCustom searchConditionCustom = (SearchConditionCustom) ite_custom.next();
						ParameterUtil.putParameterValue(parameters,"custom_id_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getId());
						switch(searchConditionCustom.getCustomFormDetail().getValuetype()){
						case 1:
							if(searchConditionCustom.getCustomFormDetail().getValueid() != null){
								ParameterUtil.putParameterValue(parameters,"custom_"+searchConditionCustom.getFormid()+"_"+projectid,ParameterUtil.splitValue(searchConditionCustom.getTextvalue()));
							}else{
								ParameterUtil.putParameterValue(parameters,"custom_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getTextvalue());
							}
							break;
						case 2:
							ParameterUtil.putParameterValue(parameters,"custom_low_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getRangelow());
							ParameterUtil.putParameterValue(parameters,"custom_high_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getRangehigh());
							break;
						case 3:
							ParameterUtil.putParameterValue(parameters,"custom_from_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getDatefrom());
							ParameterUtil.putParameterValue(parameters,"custom_to_"+searchConditionCustom.getFormid()+"_"+projectid,searchConditionCustom.getDateto());
							break;
						}
						
					}
				}
			}
			
			ParameterUtil.putParameterValue(parameters,"projectid",(Long[])projectidList.toArray(new Long[0]));
		}
	}


	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}


	
	public void setCustomFormDetailDao(CustomFormDetailDao customFormDetailDao) {
		this.customFormDetailDao = customFormDetailDao;
	}



}
