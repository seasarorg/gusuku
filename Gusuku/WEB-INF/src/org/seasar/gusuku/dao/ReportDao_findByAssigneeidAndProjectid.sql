SELECT Report.key, Report.id, Report.environment, Report.title, Report.delflag, Report.rdate, Report.udate, Report.projectid, Report.priorityid, Report.detail, Report.assigneeid, Report.typeid, Report.reporterid, Report.resolutionid, Report.statusid, status.name AS name_0, status.id AS id_0, status.description AS description_0, status.icon AS icon_0, status.delflag AS delflag_0, status.rdate AS rdate_0, status.udate AS udate_0, status.transition AS transition_0, status.resolutionflag AS resolutionflag_0, status.assignflag AS assignflag_0, status.mailflag AS mailflag_0, status.rssflag AS rssflag_0, priority.name AS name_1, priority.id AS id_1, priority.description AS description_1, priority.icon AS icon_1, priority.delflag AS delflag_1, priority.rdate AS rdate_1, priority.udate AS udate_1, resolution.name AS name_2, resolution.id AS id_2, resolution.description AS description_2, resolution.delflag AS delflag_2, resolution.rdate AS rdate_2, resolution.udate AS udate_2, assignee.name AS name_3, assignee.id AS id_3, assignee.password AS password_3, assignee.kind AS kind_3, assignee.delflag AS delflag_3, assignee.mailaddr AS mailaddr_3, assignee.rdate AS rdate_3, assignee.udate AS udate_3, reporter.name AS name_4, reporter.id AS id_4, reporter.password AS password_4, reporter.kind AS kind_4, reporter.delflag AS delflag_4, reporter.mailaddr AS mailaddr_4, reporter.rdate AS rdate_4, reporter.udate AS udate_4, type.name AS name_5, type.id AS id_5, type.description AS description_5, type.icon AS icon_5, type.delflag AS delflag_5, type.rdate AS rdate_5, type.udate AS udate_5 FROM Report LEFT OUTER JOIN Status status ON Report.STATUSID = status.ID LEFT OUTER JOIN Priority priority ON Report.PRIORITYID = priority.ID LEFT OUTER JOIN Resolution resolution ON Report.RESOLUTIONID = resolution.ID LEFT OUTER JOIN Account assignee ON Report.ASSIGNEEID = assignee.ID LEFT OUTER JOIN Account reporter ON Report.REPORTERID = reporter.ID LEFT OUTER JOIN Type type ON Report.TYPEID = type.ID LEFT OUTER JOIN Project project ON Report.PROJECTID = project.ID
WHERE Report.DELFLAG = FALSE
AND Report.ASSIGNEEID = /*assigneeid*/
AND Report.PROJECTID = /*projectid*/
AND STATUSID IN 
(
 SELECT STATUSID
 FROM WORKFLOW_STATUS 
 WHERE 
  WORKFLOWID IN 
 (
  SELECT WORKFLOWID FROM PROJECT WHERE ID IN 
   (
    SELECT PROJECTID FROM PROJECT_GROUPBASE WHERE GROUPBASEID IN
    (
     SELECT GROUPBASEID FROM ACCOUNT_GROUPBASE WHERE ACCOUNTID = /*assigneeid*/
    )
   )
 )  AND EFLAG = FALSE 
)
ORDER BY (SELECT SORT FROM PRIORITY_SCHEME WHERE HEADID = PROJECT.PRIORITYID AND REPORT.PRIORITYID = PRIORITYID),Report.RDATE DESC
