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

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.framework.util.StringUtil;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.CommentDao;
import org.seasar.gusuku.dao.CustomFormDetailDao;
import org.seasar.gusuku.dao.MailConditionDao;
import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.dao.ReportDao;
import org.seasar.gusuku.dao.ReportDataDao;
import org.seasar.gusuku.dao.StatusHistoryDao;
import org.seasar.gusuku.dao.TypeDao;
import org.seasar.gusuku.dao.WorkflowStatusDao;
import org.seasar.gusuku.dto.ReportDto;
import org.seasar.gusuku.dxo.ReportDxo;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.entity.Comment;
import org.seasar.gusuku.entity.CustomFormDetail;
import org.seasar.gusuku.entity.MailCondition;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.entity.Report;
import org.seasar.gusuku.entity.ReportData;
import org.seasar.gusuku.entity.StatusHistory;
import org.seasar.gusuku.entity.Type;
import org.seasar.gusuku.entity.WorkflowStatus;
import org.seasar.gusuku.logic.ReportLogic;
import org.seasar.gusuku.service.MailService;
import org.seasar.gusuku.util.FileUploadUtil;
import org.seasar.gusuku.util.FreemarkerUtil;
import org.seasar.gusuku.util.ParameterUtil;
import org.seasar.gusuku.util.PropertyUtil;

import com.ozacc.mail.Mail;

public class ReportLogicImpl implements ReportLogic {

	private ReportDao reportDao;
	private ReportDataDao reportDataDao;
	private ProjectDao projectDao;
	private TypeDao typeDao;
	private CustomFormDetailDao customFormDetailDao;
	private StatusHistoryDao statusHistoryDao;
	private WorkflowStatusDao workflowStatusDao;
	private ReportDxo reportDxo;
	private MailConditionDao mailConditionDao;
	
	private CommentDao commentDao;
	
	private MailService mailService;
	
	@Aspect("j2ee.requiredTx")
	public void registration(ReportDto reportDto,Map parameters,String reporterid) {
		
		reportDto.setReporterid(reporterid);
		
		Report report = reportDxo.convert(reportDto);
		
		boolean update = !StringUtil.isEmpty(reportDto.getId());
		
		
		Project project = projectDao.findByIdForUpdate(reportDto.getProjectid());
		Type type = typeDao.findById(reportDto.getTypeid());
		
		if(!update){
			//KEY値生成
			//プロジェクトのカウンターをインクリメント
			project.setCounter(project.getCounter()+1);
			projectDao.update(project);
			//インクリメント値を取得
			//KEY + インクリメント値　を生成
			//report へセット
			report.setKey(project.getKey()+"-" + project.getCounter());
			report.setMessageid("");
			//ワークフローの初期状態をセット
			WorkflowStatus workflowStatus =workflowStatusDao.findStartStatusById(Long.toString(project.getWorkflowid()));
			report.setStatusid(workflowStatus.getStatusid());

			reportDao.insert(report);

		}else{
			Report org = reportDao.findById(reportDto.getId());
			report.setKey(org.getKey());
			report.setResolutionid(org.getResolutionid());
			report.setStatusid(org.getStatusid());
			report.setUdate(new Date());
			reportDao.updateUnlessNull(report);

			//カスタムフィールド値を全て破棄
			//TODO ファイルはどうする？
			reportDataDao.deleteByReportid(reportDto.getId());
		}

		report = reportDao.findById(Long.toString(report.getId()));
		
		//カスタムフィールド値を保存
		List<CustomFormDetail> formList = customFormDetailDao.findByFormheadid(Long.toString(project.getFormid()));
		for(Iterator ite = formList.iterator();ite.hasNext();){
			CustomFormDetail form = (CustomFormDetail)ite.next();
			String formName = "custom"+form.getId();

			ReportData data = new ReportData();
			data.setReportid(report.getId());
			data.setFormid(form.getId());

			if (!form.getFormType().getTagname().equals("file")) {
				String value = ParameterUtil.getParameterValue(parameters,formName);
				if(StringUtil.isEmpty(value)){
					continue;
				}
				//ファイルアップロード以外
				switch (form.getValuetype()) {
					case 1:
						data.setTextvalue(value);
						break;
					case 2:
						data.setNumericvalue(new Long(value));
						break;
					case 3:
						try {
							SimpleDateFormat format = new SimpleDateFormat(
									"yyyy/MM/dd");
							Date datevalue = format.parse(value);
							data.setDatevalue(datevalue);
						} catch (ParseException e) {
							data.setTextvalue(value);
						}
						break;
				}
				reportDataDao.insert(data);
			}else{
				File file = ParameterUtil.getParameterFileValue(parameters,formName);
				if(file == null){
					continue;
				}
				//ファイルアップロードの場合
				if(file != null){
					String originalName = ParameterUtil.getParameterValue(parameters,formName+"FileName");
					data.setTextvalue(originalName);
					
					reportDataDao.insert(data);	
	
					String uploaddir = PropertyUtil.getProperty(GusukuConstant.UPLOAD_DIR_KEY);
					FileUploadUtil.save(file,uploaddir,Long.toString(data.getId()));
					
				}
				
			}
			
		}
		if(!update){
			updateStatusHistory(report,reporterid);
		}
		sendMail(report);
	}
	
