/* PRIORITY */
INSERT INTO PRIORITY(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('PRIORITY_ID_SEQ'),'緊急','priority_1.gif','緊急の修正が必要');
INSERT INTO PRIORITY(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('PRIORITY_ID_SEQ'),'高','priority_2.gif','優先順位高');
INSERT INTO PRIORITY(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('PRIORITY_ID_SEQ'),'中','priority_3.gif','優先順位中');
INSERT INTO PRIORITY(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('PRIORITY_ID_SEQ'),'低','priority_4.gif','後回しでもOK');
INSERT INTO PRIORITY_HEAD(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('PRIORITY_HEAD_ID_SEQ'),'デフォルト優先度','標準');
INSERT INTO PRIORITY_SCHEME(ID,HEADID,PRIORITYID,SORT) VALUES(NEXTVAL('PRIORITY_SCHEME_ID_SEQ'),1,1,1);
INSERT INTO PRIORITY_SCHEME(ID,HEADID,PRIORITYID,SORT) VALUES(NEXTVAL('PRIORITY_SCHEME_ID_SEQ'),1,2,2);
INSERT INTO PRIORITY_SCHEME(ID,HEADID,PRIORITYID,SORT) VALUES(NEXTVAL('PRIORITY_SCHEME_ID_SEQ'),1,3,3);
INSERT INTO PRIORITY_SCHEME(ID,HEADID,PRIORITYID,SORT) VALUES(NEXTVAL('PRIORITY_SCHEME_ID_SEQ'),1,4,4);

/* TYPE */
INSERT INTO TYPE(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('TYPE_ID_SEQ'),'バグ','type_1.gif','バグ');
INSERT INTO TYPE(ID,NAME,ICON,DESCRIPTION) VALUES(NEXTVAL('TYPE_ID_SEQ'),'要望','type_2.gif','要望');
INSERT INTO TYPE_HEAD(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('TYPE_HEAD_ID_SEQ'),'デフォルトタイプ','標準');
INSERT INTO TYPE_SCHEME(ID,HEADID,TYPEID,SORT) VALUES(NEXTVAL('TYPE_SCHEME_ID_SEQ'),1,1,1);
INSERT INTO TYPE_SCHEME(ID,HEADID,TYPEID,SORT) VALUES(NEXTVAL('TYPE_SCHEME_ID_SEQ'),1,2,2);

/* RESOLUTION */
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'未解決','未解決');
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'修正済','修正済');
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'修正しない','修正しない');
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'重複','重複');
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'不完全','不完全');
INSERT INTO RESOLUTION(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_ID_SEQ'),'再現しない','再現しない');
INSERT INTO RESOLUTION_HEAD(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('RESOLUTION_HEAD_ID_SEQ'),'デフォルト完了理由','標準');
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,1,1);
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,2,2);
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,3,3);
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,4,4);
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,5,5);
INSERT INTO RESOLUTION_SCHEME(ID,HEADID,RESOLUTIONID,SORT) VALUES(NEXTVAL('RESOLUTION_SCHEME_ID_SEQ'),1,6,6);

