--建表语句，此仅为参考，开发人员可根据需要自行修改脚本
create table REGISTERUSER (
	ID varchar(36) primary key,
	ACCOUNT VARCHAR(50),
	PWD VARCHAR(100),
	USERNAME VARCHAR(50)
);

--创建索引
create index REGISTERUSER_ACCOUNT on REGISTERUSER (ACCOUNT);
create index REGISTERUSER_PWD on REGISTERUSER (PWD);
create index REGISTERUSER_USERNAME on REGISTERUSER (USERNAME);

--创建注释
comment on column REGISTERUSER.ID is '主键';
comment on column REGISTERUSER.ACCOUNT is '帐号';
comment on column REGISTERUSER.PWD is '密码';
comment on column REGISTERUSER.USERNAME is '姓名';
