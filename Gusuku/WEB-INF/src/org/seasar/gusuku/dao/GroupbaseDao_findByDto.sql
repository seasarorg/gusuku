SELECT
 ID,NAME,DESCRIPTION
FROM
 GROUPBASE 
WHERE
/*IF dto.name != null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
