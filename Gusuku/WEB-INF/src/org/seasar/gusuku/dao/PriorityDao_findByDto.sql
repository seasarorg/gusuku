SELECT Priority.name, Priority.id, Priority.description, Priority.icon, Priority.delflag, Priority.rdate, Priority.udate
FROM Priority
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
