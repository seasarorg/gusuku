SELECT Workflow.name, Workflow.id, Workflow.description, Workflow.delflag, Workflow.rdate, Workflow.udate
FROM Workflow 
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
