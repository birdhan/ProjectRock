--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table APPACTION (
	ID varchar(36) primary key,
	TITLE VARCHAR2(50),
	ACTICE VARCHAR2(500),
	CREATETIME timestamp,
	STATE VARCHAR2(50),
	REPLY VARCHAR2(200),
	UID VARCHAR2(50)
);

--创建索引
create index APPACTION_TITLE on APPACTION (TITLE);
create index APPACTION_ACTICE on APPACTION (ACTICE);
create index APPACTION_CREATETIME on APPACTION (CREATETIME);
create index APPACTION_STATE on APPACTION (STATE);
create index APPACTION_REPLY on APPACTION (REPLY);
create index APPACTION_UID on APPACTION (UID);

--创建注释
comment on column APPACTION.ID is '主键';
comment on column APPACTION.TITLE is '标题';
comment on column APPACTION.ACTICE is '内容';
comment on column APPACTION.CREATETIME is '申请时间';
comment on column APPACTION.STATE is '状态';
comment on column APPACTION.REPLY is '批文';
comment on column APPACTION.UID is '用户id';
