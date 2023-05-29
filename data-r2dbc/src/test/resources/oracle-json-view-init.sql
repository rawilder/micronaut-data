CREATE TABLE "TBL_CONTACT"
(	"ID" NUMBER(*,0) NOT NULL ENABLE,
     "NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "AGE" INTEGER,
     "START_DATE_TIME" TIMESTAMP(6),
     "ACTIVE" NUMBER(1) DEFAULT 1,
     CONSTRAINT "TBL_CONTACT_PK" PRIMARY KEY ("ID")
);
