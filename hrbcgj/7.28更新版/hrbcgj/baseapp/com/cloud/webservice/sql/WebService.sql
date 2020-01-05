--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table WEBSERVICE (
	ID VARCHAR2(36) primary key,
	CLASSNAME VARCHAR2(200),
	SERVICENAME VARCHAR2(100),
	STATUS VARCHAR2(2),
	REMARK VARCHAR2(50)
);

--创建索引
create index WEBSERVICE_CLASSNAME on WEBSERVICE (CLASSNAME);
create index WEBSERVICE_SERVICENAME on WEBSERVICE (SERVICENAME);
create index WEBSERVICE_STATUS on WEBSERVICE (STATUS);
create index WEBSERVICE_REMARK on WEBSERVICE (REMARK);

--创建注释
comment on column WEBSERVICE.ID is '主键';
comment on column WEBSERVICE.CLASSNAME is '类名';
comment on column WEBSERVICE.SERVICENAME is '服务名';
comment on column WEBSERVICE.STATUS is '状态';
comment on column WEBSERVICE.REMARK is '备注';
