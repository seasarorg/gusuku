SELECT
 ID,MAILADDR,PASSWORD,NAME,KIND,DELFLAG,RDATE,UDATE
FROM ACCOUNT
WHERE ID NOT IN(
SELECT ACCOUNTID
FROM ACCOUNT_GROUPBASE
WHERE GROUPBASEID = /*groupid*/
)
AND DELFLAG = FALSE
