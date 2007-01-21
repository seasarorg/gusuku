SELECT PRIORITY_HEAD.name, PRIORITY_HEAD.id, PRIORITY_HEAD.description, PRIORITY_HEAD.rdate, PRIORITY_HEAD.udate
FROM PRIORITY_HEAD
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
