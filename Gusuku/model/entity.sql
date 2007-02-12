
DROP TABLE ACCOUNT_GROUPBASE;
DROP TABLE RESOLUTION_SCHEME;
DROP TABLE PRIORITY_SCHEME;
DROP TABLE TYPE_SCHEME;
DROP TABLE MAIL_CONDITION;
DROP TABLE SEARCH_CONDITION_CUSTOM;
DROP TABLE REPORT_DATA;
DROP TABLE CUSTOM_VALUE_DETAIL;
DROP TABLE STATUS_HISTORY;
DROP TABLE NEXTSTATUS;
DROP TABLE COMMENT;
DROP TABLE PROJECT_GROUPBASE;
DROP TABLE SEARCH_CONDITION_BASIC;
DROP TABLE PROJECT;
DROP TABLE SEARCH_CONDITION_HEAD;
DROP TABLE CUSTOM_FORM_DETAIL;
DROP TABLE REPORT;
DROP TABLE WORKFLOW_STATUS;
DROP TABLE ACCOUNT;
DROP TABLE GROUPBASE;
DROP TABLE RESOLUTION_HEAD;
DROP TABLE PRIORITY_HEAD;
DROP TABLE TYPE_HEAD;
DROP TABLE SYSTEM_PROPERTY;
DROP TABLE CUSTOM_FORM_HEAD;
DROP TABLE FORM_TYPE;
DROP TABLE CUSTOM_VALUE_HEAD;
DROP TABLE ACCOUNT_KIND;
DROP TABLE WORKFLOW;
DROP TABLE RESOLUTION;
DROP TABLE STATUS;
DROP TABLE TYPE;
DROP TABLE PRIORITY;

