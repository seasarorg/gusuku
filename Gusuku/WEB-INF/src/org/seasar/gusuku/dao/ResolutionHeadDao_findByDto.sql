SELECT RESOLUTION_HEAD.name, RESOLUTION_HEAD.id, RESOLUTION_HEAD.description, RESOLUTION_HEAD.rdate, RESOLUTION_HEAD.udate
FROM RESOLUTION_HEAD
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
