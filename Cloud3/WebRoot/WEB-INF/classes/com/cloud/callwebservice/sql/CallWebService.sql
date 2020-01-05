--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table CALLWEBSERVICE (
	ID VARCHAR2(36) primary key,
	WSDL VARCHAR2(1000),
	CALLTYPE VARCHAR2(50),
	BEANID VARCHAR2(50),
	REMARK VARCHAR2(200)
);

--创建索引
create index CALLWEBSERVICE_WSDL on CALLWEBSERVICE (WSDL);
create index CALLWEBSERVICE_CALLTYPE on CALLWEBSERVICE (CALLTYPE);
create index CALLWEBSERVICE_BEANID on CALLWEBSERVICE (BEANID);
create index CALLWEBSERVICE_REMARK on CALLWEBSERVICE (REMARK);

--创建注释
comment on column CALLWEBSERVICE.ID is '主键';
comment on column CALLWEBSERVICE.WSDL is 'wsdl地址';
comment on column CALLWEBSERVICE.CALLTYPE is '调用类型';
comment on column CALLWEBSERVICE.BEANID is 'beanId';
comment on column CALLWEBSERVICE.REMARK is '备注';