/* STATUS */
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'オープン','status_1.gif','','オープン',TRUE,'新規登録');
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,ASSIGNFLAG,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'アサイン済','','アサイン','再オープン',TRUE,'アサイン');
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'対応中','','対応開始','対応中','対応開始');
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,RESOLUTIONFLAG,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'解決済','','解決','解決済',TRUE,'解決');
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'クローズ','','クローズ','クローズ',TRUE,'クローズ');
INSERT INTO STATUS(ID,NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES(NEXTVAL('STATUS_ID_SEQ'),'再オープン','','再オープン','再オープン',TRUE,'再オープン');

/* ACCOUNT_KIND */
INSERT INTO ACCOUNT_KIND(ID,NAME) VALUES(NEXTVAL('ACCOUNT_KIND_ID_SEQ'),'管理');
INSERT INTO ACCOUNT_KIND(ID,NAME) VALUES(NEXTVAL('ACCOUNT_KIND_ID_SEQ'),'一般');
INSERT INTO ACCOUNT_KIND(ID,NAME) VALUES(NEXTVAL('ACCOUNT_KIND_ID_SEQ'),'ゲスト');

/* ACCOUNT */
/*
INSERT INTO ACCOUNT(ID,NAME,MAILADDR,PASSWORD,KIND) VALUES(NEXTVAL('ACCOUNT_ID_SEQ'),'アドミニストレーター','admin@gusuku.org','admin',1);
INSERT INTO ACCOUNT(ID,NAME,MAILADDR,PASSWORD,KIND) VALUES(NEXTVAL('ACCOUNT_ID_SEQ'),'開発者','dev@gusuku.org','dev',2);
*/
/* WORKFLOW */
INSERT INTO WORKFLOW(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('WORKFLOW_ID_SEQ'),'デフォルトワークフロー','標準的なワークフロー');

/* WORKFLOW_STATUS */
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,1,TRUE,FALSE,TRUE,FALSE,TRUE);
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,2,FALSE,FALSE,TRUE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,3,FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,4,FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,5,FALSE,TRUE,TRUE,FALSE,TRUE);
INSERT INTO WORKFLOW_STATUS(ID,WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(NEXTVAL('WORKFLOW_STATUS_ID_SEQ'),1,6,FALSE,FALSE,TRUE,FALSE,TRUE);

/* NEXTSTATUS */
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),1,2);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),2,3);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),3,2);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),3,4);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),4,5);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),5,6);
INSERT INTO NEXTSTATUS(ID,WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(NEXTVAL('NEXTSTATUS_ID_SEQ'),6,2);

/* FORM_TYPE */
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'テキストフィールド','textfield','テキストフィールド');
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'テキストエリア','textarea','テキストエリア');
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'チェックボックス','checkbox','チェックボックス');
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'ラジオボタン','radio','ラジオボタン');
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'ファイル','file','ファイル');
INSERT INTO FORM_TYPE(ID,NAME,TAGNAME,DESCRIPTION) VALUES(NEXTVAL('FORM_TYPE_ID_SEQ'),'セレクト','select','セレクト');

/* CUSTOM_VALUE */

INSERT INTO CUSTOM_VALUE_HEAD(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('CUSTOM_VALUE_HEAD_ID_SEQ'),'する・しない','文字列');
INSERT INTO CUSTOM_VALUE_HEAD(ID,NAME,DESCRIPTION) VALUES(NEXTVAL('CUSTOM_VALUE_HEAD_ID_SEQ'),'都道府県セレクト','文字列');

INSERT INTO CUSTOM_VALUE_DETAIL(ID,VALUEHEADID,LABEL,VALUE,SORT) VALUES(NEXTVAL('CUSTOM_VALUE_DETAIL_ID_SEQ'),1,'する','1',1);
INSERT INTO CUSTOM_VALUE_DETAIL(ID,VALUEHEADID,LABEL,VALUE,SORT) VALUES(NEXTVAL('CUSTOM_VALUE_DETAIL_ID_SEQ'),1,'しない','2',2);
INSERT INTO CUSTOM_VALUE_DETAIL(ID,VALUEHEADID,LABEL,VALUE,SORT) VALUES(NEXTVAL('CUSTOM_VALUE_DETAIL_ID_SEQ'),2,'北海道','1',1);
INSERT INTO CUSTOM_VALUE_DETAIL(ID,VALUEHEADID,LABEL,VALUE,SORT) VALUES(NEXTVAL('CUSTOM_VALUE_DETAIL_ID_SEQ'),2,'愛媛','2',2);
INSERT INTO CUSTOM_VALUE_DETAIL(ID,VALUEHEADID,LABEL,VALUE,SORT) VALUES(NEXTVAL('CUSTOM_VALUE_DETAIL_ID_SEQ'),2,'大阪','3',3);

/* CUSTOM_FORM */
/*
INSERT INTO CUSTOM_FORM_HEAD(NAME,DESCRIPTION) VALUES('テストフォーム','テスト用');

INSERT INTO CUSTOM_FORM_DETAIL(FORMHEADID,VALUEID,TYPEID,LABEL) VALUES(1,2,6,'都道府県');
*/
/* SAMPLE PROJECT */
/*
INSERT INTO PROJECT(KEY,NAME,WORKFLOWID,TYPEID,PRIORITYID,RESOLUTIONID,FORMID,LEADERID) VALUES('SAMP','サンプルプロジェクト',1,1,1,1,1,1);
*/
/* SAMPLE GROUP */
/*
INSERT INTO GROUPBASE(NAME) VALUES('サンプルグループ');
*/
