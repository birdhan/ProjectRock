--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table EASYSERVICE (
	ID varchar(36) primary key,
	NAME VARCHAR(50),
	LOGOPIC VARCHAR(100),
	LINKURL VARCHAR(1000)
);

--创建索引
create index EASYSERVICE_NAME on EASYSERVICE (NAME);
create index EASYSERVICE_LOGOPIC on EASYSERVICE (LOGOPIC);
create index EASYSERVICE_LINKURL on EASYSERVICE (LINKURL);

--创建注释
comment on column EASYSERVICE.ID is '主键';
comment on column EASYSERVICE.NAME is '服务名称';
comment on column EASYSERVICE.LOGOPIC is '图标';
comment on column EASYSERVICE.LINKURL is '外部链接';
