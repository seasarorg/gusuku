package org.seasar.gusuku.dto;

import java.io.Serializable;
import java.util.List;

import org.seasar.gusuku.entity.SearchConditionBasic;
import org.seasar.gusuku.entity.SearchConditionHead;


public class SearchDto extends GusukuPagerCondition implements Serializable {
	
	private SearchConditionHead searchConditionHead;
	private List<SearchConditionBasic> searchConditionBasicList;
	
	public List<SearchConditionBasic> getSearchConditionBasicList() {
		return searchConditionBasicList;
	}
	
	public void setSearchConditionBasicList(
			List<SearchConditionBasic> searchConditionBasicList) {
		this.searchConditionBasicList = searchConditionBasicList;
	}
	
	public SearchConditionHead getSearchConditionHead() {
		return searchConditionHead;
	}
	
	public void setSearchConditionHead(SearchConditionHead searchConditionHead) {
		this.searchConditionHead = searchConditionHead;
	}
	

}
