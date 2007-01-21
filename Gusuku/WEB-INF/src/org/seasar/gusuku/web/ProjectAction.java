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
package org.seasar.gusuku.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.gusuku.entity.MailCondition;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.TypeScheme;
import org.seasar.gusuku.entity.WorkflowStatus;
import org.seasar.gusuku.helper.MailConditionHelper;
import org.seasar.gusuku.helper.ProjectHelper;
import org.seasar.gusuku.helper.ReportHelper;
import org.seasar.gusuku.helper.TypeHelper;
import org.seasar.gusuku.helper.WorkflowStatusHelper;
import org.seasar.xwork.annotation.Param;
import org.seasar.xwork.annotation.Result;
import org.seasar.xwork.annotation.XWorkAction;

/**
 * プロジェクト
 * @author duran
 *
 */
public class ProjectAction extends GusukuAction {

	private static final long serialVersionUID = -5905800626115458208L;
	private ProjectHelper projectHelper;
	private TypeHelper typeHelper;
	private MailConditionHelper mailConditionHelper;
	private WorkflowStatusHelper workflowStatusHelper;
	private ReportHelper reportHelper;

	private List<Project> entryList;

	private String id;

	private Project project;
	private MailCondition mailCondition;
	
	private ProjectReport projectReport;

	/**
	 * 参加プロジェクト一覧
	 * @return
	 */
	@XWorkAction(name = "project", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/project.html")))
	public String entryList() {

		//参加プロジェクト取得
		entryList = projectHelper.getEntryList(getLoginid());

		return SUCCESS;
	}

	/**
	 * プロジェクト詳細
	 * @return
	 */
	@XWorkAction(name = "project_detail", result = @Result(type = "mayaa", param = @Param(name = "location", value = "/project_detail.html")))
	public String detail() {
		
		

		project = projectHelper.getProject(id,getLoginid());
		
		//レポート作成
		//状態別
		List<Progress> statusProgress = new ArrayList<Progress>();
		
		//開始
		int totalCount = 0;
		WorkflowStatus startStatus = workflowStatusHelper.getStartStatus(Long.toString(project.getWorkflowid()));
		int startCount = reportHelper.getStatusCount(Long.toString(project.getId()),Long.toString(startStatus.getStatusid()));
		
		Progress startProgress = new Progress();
		startProgress.setName(startStatus.getStatus().getName());
		startProgress.setTotal(startCount);
		statusProgress.add(startProgress);
		//経過
		List<WorkflowStatus> processStatus = workflowStatusHelper.getStatusListWithoutStartAndEnd(Long.toString(project.getWorkflowid()));
		for(Iterator ite=processStatus.iterator();ite.hasNext();){
			WorkflowStatus workflowStatus = (WorkflowStatus)ite.next();
			int count = reportHelper.getStatusCount(Long.toString(project.getId()),Long.toString(workflowStatus.getStatusid()));

			totalCount += count;
			Progress progress = new Progress();
			progress.setName(workflowStatus.getStatus().getName());
			progress.setTotal(count);
			statusProgress.add(progress);
		}
		//終了
		WorkflowStatus endStatus = workflowStatusHelper.getEndStatus(Long.toString(project.getWorkflowid()));
		int endCount = reportHelper.getStatusCount(Long.toString(project.getId()),Long.toString(endStatus.getStatusid()));
		
		Progress endProgress = new Progress();
		endProgress.setName(endStatus.getStatus().getName());
		endProgress.setTotal(endCount);
		statusProgress.add(endProgress);
		
		//全体
		totalCount += startCount + endCount;
		Progress totalProgress = new Progress();
		totalProgress.setName("全体");
		totalProgress.setTotal(totalCount);
		totalProgress.setEnd(endCount);
		
		//タイプ別
		List<TypeScheme> typeList = typeHelper.getTypeListWithScheme(Long.toString(project.getTypeid()));
		List<Progress> typeProgress = new ArrayList<Progress>();
		typeProgress.add(totalProgress);
		for(TypeScheme typeScheme : typeList){
			int count = reportHelper.getTypeCount(Long.toString(project.getId()),Long.toString(typeScheme.getTypeid()),null);
			int end =  reportHelper.getTypeCount(Long.toString(project.getId()),Long.toString(typeScheme.getTypeid()),Long.toString(endStatus.getStatusid()));
			Progress progress = new Progress();
			progress.setName(typeScheme.getType().getName());
			progress.setTotal(count);
			progress.setEnd(end);
			typeProgress.add(progress);
		}
		
		projectReport = new ProjectReport();
		projectReport.setTypeProgress(typeProgress);
		projectReport.setStatusProgress(statusProgress);
		
		//メール通知設定
		mailCondition = mailConditionHelper.getMailCondition(getLoginid(),id);

		if(mailCondition == null){
			mailCondition = new MailCondition();
			mailCondition.setComment(1);
			mailCondition.setStart(1);
			mailCondition.setProcess(3);
			mailCondition.setEnd(1);
		}
		return SUCCESS;
	}

	public List<Project> getEntryList() {
		return entryList;
	}

	public void setProjectHelper(ProjectHelper projectHelper) {
		this.projectHelper = projectHelper;
	}

	public Project getProject() {
		return project;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public TypeHelper getTypeHelper() {
		return typeHelper;
	}

	
	public void setTypeHelper(TypeHelper typeHelper) {
		this.typeHelper = typeHelper;
	}

	public MailCondition getMailCondition() {
		return mailCondition;
	}

	public void setMailConditionHelper(MailConditionHelper mailConditionHelper) {
		this.mailConditionHelper = mailConditionHelper;
	}

	
	public void setWorkflowStatusHelper(WorkflowStatusHelper workflowStatusHelper) {
		this.workflowStatusHelper = workflowStatusHelper;
	}
	
	private class ProjectReport{
		
		private Progress totalProgress;
		private List<Progress> typeProgress;
		private List<Progress> statusProgress;
		
		public List<Progress> getStatusProgress() {
			return statusProgress;
		}

		public void setStatusProgress(List<Progress> statusProgress) {
			this.statusProgress = statusProgress;
		}

		public Progress getTotalProgress() {
			return totalProgress;
		}

		public void setTotalProgress(Progress totalProgress) {
			this.totalProgress = totalProgress;
		}

		public List<Progress> getTypeProgress() {
			return typeProgress;
		}

		public void setTypeProgress(List<Progress> typeProgress) {
			this.typeProgress = typeProgress;
		}
		
	}
	
	private class Progress{
		
		private String name;
		private int end;
		private int total;
		private float progress;
		
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public float getProgress() {
			if (total == 0 || end == 0) {
				return 0;
			}
			return (float)end / (float)total * 100;
		}
		public void setProgress(float progress) {
			this.progress = progress;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		
		
	}

	
	public void setReportHelper(ReportHelper reportHelper) {
		this.reportHelper = reportHelper;
	}

	
	public ProjectReport getProjectReport() {
		return projectReport;
	}

}
