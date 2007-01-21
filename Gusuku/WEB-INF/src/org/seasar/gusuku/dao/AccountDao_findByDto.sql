SELECT
 A.ID,A.MAILADDR,A.PASSWORD,A.NAME,A.KIND,A.DELFLAG,A.RDATE,A.UDATE,AK.ID AS ID_0,AK.NAME AS NAME_0
FROM
 ACCOUNT A INNER JOIN ACCOUNT_KIND AK ON A.KIND = AK.ID
WHERE
/*IF dto.mailaddr != null && dto.mailaddr != "" */A.MAILADDR LIKE /*dto.likeMailaddr*/ AND/*END*/
/*IF dto.name!= null && dto.name != "" */A.NAME LIKE /*dto.likeName*/ AND/*END*/
/*IF dto.kind!= null && dto.kind != "" */A.KIND = /*dto.kind*/ AND/*END*/
A.DELFLAG = FALSE
ORDER BY A.ID
