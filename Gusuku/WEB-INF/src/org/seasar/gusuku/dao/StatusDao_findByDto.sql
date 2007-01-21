SELECT Status.name, Status.id, Status.description, Status.icon, Status.delflag, Status.rdate, Status.udate, Status.transition, Status.resolutionflag, Status.assignflag, Status.mailflag, Status.rssflag
FROM Status
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