CREATE TABLE PRIORITY (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , ICON TEXT
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE TYPE (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , ICON TEXT
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE STATUS (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , ICON TEXT
     , TRANSITION TEXT
     , ASSIGNFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RESOLUTIONFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , MAILFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RSSFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , SUBJECT TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE RESOLUTION (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE WORKFLOW (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT NOT NULL
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE ACCOUNT_KIND (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
);

CREATE TABLE CUSTOM_VALUE_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE FORM_TYPE (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , TAGNAME TEXT NOT NULL
     , DESCRIPTION TEXT
);

CREATE TABLE CUSTOM_FORM_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE SYSTEM_PROPERTY (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , KEY TEXT NOT NULL
     , VALUE TEXT NOT NULL
);

CREATE TABLE TYPE_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE PRIORITY_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE RESOLUTION_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE GROUPBASE (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , NAME TEXT NOT NULL
     , DESCRIPTION TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
);

CREATE TABLE ACCOUNT (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , MAILADDR TEXT NOT NULL
     , PASSWORD TEXT NOT NULL
     , NAME TEXT NOT NULL
     , KIND BIGINT NOT NULL
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
     , CONSTRAINT FK_ACCOUNT_1 FOREIGN KEY (KIND)
                  REFERENCES ACCOUNT_KIND (ID)
);

CREATE TABLE WORKFLOW_STATUS (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , WORKFLOWID BIGINT NOT NULL
     , STATUSID BIGINT NOT NULL
     , SFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , EFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , REPORTER BOOLEAN DEFAULT 'TRUE' NOT NULL
     , ASSIGNEE BOOLEAN DEFAULT 'TRUE' NOT NULL
     , LEADER BOOLEAN DEFAULT 'TRUE' NOT NULL
     , CONSTRAINT FK_WORKFLOW_STATUS_1 FOREIGN KEY (WORKFLOWID)
                  REFERENCES WORKFLOW (ID)
     , CONSTRAINT FK_WORKFLOW_STATUS_2 FOREIGN KEY (STATUSID)
                  REFERENCES STATUS (ID) ON DELETE CASCADE
);

CREATE TABLE REPORT (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , PROJECTID BIGINT NOT NULL
     , TYPEID BIGINT DEFAULT '0' NOT NULL
     , TITLE TEXT NOT NULL
     , KEY TEXT NOT NULL
     , MESSAGEID TEXT NOT NULL
     , STATUSID BIGINT DEFAULT '0' NOT NULL
     , RESOLUTIONID BIGINT DEFAULT '0'
     , PRIORITYID BIGINT DEFAULT '0' NOT NULL
     , REPORTERID BIGINT NOT NULL
     , ASSIGNEEID BIGINT
     , COMPONENTID BIGINT
     , VERSIONID BIGINT
     , ENVIRONMENT TEXT
     , DETAIL TEXT NOT NULL
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
     , CONSTRAINT FK_REPORT_7 FOREIGN KEY (REPORTERID)
                  REFERENCES ACCOUNT (ID)
     , CONSTRAINT FK_REPORT_6 FOREIGN KEY (PROJECTID)
                  REFERENCES PROJECT (ID)
     , CONSTRAINT FK_REPORT_4 FOREIGN KEY (TYPEID)
                  REFERENCES TYPE (ID) ON DELETE SET DEFAULT
     , CONSTRAINT FK_REPORT_3 FOREIGN KEY (STATUSID)
                  REFERENCES STATUS (ID) ON DELETE SET DEFAULT
     , CONSTRAINT FK_REPORT_2 FOREIGN KEY (PRIORITYID)
                  REFERENCES PRIORITY (ID) ON DELETE SET DEFAULT
);

CREATE TABLE CUSTOM_FORM_DETAIL (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , FORMHEADID BIGINT NOT NULL
     , VALUEID BIGINT
     , TYPEID BIGINT NOT NULL
     , LABEL TEXT NOT NULL
     , DEFAULTVALUE TEXT
     , VALUETYPE INT DEFAULT '1' NOT NULL
     , REQUIREFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , CHECKTYPE INT DEFAULT '0' NOT NULL
     , LENGTHLOW BIGINT
     , LENGTHHIGH BIGINT
     , DATEFROM DATE
     , DATETO DATE
     , RANGELOW BIGINT
     , RANGEHIGH BIGINT
     , SORT INT DEFAULT '0' NOT NULL
     , COMMENT TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , CONSTRAINT FK_CUSTOM_FORM_DETAIL_2 FOREIGN KEY (FORMHEADID)
                  REFERENCES CUSTOM_FORM_HEAD (ID)
     , CONSTRAINT FK_CUSTOM_FORM_DETAIL_1 FOREIGN KEY (TYPEID)
                  REFERENCES FORM_TYPE (ID)
);

CREATE TABLE SEARCH_CONDITION_HEAD (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , ACCOUNTID BIGINT NOT NULL
     , NAME TEXT NOT NULL
     , VISIBLE BOOLEAN DEFAULT 'FALSE' NOT NULL
     , SORT BIGINT
     , AMOUNT INT DEFAULT '10' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , CONSTRAINT FK_SEARCH_CONDITION_HEAD_1 FOREIGN KEY (ACCOUNTID)
                  REFERENCES ACCOUNT (ID)
);

CREATE TABLE PROJECT (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , KEY TEXT NOT NULL
     , COUNTER BIGINT DEFAULT '0' NOT NULL
     , WORKFLOWID BIGINT NOT NULL
     , FORMID BIGINT
     , TYPEID BIGINT NOT NULL
     , PRIORITYID BIGINT NOT NULL
     , RESOLUTIONID BIGINT NOT NULL
     , NAME TEXT NOT NULL
     , LEADERID BIGINT
     , SDATE TIMESTAMP
     , EDATE TIMESTAMP
     , URL TEXT
     , DEVURL TEXT
     , MAILADDR TEXT
     , SUBJECT TEXT
     , DESCRIPTION TEXT
     , CLOSEFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
     , CONSTRAINT FK_PROJECT_1 FOREIGN KEY (LEADERID)
                  REFERENCES ACCOUNT (ID)
     , CONSTRAINT FK_PROJECT_2 FOREIGN KEY (WORKFLOWID)
                  REFERENCES WORKFLOW (ID)
     , CONSTRAINT FK_PROJECT_3 FOREIGN KEY (FORMID)
                  REFERENCES CUSTOM_FORM_HEAD (ID)
     , CONSTRAINT FK_PROJECT_4 FOREIGN KEY (TYPEID)
                  REFERENCES TYPE_HEAD (ID)
     , CONSTRAINT FK_PROJECT_5 FOREIGN KEY (RESOLUTIONID)
                  REFERENCES RESOLUTION_HEAD (ID)
     , CONSTRAINT FK_PROJECT_6 FOREIGN KEY (PRIORITYID)
                  REFERENCES PRIORITY_HEAD (ID)
);

CREATE TABLE SEARCH_CONDITION_BASIC (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , CONDITIONHEADID BIGINT NOT NULL
     , PROJECTID BIGINT NOT NULL
     , TITLE TEXT
     , TYPEID TEXT
     , STATUSID TEXT
     , PRIORITYID TEXT
     , ASSIGNEEID TEXT
     , ENVIRONMENT TEXT
     , DETAIL TEXT
     , DATEFROM DATE
     , DATETO DATE
     , CONSTRAINT FK_SEARCH_CONDITION_BASIC_2 FOREIGN KEY (PROJECTID)
                  REFERENCES PROJECT (ID)
     , CONSTRAINT FK_SEARCH_CONDITION_BASIC_1 FOREIGN KEY (CONDITIONHEADID)
                  REFERENCES SEARCH_CONDITION_HEAD (ID) ON DELETE CASCADE
);

CREATE TABLE PROJECT_GROUPBASE (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , PROJECTID BIGINT NOT NULL
     , GROUPBASEID BIGINT NOT NULL
     , CONSTRAINT UQ_PROJECT_GROUPBASE_1 UNIQUE (PROJECTID, GROUPBASEID)
     , CONSTRAINT FK_PROJECT_GROUPBASE_1 FOREIGN KEY (PROJECTID)
                  REFERENCES PROJECT (ID) ON DELETE CASCADE
     , CONSTRAINT FK_PROJECT_GROUPBASE_2 FOREIGN KEY (GROUPBASEID)
                  REFERENCES GROUPBASE (ID) ON DELETE CASCADE
);

CREATE TABLE COMMENT (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , REPORTID BIGINT NOT NULL
     , WRITERID BIGINT NOT NULL
     , COMMENT TEXT
     , FILENAME TEXT
     , MESSAGEID TEXT
     , DELFLAG BOOLEAN DEFAULT 'FALSE' NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , UDATE TIMESTAMP
     , CONSTRAINT FK_COMMENT_2 FOREIGN KEY (WRITERID)
                  REFERENCES ACCOUNT (ID)
     , CONSTRAINT FK_COMMENT_1 FOREIGN KEY (REPORTID)
                  REFERENCES REPORT (ID)
);

CREATE TABLE NEXTSTATUS (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , WORKFLOWSTATUSID BIGINT NOT NULL
     , NEXTSTATUSID BIGINT NOT NULL
     , CONSTRAINT FK_NEXTSTATUS_1 FOREIGN KEY (WORKFLOWSTATUSID)
                  REFERENCES WORKFLOW_STATUS (ID) ON DELETE CASCADE
);

CREATE TABLE STATUS_HISTORY (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , REPORTID BIGINT NOT NULL
     , STATUSID BIGINT NOT NULL
     , CHANGERID BIGINT NOT NULL
     , RDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL
     , CONSTRAINT FK_STATUS_HISTORY_3 FOREIGN KEY (CHANGERID)
                  REFERENCES ACCOUNT (ID)
     , CONSTRAINT FK_STATUS_HISTORY_2 FOREIGN KEY (REPORTID)
                  REFERENCES REPORT (ID)
     , CONSTRAINT FK_STATUS_HISTORY_1 FOREIGN KEY (STATUSID)
                  REFERENCES STATUS (ID)
);

CREATE TABLE CUSTOM_VALUE_DETAIL (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , VALUEHEADID BIGINT NOT NULL
     , LABEL TEXT
     , VALUE TEXT NOT NULL
     , SORT INT DEFAULT '0' NOT NULL
     , CONSTRAINT FK_CUSTOM_VALUE_DETAIL_1 FOREIGN KEY (VALUEHEADID)
                  REFERENCES CUSTOM_VALUE_HEAD (ID)
);

CREATE TABLE REPORT_DATA (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , REPORTID BIGINT NOT NULL
     , FORMID BIGINT NOT NULL
     , TEXTVALUE TEXT
     , NUMERICVALUE BIGINT
     , DATEVALUE DATE
     , CONSTRAINT FK_REPORT_DATA_1 FOREIGN KEY (FORMID)
                  REFERENCES CUSTOM_FORM_DETAIL (ID)
     , CONSTRAINT FK_REPORT_DATA_2 FOREIGN KEY (REPORTID)
                  REFERENCES REPORT (ID)
);

CREATE TABLE SEARCH_CONDITION_CUSTOM (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , CONDITIONBASICID BIGINT NOT NULL
     , FORMID BIGINT NOT NULL
     , TEXTVALUE TEXT
     , RANGELOW BIGINT
     , RANGHIGH BIGINT
     , DATEFROM DATE
     , DATETO DATE
     , CONSTRAINT FK_SEARCH_CONDITION_CUSTOM_1 FOREIGN KEY (CONDITIONBASICID)
                  REFERENCES SEARCH_CONDITION_BASIC (ID) ON DELETE CASCADE
);

CREATE TABLE MAIL_CONDITION (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , ACCOUNTID BIGINT NOT NULL
     , PROJECTID BIGINT NOT NULL
     , COMMENT INT DEFAULT '1' NOT NULL
     , START INT DEFAULT '1' NOT NULL
     , PROCESS INT DEFAULT '1' NOT NULL
     , END INT DEFAULT '1' NOT NULL
     , CONSTRAINT FK_MAIL_CONDITION_1 FOREIGN KEY (PROJECTID)
                  REFERENCES PROJECT (ID)
     , CONSTRAINT FK_MAIL_CONDITION_2 FOREIGN KEY (ACCOUNTID)
                  REFERENCES ACCOUNT (ID)
);

CREATE TABLE TYPE_SCHEME (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , HEADID BIGINT NOT NULL
     , TYPEID BIGINT NOT NULL
     , SORT INT DEFAULT '0' NOT NULL
     , CONSTRAINT FK_TYPE_SCHEME_2 FOREIGN KEY (HEADID)
                  REFERENCES TYPE_HEAD (ID)
     , CONSTRAINT FK_TYPE_SCHEME_1 FOREIGN KEY (TYPEID)
                  REFERENCES TYPE (ID)
);

CREATE TABLE PRIORITY_SCHEME (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , HEADID BIGINT NOT NULL
     , PRIORITYID BIGINT NOT NULL
     , SORT INT DEFAULT '0' NOT NULL
     , CONSTRAINT FK_PRIORITY_SCHEME_1 FOREIGN KEY (HEADID)
                  REFERENCES PRIORITY_HEAD (ID)
     , CONSTRAINT FK_PRIORITY_SCHEME_2 FOREIGN KEY (PRIORITYID)
                  REFERENCES PRIORITY (ID)
);

CREATE TABLE RESOLUTION_SCHEME (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , HEADID BIGINT NOT NULL
     , RESOLUTIONID BIGINT NOT NULL
     , SORT INT DEFAULT '0' NOT NULL
     , CONSTRAINT FK_RESOLUTION_SCHEME_1 FOREIGN KEY (HEADID)
                  REFERENCES RESOLUTION_HEAD (ID)
     , CONSTRAINT FK_RESOLUTION_SCHEME_2 FOREIGN KEY (RESOLUTIONID)
                  REFERENCES RESOLUTION (ID)
);

CREATE TABLE ACCOUNT_GROUPBASE (
       ID BIGINT NOT NULL IDENTITY PRIMARY KEY
     , ACCOUNTID BIGINT NOT NULL
     , GROUPBASEID BIGINT NOT NULL
     , CONSTRAINT UQ_ACCOUNT_GROUPBASE_1 UNIQUE (ACCOUNTID, GROUPBASEID)
     , CONSTRAINT FK_ACCOUNT_GROUPBASE_1 FOREIGN KEY (GROUPBASEID)
                  REFERENCES GROUPBASE (ID) ON DELETE CASCADE
     , CONSTRAINT FK_ACCOUNT_GROUPBASE_2 FOREIGN KEY (ACCOUNTID)
                  REFERENCES ACCOUNT (ID) ON DELETE CASCADE
);
