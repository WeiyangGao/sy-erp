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