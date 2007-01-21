SELECT Type.name, Type.id, Type.description, Type.icon, Type.delflag, Type.rdate, Type.udate
FROM Type
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
