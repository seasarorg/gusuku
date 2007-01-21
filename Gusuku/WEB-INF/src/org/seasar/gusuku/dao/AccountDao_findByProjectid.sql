SELECT
 A.ID,A.MAILADDR,A.PASSWORD,A.NAME,A.KIND,A.DELFLAG,A.RDATE,A.UDATE,AK.ID AS ID_0,AK.NAME AS NAME_0
FROM
 ACCOUNT A INNER JOIN ACCOUNT_KIND AK ON A.KIND = AK.ID
WHERE
A.DELFLAG = FALSE
AND
A.ID IN (
 SELECT AccountGroupbase.ACCOUNTID
 FROM ACCOUNT_GROUPBASE AccountGroupbase
 WHERE AccountGroupbase.GROUPBASEID IN (
  SELECT ProjectGroupbase.GROUPBASEID
  FROM PROJECT_GROUPBASE ProjectGroupbase
  WHERE ProjectGroupbase.PROJECTID = /*projectid*/
 )
)
ORDER BY A.ID