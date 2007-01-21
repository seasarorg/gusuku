SELECT TYPE_HEAD.name, TYPE_HEAD.id, TYPE_HEAD.description, TYPE_HEAD.rdate, TYPE_HEAD.udate
FROM TYPE_HEAD
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
