--------------------------------------------------------
--  File created - Thursday-Sep-08-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table T_CAM_API_AUDIT
--------------------------------------------------------

  CREATE TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" 
   ("AUDIT_ID" VARCHAR2(22 BYTE), 
	"RESOURCE_TYPE" VARCHAR2(10 BYTE), 
	"REQUEST_URI" VARCHAR2(100 BYTE), 
	"REQUEST_DATE" TIMESTAMP (6), 
	"DATA" CLOB, 
	"RESPONSE_STATUS" NUMBER(3,0), 
	"STATUS" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" 
 LOB ("DATA") STORE AS SECUREFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES ) ;
--------------------------------------------------------
--  Constraints for Table T_CAM_API_AUDIT
--------------------------------------------------------

  ALTER TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" MODIFY ("AUDIT_ID" NOT NULL ENABLE);
  ALTER TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" MODIFY ("RESOURCE_TYPE" NOT NULL ENABLE);
  ALTER TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" MODIFY ("REQUEST_URI" NOT NULL ENABLE);
  ALTER TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" MODIFY ("REQUEST_DATE" NOT NULL ENABLE);
  ALTER TABLE "IGMS_ADMIN"."T_CAM_API_AUDIT" MODIFY ("RESPONSE_STATUS" NOT NULL ENABLE);
