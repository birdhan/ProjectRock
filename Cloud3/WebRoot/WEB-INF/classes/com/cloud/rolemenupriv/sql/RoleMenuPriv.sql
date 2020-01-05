--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table ROLEMENUPRIV (
	ID VARCHAR2(36) primary key,
	LINKROLEID VARCHAR2(36),
	LINKMENUID VARCHAR2(36),
	PRIVNO VARCHAR2(50)
);

--创建索引
create index ROLEMENUPRIV_LINKROLEID on ROLEMENUPRIV (LINKROLEID);
create index ROLEMENUPRIV_LINKMENUID on ROLEMENUPRIV (LINKMENUID);
create index ROLEMENUPRIV_PRIVNO on ROLEMENUPRIV (PRIVNO);

--创建注释
comment on column ROLEMENUPRIV.ID is '主键';
comment on column ROLEMENUPRIV.LINKROLEID is '关联角色ID';
comment on column ROLEMENUPRIV.LINKMENUID is '关联菜单ID';
comment on column ROLEMENUPRIV.PRIVNO is '对应权限编号';
