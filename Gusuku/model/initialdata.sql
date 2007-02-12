/* PRIORITY */
INSERT INTO PRIORITY(NAME,ICON,DESCRIPTION) VALUES('緊急','priority_1.gif','緊急の修正が必要');
INSERT INTO PRIORITY(NAME,ICON,DESCRIPTION) VALUES('高','priority_2.gif','優先順位高');
INSERT INTO PRIORITY(NAME,ICON,DESCRIPTION) VALUES('中','priority_3.gif','優先順位中');
INSERT INTO PRIORITY(NAME,ICON,DESCRIPTION) VALUES('低','priority_4.gif','後回しでもOK');
INSERT INTO PRIORITY_HEAD(NAME,DESCRIPTION) VALUES('デフォルト優先度','標準');
INSERT INTO PRIORITY_SCHEME(HEADID,PRIORITYID,SORT) VALUES(1,1,1);
INSERT INTO PRIORITY_SCHEME(HEADID,PRIORITYID,SORT) VALUES(1,2,2);
INSERT INTO PRIORITY_SCHEME(HEADID,PRIORITYID,SORT) VALUES(1,3,3);
INSERT INTO PRIORITY_SCHEME(HEADID,PRIORITYID,SORT) VALUES(1,4,4);

/* TYPE */
INSERT INTO TYPE(NAME,ICON,DESCRIPTION) VALUES('バグ','type_1.gif','バグ');
INSERT INTO TYPE(NAME,ICON,DESCRIPTION) VALUES('要望','type_2.gif','要望');
INSERT INTO TYPE_HEAD(NAME,DESCRIPTION) VALUES('デフォルトタイプ','標準');
INSERT INTO TYPE_SCHEME(HEADID,TYPEID,SORT) VALUES(1,1,1);
INSERT INTO TYPE_SCHEME(HEADID,TYPEID,SORT) VALUES(1,2,2);

/* RESOLUTION */
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('未解決','未解決');
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('修正済','修正済');
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('修正しない','修正しない');
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('重複','重複');
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('不完全','不完全');
INSERT INTO RESOLUTION(NAME,DESCRIPTION) VALUES('再現しない','再現しない');
INSERT INTO RESOLUTION_HEAD(NAME,DESCRIPTION) VALUES('デフォルト完了理由','標準');
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,1,1);
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,2,2);
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,3,3);
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,4,4);
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,5,5);
INSERT INTO RESOLUTION_SCHEME(HEADID,RESOLUTIONID,SORT) VALUES(1,6,6);

/* STATUS */
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES('オープン','status_1.gif','','オープン',TRUE,'新規登録');
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,ASSIGNFLAG,SUBJECT) VALUES('アサイン済','','アサイン','再オープン',TRUE,'アサイン');
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,SUBJECT) VALUES('対応中','','対応開始','対応中','対応開始');
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,RESOLUTIONFLAG,SUBJECT) VALUES('解決済','','解決','解決済',TRUE,'解決');
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES('クローズ','','クローズ','クローズ',TRUE,'クローズ');
INSERT INTO STATUS(NAME,ICON,TRANSITION,DESCRIPTION,MAILFLAG,SUBJECT) VALUES('再オープン','','再オープン','再オープン',TRUE,'再オープン');

/* ACCOUNT_KIND */
INSERT INTO ACCOUNT_KIND(NAME) VALUES('管理');
INSERT INTO ACCOUNT_KIND(NAME) VALUES('一般');
INSERT INTO ACCOUNT_KIND(NAME) VALUES('ゲスト');

/* ACCOUNT */
/*
INSERT INTO ACCOUNT(NAME,MAILADDR,PASSWORD,KIND) VALUES('アドミニストレーター','admin@gusuku.org','admin',1);
INSERT INTO ACCOUNT(NAME,MAILADDR,PASSWORD,KIND) VALUES('開発者','dev@gusuku.org','dev',2);
*/
/* WORKFLOW */
INSERT INTO WORKFLOW(NAME,DESCRIPTION) VALUES('デフォルトワークフロー','標準的なワークフロー');

/* WORKFLOW_STATUS */
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,1,TRUE,FALSE,TRUE,FALSE,TRUE);
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,2,FALSE,FALSE,TRUE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,3,FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,4,FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,5,FALSE,TRUE,TRUE,FALSE,TRUE);
INSERT INTO WORKFLOW_STATUS(WORKFLOWID,STATUSID,SFLAG,EFLAG,REPORTER,ASSIGNEE,LEADER) VALUES(1,6,FALSE,FALSE,TRUE,FALSE,TRUE);

/* NEXTSTATUS */
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(1,2);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(2,3);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(3,2);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(3,4);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(4,5);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(5,6);
INSERT INTO NEXTSTATUS(WORKFLOWSTATUSID,NEXTSTATUSID) VALUES(6,2);

/* FORM_TYPE */
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('テキストフィールド','textfield','テキストフィールド');
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('テキストエリア','textarea','テキストエリア');
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('チェックボックス','checkbox','チェックボックス');
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('ラジオボタン','radio','ラジオボタン');
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('ファイル','file','ファイル');
INSERT INTO FORM_TYPE(NAME,TAGNAME,DESCRIPTION) VALUES('セレクト','select','セレクト');

/* CUSTOM_VALUE */

INSERT INTO CUSTOM_VALUE_HEAD(NAME,DESCRIPTION) VALUES('する・しない','文字列');
INSERT INTO CUSTOM_VALUE_HEAD(NAME,DESCRIPTION) VALUES('都道府県セレクト','文字列');

INSERT INTO CUSTOM_VALUE_DETAIL(VALUEHEADID,LABEL,VALUE,SORT) VALUES(1,'する','1',1);
INSERT INTO CUSTOM_VALUE_DETAIL(VALUEHEADID,LABEL,VALUE,SORT) VALUES(1,'しない','2',2);
INSERT INTO CUSTOM_VALUE_DETAIL(VALUEHEADID,LABEL,VALUE,SORT) VALUES(2,'北海道','1',1);
INSERT INTO CUSTOM_VALUE_DETAIL(VALUEHEADID,LABEL,VALUE,SORT) VALUES(2,'愛媛','2',2);
INSERT INTO CUSTOM_VALUE_DETAIL(VALUEHEADID,LABEL,VALUE,SORT) VALUES(2,'大阪','3',3);

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
