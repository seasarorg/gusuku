SELECT COUNT(*)
FROM REPORT
WHERE
 PROJECTID = /*projectid*/
AND
 TYPEID = /*typeid*/
AND
 DELFLAG = FALSE
/*IF statusid != null*/
AND
 STATUSID = /*statusid*/
 /*END*/