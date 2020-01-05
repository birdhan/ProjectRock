--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SYSTEM_MENU_PRIV (
	ID VARCHAR2(36) primary key,
	PRIVNO VARCHAR2(10),
	PRIVNAME VARCHAR2(50),
	LINKMENUID VARCHAR2(50)
);

--创建索引
create index SYSTEM_MENU_PRIV_PRIVNO on SYSTEM_MENU_PRIV (PRIVNO);
create index SYSTEM_MENU_PRIV_PRIVNAME on SYSTEM_MENU_PRIV (PRIVNAME);
create index SYSTEM_MENU_PRIV_LINKMENUID on SYSTEM_MENU_PRIV (LINKMENUID);

--创建注释
comment on column SYSTEM_MENU_PRIV.ID is '主键';
comment on column SYSTEM_MENU_PRIV.PRIVNO is '权限编号';
comment on column SYSTEM_MENU_PRIV.PRIVNAME is '权限名称';
comment on column SYSTEM_MENU_PRIV.LINKMENUID is '关联菜单';
