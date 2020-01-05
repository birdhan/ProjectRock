--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table SYSTEMUSER (
	ID VARCHAR2(36) primary key,
	USERID VARCHAR2(10),
	PASSWORD VARCHAR2(50),
	USERNAME VARCHAR2(50),
	DEPARTNO VARCHAR2(20)
);

--创建索引
create index SYSTEMUSER_USERID on SYSTEMUSER (USERID);
create index SYSTEMUSER_PASSWORD on SYSTEMUSER (PASSWORD);
create index SYSTEMUSER_USERNAME on SYSTEMUSER (USERNAME);
create index SYSTEMUSER_DEPARTNO on SYSTEMUSER (DEPARTNO);

--创建注释
comment on column SYSTEMUSER.ID is '主键';
comment on column SYSTEMUSER.USERID is '帐号';
comment on column SYSTEMUSER.PASSWORD is '密码';
comment on column SYSTEMUSER.USERNAME is '名字';
comment on column SYSTEMUSER.DEPARTNO is '部门编号';
