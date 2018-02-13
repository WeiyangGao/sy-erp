-- Postgres
create table android_bus_accident
(
  id serial,
  accidentDate date ,
  accidentAddress text,
  license text,
  accidentNote text,
  accidentImgNo INTEGER,
  op_date date,
  op_no text,
  constraint pk_android_bus_accident primary key(id)
);
-- SQK dialect

-- Oracle
-- auto-generated definition
CREATE TABLE ANDROID_BUS_ACCIDENT
(
  ID               VARCHAR2(32) DEFAULT sys_guid()  NOT NULL
    PRIMARY KEY,
  ACCIDENT_DATE    DATE,
  ACCIDENT_ADDRESS VARCHAR2(200),
  ACCIDENT_IMG_NO  NUMBER,
  ACCIDENT_NOTE    VARCHAR2(200),
  OP_DATE          DATE DEFAULT sysdate,
  OP_NO            VARCHAR2(10),
  LICENSE          VARCHAR2(10)
)
/

COMMENT ON TABLE ANDROID_BUS_ACCIDENT IS 'android车辆事故表'
/

COMMENT ON COLUMN ANDROID_BUS_ACCIDENT.ID IS '使用sys_guid，自动主键生成。'
/

COMMENT ON COLUMN ANDROID_BUS_ACCIDENT.ACCIDENT_DATE IS '事故时间'
/

COMMENT ON COLUMN ANDROID_BUS_ACCIDENT.ACCIDENT_ADDRESS IS '事故地点'
/

COMMENT ON COLUMN ANDROID_BUS_ACCIDENT.LICENSE IS '事故车牌照'
/