	@Aspect("j2ee.requiredTx")
	public void addComment(Map parameters,Account writer) {
		String id = ParameterUtil.getParameterValue(parameters,"id");
		String commentValue = ParameterUtil.getParameterValue(parameters,"comment");
		File file =  ParameterUtil.getParameterFileValue(parameters,"comment_file");
		if (!StringUtil.isEmpty(commentValue.trim())) {
								
			Comment comment = new Comment();
			comment.setReportid(Long.parseLong(id));
			comment.setWriterid(writer.getId());
			comment.setComment(commentValue);
			
			if(file != null){
				String originalName = ParameterUtil.getParameterValue(parameters,"comment_file"+"FileName");
				comment.setFilename(originalName);
			}
			commentDao.insert(comment);
			
			if(file != null){
				String uploaddir = PropertyUtil.getProperty(GusukuConstant.UPLOAD_DIR_KEY);
				FileUploadUtil.save(file,uploaddir,"cmt"+comment.getId());
				
			}
			
			Report report = reportDao.findById(id);
			
			List<MailCondition> pickList = mailConditionDao.findMailList(Long.toString(report.getProjectid()));
			List<Mail> sendList = new ArrayList<Mail>();
			
			
			//親コメントを取得
			
			Comment parentComment = commentDao.findParentComment(id,Long.toString(comment.getId()));
			String references = "";
			if(parentComment != null){
				references = parentComment.getMessageid();
			}else{
				references = report.getMessageid();
			}
			
			
			//プロジェクトにメールアドレスが設定されている場合は、そのメールアドレスをToに設定し、
			//送信対象ユーザーをBccに設定する。
			//設定されていない場合は、個々のユーザーをToとして送信する。
			String mailaddr = report.getProject().getMailaddr();
			
			if(StringUtil.isEmpty(mailaddr)){
				String from = PropertyUtil.getProperty(GusukuConstant.MAIL_FROM);
				for (MailCondition mailCondition : pickList) {
					Mail mail = new Mail();
					mail.addTo(mailCondition.getAccount().getMailaddr());
					
					if(!StringUtil.isEmpty(from)){
						mail.setFrom(from);
					}
					mail.setSubject("["+report.getKey()+"] コメントが登録されました " + report.getTitle());
					Map params = new HashMap();
					params.put("report",report);
					params.put("comment",commentValue);
					params.put("writer",writer.getName());
					mail.setText(FreemarkerUtil.merge(params,FreemarkerUtil.TEMPLATE_COMMENT));
					//mail.setText(commentValue);
					
					
					//親記事がある場合は親のMessage-IDをReferencesにセット
					if(!StringUtil.isEmpty(references)){
						mail.addHeader("References",references);
					}

					addMailList(report, sendList, mailCondition.getComment(), mail,mailCondition.getAccountid());
				}
			}else{
				Mail mail = new Mail();
				mail.addTo(mailaddr);
				for (MailCondition mailCondition : pickList) {
					addBcc(report,mail,mailCondition.getComment(),mailCondition.getAccount().getMailaddr(),mailCondition.getAccountid());
				}
				mail.setFrom(PropertyUtil.getProperty(GusukuConstant.MAIL_FROM));
				mail.setSubject("["+report.getKey()+"] コメントが登録されました " + report.getTitle());
				Map params = new HashMap();
				params.put("report",report);
				params.put("comment",commentValue);
				params.put("writer",writer.getName());
				mail.setText(FreemarkerUtil.merge(params,FreemarkerUtil.TEMPLATE_COMMENT));
				//mail.setText(commentValue);
				//親記事がある場合は親のMessage-IDをReferencesにセット
				if(!StringUtil.isEmpty(references)){
					mail.addHeader("References",references);
				}
				sendList.add(mail);
			}
			
			Mail[] mailArray = (Mail[])sendList.toArray(new Mail[0]);
			mailService.send(mailArray);
			
			if(!StringUtil.isEmpty(mailaddr)){
				Map headers = mailArray[0].getHeaders();
				if(headers != null){
					String messageid = (String)headers.get("Message-ID");
					comment.setMessageid(messageid);
					commentDao.update(comment);
				}
			}
			//System.out.println("******コメントメール送信******");
		}
	}
	
	

