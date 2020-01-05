--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SYSTEM_ROLEUSER (
	ID VARCHAR2(36) primary key,
	LINKROLEID VARCHAR2(36),
	USERID VARCHAR2(500)
);

--创建索引
create index SYSTEM_ROLEUSER_LINKROLEID on SYSTEM_ROLEUSER (LINKROLEID);
create index SYSTEM_ROLEUSER_USERID on SYSTEM_ROLEUSER (USERID);

--创建注释
comment on column SYSTEM_ROLEUSER.ID is '主键';
comment on column SYSTEM_ROLEUSER.LINKROLEID is '角色ID';
comment on column SYSTEM_ROLEUSER.USERID is '人员userId';
