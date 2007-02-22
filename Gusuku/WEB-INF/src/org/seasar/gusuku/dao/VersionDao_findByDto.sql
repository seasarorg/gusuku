SELECT
 ID,PROJECTID,NAME,DESCRIPTION
FROM
 VERSION
WHERE
/*IF dto.name != null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
PROJECTID = /*dto.projectid*/
AND DELFLAG = FALSE
ORDER BY ID