	public void deleteComment(Map parameters) {
		String id = ParameterUtil.getParameterValue(parameters,"commentid");
		
		if(!StringUtil.isEmpty(id)){
			commentDao.updateDelflag(id);
		}
	}

	@Aspect("j2ee.requiredTx")
	public void changeStatus(ReportDto reportDto,String changerid) {
		Report report = reportDao.findById(reportDto.getId());
		report.setStatusid(Long.parseLong(reportDto.getNextstatusid()));
		if(!StringUtil.isEmpty(reportDto.getAssigneeid())){
			report.setAssigneeid(Long.parseLong(reportDto.getAssigneeid()));
		}
		if(!StringUtil.isEmpty(reportDto.getResolutionid())){
			report.setResolutionid(Long.parseLong(reportDto.getResolutionid()));
		}
		reportDao.update(report);
		
		updateStatusHistory(report,changerid);
		sendMail(report);
	}
	
	public void load(Map parameters,String reportid){
		//基本情報取得
		Report report = reportDao.findById(reportid);
		ParameterUtil.putParameterValue(parameters,"title",report.getTitle());
		ParameterUtil.putParameterValue(parameters,"priorityid",report.getPriorityid());
		ParameterUtil.putParameterValue(parameters,"detail",report.getDetail());
		ParameterUtil.putParameterValue(parameters,"assigneeid",report.getAssigneeid());
		ParameterUtil.putParameterValue(parameters,"environment",report.getEnvironment());
		ParameterUtil.putParameterValue(parameters,"typeid",report.getTypeid());
		ParameterUtil.putParameterValue(parameters,"projectid",report.getProjectid());
		ParameterUtil.putParameterValue(parameters,"id",reportid);
		
		//拡張情報取得
		List<ReportData> custom = reportDataDao.findByReportid(reportid);
		
		for(ReportData reportData:custom){
			switch(reportData.getCustomFormDetail().getValuetype()){
				case 1:
					ParameterUtil.putParameterValue(parameters,"custom"+reportData.getFormid(),reportData.getTextvalue());
					break;
				case 2:
					ParameterUtil.putParameterValue(parameters,"custom"+reportData.getFormid(),reportData.getNumericvalue());
					break;
				case 3:
					ParameterUtil.putParameterValue(parameters,"custom"+reportData.getFormid(),reportData.getDatevalue());
					break;
			}
		}
		
	}
	
	private void updateStatusHistory(Report report,String changerid){
		//状態履歴登録
		StatusHistory statusHistory = new StatusHistory();
		statusHistory.setChangerid(Long.parseLong(changerid));
		statusHistory.setStatusid(report.getStatusid());
		statusHistory.setReportid(report.getId());
		statusHistoryDao.insert(statusHistory);
	}
	
