SELECT Resolution.name, Resolution.id, Resolution.description, Resolution.delflag, Resolution.rdate, Resolution.udate
FROM Resolution
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
