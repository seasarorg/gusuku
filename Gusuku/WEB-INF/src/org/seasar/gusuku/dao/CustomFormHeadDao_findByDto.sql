SELECT
 ID,NAME,DESCRIPTION,DELFLAG,RDATE,UDATE
FROM
 CUSTOM_FORM_HEAD
WHERE
/*IF dto.name!= null && dto.name != "" */NAME LIKE /*dto.likeName*/ AND/*END*/
DELFLAG = FALSE
ORDER BY ID