	private void sendMail(Report report){
		//Status status = statusDao.findById(Long.toString(report.getStatusid()));
		WorkflowStatus workflowStatus = workflowStatusDao.findByWorkflowidAndStatusid(Long.toString(report.getProject().getWorkflowid()),Long.toString(report.getStatusid()));
		
		if(workflowStatus.getStatus().isMailflag()){
			//メール送信
			//このステータスが開始・経過・終了なのかを判断する必要アリ
			
			List<MailCondition> pickList = mailConditionDao.findMailList(Long.toString(report.getProjectid()));
			List<Mail> sendList = new ArrayList<Mail>();
			
			String mailaddr = report.getProject().getMailaddr();
			if(StringUtil.isEmpty(mailaddr)){
				String from = PropertyUtil.getProperty(GusukuConstant.MAIL_FROM);
				for (MailCondition mailCondition : pickList) {
					Mail mail = new Mail();
					mail.addTo(mailCondition.getAccount().getMailaddr());
					if(!StringUtil.isEmpty(from)){
						mail.setFrom(from);
					}
					mail.setSubject("["+report.getKey()+"] " + workflowStatus.getStatus().getSubject() + " "+report.getTitle());
					Map params = new HashMap();
					params.put("report",report);
					params.put("next",workflowStatus.getStatus());
					mail.setText(FreemarkerUtil.merge(params,FreemarkerUtil.TEMPLATE_REPORT));
					//mail.setText(workflowStatus.getStatus().getName()+"へ変更");
	
					if (workflowStatus.isSflag()) {
						addMailList(report, sendList, mailCondition.getStart(), mail,mailCondition.getAccountid());
					}else if (workflowStatus.isEflag()) {
						addMailList(report, sendList, mailCondition.getEnd(), mail,mailCondition.getAccountid());
					}else{
						addMailList(report, sendList, mailCondition.getProcess(), mail,mailCondition.getAccountid());
					}
				}
			}else{
				Mail mail = new Mail();
				mail.addTo(mailaddr);
				for (MailCondition mailCondition : pickList) {
					if (workflowStatus.isSflag()) {
						addBcc(report,mail,mailCondition.getStart(),mailCondition.getAccount().getMailaddr(),mailCondition.getAccountid());
					}else if (workflowStatus.isEflag()) {
						addBcc(report,mail,mailCondition.getEnd(),mailCondition.getAccount().getMailaddr(),mailCondition.getAccountid());
					}else{
						addBcc(report,mail,mailCondition.getProcess(),mailCondition.getAccount().getMailaddr(),mailCondition.getAccountid());
					}
				}
				mail.setFrom(PropertyUtil.getProperty(GusukuConstant.MAIL_FROM));
				mail.setSubject("["+report.getKey()+"] " + workflowStatus.getStatus().getSubject() + " "+report.getTitle());
				//mail.setText(workflowStatus.getStatus().getName()+"へ変更");
				Map params = new HashMap();
				params.put("report",report);
				params.put("next",workflowStatus.getStatus());
				mail.setText(FreemarkerUtil.merge(params,FreemarkerUtil.TEMPLATE_REPORT));
				
				sendList.add(mail);
			}
			Mail[] mailArray = (Mail[])sendList.toArray(new Mail[0]);
			mailService.send(mailArray);
			
			if(!StringUtil.isEmpty(mailaddr)){
				Map headers = mailArray[0].getHeaders();
				if(headers != null){
				String messageid = (String) headers.get("Message-ID");
				report.setMessageid(messageid);
				reportDao.update(report);
				}
			}
			
			//System.out.println("***ステータス変更メール送信***");
		}
	}

	private void addMailList(Report report, List<Mail> sendList, Integer  mailCondition, Mail mail,long accountid) {
		if (mailCondition != null) {

			// 設定済
			int type = mailCondition.intValue();
			switch (type) {
				case 1:
					// 全て
					sendList.add(mail);
					break;
				case 2:
					// 自分へのアサインのみ
					if (report.getAssigneeid() == accountid) {
						sendList.add(mail);
					}
					break;
				case 3:
					// 拒否
					break;
			}
		} else {
			// 未設定
			sendList.add(mail);
		}
	}
	
	private void addBcc(Report report,Mail mail,Integer mailCondition,String mailaddr,long accountid){

			if (mailCondition != null) {
				// 設定済
				int type = mailCondition.intValue();
				switch (type) {
					case 1:
						// 全て
						mail.addBcc(mailaddr);
						break;
					case 2:
						// 自分へのアサインのみ
						if (report.getAssigneeid() == accountid) {
							mail.addBcc(mailaddr);
						}
						break;
					case 3:
						// 拒否
						break;
				}
			} else {
				// 未設定
				mail.addBcc(mailaddr);
			}
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public void setReportDxo(ReportDxo reportDxo) {
		this.reportDxo = reportDxo;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	
	public void setStatusHistoryDao(StatusHistoryDao statusHistoryDao) {
		this.statusHistoryDao = statusHistoryDao;
	}

	
	public void setWorkflowStatusDao(WorkflowStatusDao workflowStatusDao) {
		this.workflowStatusDao = workflowStatusDao;
	}

	
	public void setReportDataDao(ReportDataDao reportDataDao) {
		this.reportDataDao = reportDataDao;
	}

	
	public void setCustomFormDetailDao(CustomFormDetailDao customFormDetailDao) {
		this.customFormDetailDao = customFormDetailDao;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	
	public void setMailConditionDao(MailConditionDao mailConditionDao) {
		this.mailConditionDao = mailConditionDao;
	}

	
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	
	
	

}
