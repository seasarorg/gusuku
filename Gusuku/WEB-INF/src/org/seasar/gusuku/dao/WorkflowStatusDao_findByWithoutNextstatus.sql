SELECT WORKFLOW_STATUS.id, WORKFLOW_STATUS.workflowid, WORKFLOW_STATUS.statusid, WORKFLOW_STATUS.sflag, WORKFLOW_STATUS.eflag, status.name AS name_0, status.id AS id_0, status.description AS description_0, status.icon AS icon_0, status.delflag AS delflag_0, status.rdate AS rdate_0, status.udate AS udate_0, status.transition AS transition_0, status.resolutionflag AS resolutionflag_0 FROM WORKFLOW_STATUS LEFT OUTER JOIN Status status ON WORKFLOW_STATUS.STATUSID = status.ID
WHERE WORKFLOW_STATUS.ID NOT IN(
 SELECT NEXTSTATUSID
 FROM NEXTSTATUS
 WHERE WORKFLOWSTATUSID = /*workflowstatusid*/
) AND
WORKFLOW_STATUS.ID != /*workflowstatusid*/
AND WORKFLOW_STATUS.WORKFLOWID = /*workflowid*/
ORDER BY WORKFLOW_STATUS.statusid