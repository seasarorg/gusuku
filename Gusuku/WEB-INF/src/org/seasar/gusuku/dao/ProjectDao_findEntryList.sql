SELECT
Project.ID,Project.NAME,Project.KEY,Project.COUNTER,Project.LEADERID,Project.WORKFLOWID,Project.TYPEID,Project.PRIORITYID,Project.RESOLUTIONID,Project.FORMID,Project.URL,Project.DEVURL,Project.SDATE,Project.EDATE,Project.DESCRIPTION,
Account.NAME NAME_0
FROM PROJECT Project LEFT OUTER JOIN ACCOUNT Account ON Project.LEADERID = Account.ID
WHERE
Project.DELFLAG = FALSE
AND
Project.ID IN 
(
 SELECT DISTINCT ProjectGroupbase.PROJECTID FROM PROJECT_GROUPBASE ProjectGroupbase
 WHERE ProjectGroupbase.GROUPBASEID IN 
 (
  SELECT AccountGroupbase.GROUPBASEID FROM ACCOUNT_GROUPBASE AccountGroupbase
  WHERE AccountGroupbase.ACCOUNTID = /*accountid*/
 )
)
ORDER BY Project.ID
